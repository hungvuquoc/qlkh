package com.example.qlkh.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.qlkh.config.JwtTokenBlacklist;
import com.example.qlkh.constant.Variables;
import com.example.qlkh.constant.enums.VerificationType;
import com.example.qlkh.dto.*;
import com.example.qlkh.dto.request.UserReqDto;
import com.example.qlkh.dto.request.searchs.SearchDto;
import com.example.qlkh.dto.response.UserRespDto;
import com.example.qlkh.dto.response.projection.UserProjectionDto;
import com.example.qlkh.entity.*;
import com.example.qlkh.error.status.CommonStatus;
import com.example.qlkh.error.status.UserStatus;
import com.example.qlkh.exception.DataRuntimeException;
import com.example.qlkh.repository.*;
import com.example.qlkh.repository.warehouse.WarehouseRepository;
import com.example.qlkh.service.UserService;
import com.example.qlkh.service.mapper.EmployeeMapper;
import com.example.qlkh.service.mapper.UserMapper;
import com.example.qlkh.service.mapper.warehouse.WarehouseMapper;
import com.example.qlkh.utils.EbsEmailUtils;
import com.example.qlkh.utils.EbsGenerateUtils;
import com.example.qlkh.utils.EbsSecurityUtils;
import com.example.qlkh.utils.EbsTokenUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;
    private final UserRoleConnectRepository userRoleConnectRepository;
    private final UserAuthorityConnectRepository userAuthorityConnectRepository;
    private final WarehouseRepository warehouseRepository;
    private final EmployeeRepository employeeRepository;
    private final UserMapper userMapper;
    private final WarehouseMapper warehouseMapper;
    private final EmployeeMapper employeeMapper;
    private final VerificationCodeRepository verificationCodeRepository;
    private final EbsGenerateUtils generateUtils;
    private final JwtTokenBlacklist tokenBlacklist;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EbsEmailUtils emailUtils;
    @Value("${mailServer.SendLimit}")
    private Integer sendLimit;
    @Value("${mailServer.subject}")
    private String subject;

    @Override
    public Map<Long, EbsWarehouse> getWarehouseByUserId(@NonNull Long id) {
        List<EbsWarehouseProjection> warehouseProjections = warehouseRepository.getEbsWarehousesByUserId(id);
        Map<Long, EbsWarehouse> warehouseMap = new HashMap<>();

        for (EbsWarehouseProjection warehouseProjection : warehouseProjections) {
            EbsWarehouse warehouse = warehouseMapper.mapProjectionToEbsWarehouse(warehouseProjection);
            warehouseMap.put(warehouse.getId(), warehouse);
        }
        return warehouseMap;
    }

    @Override
    public User getUserByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            throw new DataRuntimeException(UserStatus.USERNAME_IS_EMPTY);
        }

        User user = userRepository.findByUsername(username);
        if (null == user) {
            throw new DataRuntimeException(CommonStatus.ACCOUNT_NOT_FOUND);
        }

        return user;
    }

    @Override
    public EbsPrincipal getInfo() {
        User user = userRepository.findByUsername(EbsSecurityUtils.getUsername());
        if (null == user) {
            throw new DataRuntimeException(CommonStatus.ACCOUNT_NOT_FOUND);
        }

        return userMapper.entityToPrincipal(user);
    }

    @Override
    public UserRespDto getById(@NonNull Long id) {
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(UserStatus.ID_NOT_FOUND));

        Employee employee = employeeRepository.getByUserId(id);
        if (employee == null) {
            throw new DataRuntimeException(UserStatus.EMPLOYEE_ID_NOT_FOUND);
        }

        return userMapper.entityToDto(entity, employee);
    }

    @Override
    public Page<UserProjectionDto> search(@NonNull SearchDto dto) {
        EbsPrincipal principal = EbsSecurityUtils.getCurrentUser();
        List<UserProjectionDto> content = userRepository.search(dto, principal);
        long total = userRepository.count(dto, principal);
        return new PageImpl<>(content, dto.getPageable(), total);
    }

    @Transactional(rollbackOn = {Exception.class})
    @Override
    public UserRespDto save(UserReqDto dto) {
        dto.setId(null);
        this.validateDto(dto);

        User user = new User();
        this.mapDtoToEntity(dto, user);
        user = userRepository.save(user);

        Employee employee = this.mapUserToEmployee(user, dto.getEmployeeId());
        return userMapper.entityToDto(user, employee);
    }

//    @Override
//    public boolean createVerificationToChangePassword(String username) {
//        return hasSendVerificationCodeSuccess(username, VerificationType.FORGOT_PASSWORD);
//    }

//    @Override
//    public boolean createVerificationToRegister(String username) {
//        return hasSendVerificationCodeSuccess(username, VerificationType.REGISTER);
//    }

//    @Override
//    public TokenDto register(UserDto dto) {
//        dto.setUsername(dto.getEmail());
//        this.validateRegister(dto);
//
//        User user = new User();
//        this.mapDtoToEntity(dto, user, true);
//        user = userRepository.save(user);
//
//        String accessToken = EbsTokenUtils.createAccessToken(user);
//        String refreshToken = EbsTokenUtils.createRefreshToken(user.getUsername());
//        EbsPrincipal userPrincipal = userMapper.entityToPrincipal(user);
//        return new TokenDto(accessToken, refreshToken, userPrincipal);
//    }

    @Override
    public TokenDto refreshToken(String refreshToken) {
        Algorithm algorithm = Algorithm.HMAC256(Variables.SECRET_KEY.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(refreshToken);
        String username = decodedJWT.getSubject();
        User user = userRepository.findByUsername(username);

        if (null == user) {
            throw new DataRuntimeException(CommonStatus.ACCOUNT_NOT_FOUND);
        }

        String accessToken = EbsTokenUtils.createAccessToken(user);
        EbsPrincipal userPrincipal = userMapper.entityToPrincipal(user);

        Map<String, Boolean> authorities = new HashMap<>();
        for (String authority : userPrincipal.getAuthorities()) {
            authorities.put(authority, true);
        }
        if (CollectionUtils.isNotEmpty(userPrincipal.getRoles())) {
            for (String role : userPrincipal.getRoles()) {
                authorities.put(role, true);
            }
        }

        return new TokenDto(accessToken, refreshToken, userPrincipal, authorities);
    }

    @Override
    public void logout(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (!StringUtils.hasText(header) || !header.startsWith("Bearer ")) {
            throw new DataRuntimeException(UserStatus.IS_NOT_TOKEN);
        }

        String token = header.substring(7);

        tokenBlacklist.add(token);
    }

    @Transactional(rollbackOn = {Exception.class})
    @Override
    public UserRespDto update(@NonNull Long id, @NonNull UserReqDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(UserStatus.ID_NOT_FOUND));
        dto.setId(id);
        this.validateDto(dto);
        this.deleteRoleOldAndAuthorityOld(user, dto);
        this.mapDtoToEntity(dto, user);
        user = userRepository.save(user);

        Employee employee = this.updateEmployee(user);
        return userMapper.entityToDto(user, employee);
    }

    @Override
    public boolean changePassword(UserDto dto) {
        this.validateChangePassword(dto);

        User user = userRepository.findByUsername(dto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
        verificationCodeRepository.removeByUsername(user.getUsername());

        return true;
    }

    @Override
    public boolean deleteById(@NonNull Long id) {
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(UserStatus.ID_NOT_FOUND));

        if (employeeRepository.userHasUsed(id)) {
            throw new DataRuntimeException(UserStatus.HAS_USED);
        }

        userRepository.delete(entity);
        return true;
    }

    @Transactional
    @Override
    public void increaseFailedAttempts(String username, byte newFailAttempts) {
        userRepository.updateFailedAttempts(newFailAttempts, username);
    }

    @Transactional
    @Override
    public void temporaryLock(String username, int coefficient) {
        LocalDateTime date = LocalDateTime.now().plusMinutes((long) Variables.LOCK_TIME_DURATION * coefficient);

        userRepository.updateLockedTime(date, username);
    }

    @Override
    public boolean permanentLock(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null || user.getLockTime() == null || LocalDateTime.now().isBefore(user.getLockTime())) {
            return false;
        }

        user.setAccountNonLocked(true);
        user.setLockTime(null);
        user.setFailedAttempt((byte) 0);
        userRepository.save(user);

        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new ProviderNotFoundException(CommonStatus.ACCOUNT_NOT_FOUND.getMessage());
        }

        if (user.getLockTime() != null && user.getLockTime().isAfter(LocalDateTime.now())) {
            throw new LockedException(CommonStatus.TEMPORARY_LOCK.getMessage());
        }

        return user;
    }

    private Employee updateEmployee(@NonNull User user) {
        Employee employee = employeeRepository.getByUserId(user.getId());
        employee.setDeleted(!user.isActive());
        return employeeRepository.save(employee);
    }

    private Employee mapUserToEmployee(@NonNull User user, @NonNull Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new DataRuntimeException(UserStatus.EMPLOYEE_ID_NOT_FOUND));

        employee.setDeleted(!user.isActive());
        employee.setUser(user);
        return employeeRepository.save(employee);
    }

    private void deleteRoleOldAndAuthorityOld(@NonNull User entity, @NonNull UserReqDto dto) {
        if (CollectionUtils.isNotEmpty(entity.getRoles())) {
            if (CollectionUtils.isEmpty(dto.getRoles())) {
                userRoleConnectRepository.deleteByUserId(entity.getId());
            } else {
                userRoleConnectRepository.deleteByUserId(entity.getId(), dto.getRoles());
            }
        }

        if (CollectionUtils.isNotEmpty(entity.getAuthorityEntities())) {
            if (CollectionUtils.isEmpty(dto.getAuthorities())) {
                userAuthorityConnectRepository.deleteByUserId(entity.getId());
            } else {
                userAuthorityConnectRepository.deleteByUserId(entity.getId(), dto.getAuthorities());
            }
        }
    }

    private boolean hasSendVerificationCodeSuccess(String username, VerificationType type) {
        if (!username.matches(Variables.REGEX_EMAIL)) {
            throw new DataRuntimeException(CommonStatus.EMAIL_IS_WRONG_FORMAT);
        }

        if (userRepository.existsByEmail(username)) {
            throw new DataRuntimeException(CommonStatus.EMAIL_IS_EXIST);
        }

        Integer sendNum = verificationCodeRepository.countNumberByUsernameAndType(username, type.getValue());
        if (sendNum >= sendLimit) {
            throw new DataRuntimeException(CommonStatus.SENT_EMAIL_TO_YOU);
        }

        VerificationCode forgotPassword = new VerificationCode();
        forgotPassword.setUsername(username);
        forgotPassword.setEmail(username);
        forgotPassword.setCodeSMS(generateUtils.code());
        forgotPassword.setExpired(LocalDateTime.now().plusMinutes(5));
        forgotPassword.setType(type.getValue());

        verificationCodeRepository.save(forgotPassword);

        String body = "code: " + forgotPassword.getCodeSMS();
        emailUtils.send(username, subject, body);
        return true;
    }

    private void validateDto(@NonNull UserReqDto dto) {
        if (dto.getId() == null && dto.getEmployeeId() == null) {
            throw new DataRuntimeException(UserStatus.EMPLOYEE_ID_IS_NULL);
        }

        if (dto.getId() == null && !employeeRepository.existsById(dto.getEmployeeId())) {
            throw new DataRuntimeException(UserStatus.EMPLOYEE_ID_NOT_FOUND);
        }

        if (dto.getId() == null && employeeRepository.hasAccount(dto.getEmployeeId())) {
            throw new DataRuntimeException(UserStatus.EMPLOYEE_HAS_ACCOUNT);
        }

        if (StringUtils.isEmpty(dto.getEmail())) {
            throw new DataRuntimeException(UserStatus.EMAIL_IS_EMPTY);
        }

        if (!dto.getEmail().matches(Variables.REGEX_EMAIL)) {
            throw new DataRuntimeException(UserStatus.EMAIL_IS_WRONG_FORMAT);
        }

        if (userRepository.existsByIdAndEmail(dto.getId(), dto.getEmail())) {
            throw new DataRuntimeException(UserStatus.EMAIL_IS_EXIST);
        }
    }

    private void validateChangePassword(UserDto dto) {
        String userName = dto.getUsername();
        if (!StringUtils.hasText(userName)) {
            throw new DataRuntimeException(UserStatus.USERNAME_IS_EMPTY);
        }

        if (Boolean.FALSE.equals(userRepository.existsByUsername(userName))) {
            throw new DataRuntimeException(CommonStatus.ACCOUNT_NOT_FOUND);
        }

        String codeSMS = dto.getCodeSMS();
        if (!StringUtils.hasText(codeSMS)) {
            throw new DataRuntimeException(CommonStatus.CODE_SMS_IS_EMPTY);
        }

        VerificationCode forgotPassword = verificationCodeRepository.getVerificationCodeByUsernameAndCodeSMS(userName, codeSMS);
        if (null == forgotPassword) {
            throw new DataRuntimeException(CommonStatus.CODE_SMS_IS_EMPTY);
        }

        if (forgotPassword.getExpired().isBefore(LocalDateTime.now())) {
            throw new DataRuntimeException(CommonStatus.CODE_SMS_INVALID);
        }

        String newPassword = dto.getPassword();
        if (!StringUtils.hasText(newPassword)) {
            throw new DataRuntimeException(UserStatus.PASSWORD_IS_EMPTY);
        }

        if (!newPassword.equals(dto.getConfirmPassword())) {
            throw new DataRuntimeException(UserStatus.CONFIRM_PASSWORD_IS_ERROR);
        }
    }

//    private void validateRegister(UserDto dto) {
//        String codeSMS = dto.getCodeSMS();
//        if (!StringUtils.hasText(codeSMS)) {
//            throw new DataRuntimeException(CommonStatus.CODE_SMS_IS_EMPTY);
//        }
//
//        VerificationCode verificationCode = verificationCodeRepository.getVerificationCodeByUsernameAndCodeSMS(dto.getUsername(), codeSMS);
//        if (verificationCode == null) {
//            throw new DataRuntimeException(CommonStatus.CODE_SMS_IS_EMPTY);
//        }
//
//        if (verificationCode.getExpired().isBefore(LocalDateTime.now())) {
//            throw new DataRuntimeException(CommonStatus.CODE_SMS_INVALID);
//        }

//        this.validateDto(dto);
//    }

    private void mapDtoToEntity(@NonNull UserReqDto dto, @NonNull User entity) {
        if (dto.getId() == null) {
            String employeeCode = employeeRepository.getCodeById(dto.getEmployeeId());
            entity.setUsername(employeeCode);
        }
        entity.setEmail(dto.getEmail());
        entity.setActive(dto.isActive());
        if (dto.isGenCode()) {
            String code = generateUtils.code();
            entity.setPassword(bCryptPasswordEncoder.encode(code));
        }

        if (CollectionUtils.isNotEmpty(dto.getRoles())) {
            List<UserRoleConnect> userRoleConnects = new ArrayList<>();
            Set<Role> roles = roleRepository.getByNames(dto.getRoles());
            if (CollectionUtils.isNotEmpty(entity.getRoles())) {
                List<Role> roleOlds = entity.getRoles().stream()
                        .map(UserRoleConnect::getRole).collect(Collectors.toList());
                roleOlds.forEach(roles::remove);
            }

            for (Role role : roles) {
                UserRoleConnect userRoleConnect = new UserRoleConnect();
                userRoleConnect.setRole(role);
                userRoleConnect.setUser(entity);
                userRoleConnects.add(userRoleConnect);
            }
            entity.setRoles(userRoleConnects);
        }

        if (CollectionUtils.isNotEmpty(dto.getAuthorities())) {
            List<UserAuthorityConnect> userAuthorityConnects = new ArrayList<>();
            List<Authority> authorities = authorityRepository.getByNames(dto.getAuthorities());
            if (CollectionUtils.isNotEmpty(entity.getAuthorities())) {
                List<Authority> authorityOlds = entity.getAuthorityEntities().stream()
                        .map(UserAuthorityConnect::getAuthority).collect(Collectors.toList());
                authorityOlds.forEach(authorities::remove);
            }
            for (Authority authority : authorities) {
                UserAuthorityConnect userAuthorityConnect = new UserAuthorityConnect();
                userAuthorityConnect.setAuthority(authority);
                userAuthorityConnect.setUser(entity);
                userAuthorityConnects.add(userAuthorityConnect);
            }
            entity.setAuthorities(userAuthorityConnects);
        }
    }
}
