package com.example.qlkh.service;

import com.example.qlkh.dto.EbsPrincipal;
import com.example.qlkh.dto.EbsWarehouse;
import com.example.qlkh.dto.TokenDto;
import com.example.qlkh.dto.UserDto;
import com.example.qlkh.dto.request.UserReqDto;
import com.example.qlkh.dto.request.searchs.SearchDto;
import com.example.qlkh.dto.response.UserRespDto;
import com.example.qlkh.dto.response.projection.UserProjectionDto;
import com.example.qlkh.entity.User;
import lombok.NonNull;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserService {
    Map<Long, EbsWarehouse> getWarehouseByUserId(@NonNull Long id);

    User getUserByUsername(String username);

    EbsPrincipal getInfo();

    UserRespDto getById(@NonNull Long id);

    Page<UserProjectionDto> search(@NonNull SearchDto dto);

    UserRespDto save(UserReqDto dto);

//    boolean createVerificationToChangePassword(String username);

//    boolean createVerificationToRegister(String username);

//    TokenDto register(UserRespDto dto);

    TokenDto refreshToken(String refreshToken);

    void logout(HttpServletRequest request);

    UserRespDto update(@NonNull Long id, @NonNull UserReqDto dto);

    boolean changePassword(UserDto dto);

    boolean deleteById(@NonNull Long id);

    // System
    void increaseFailedAttempts(String username, byte newFailAttempts);

    void temporaryLock(String username, int coefficient);

    boolean permanentLock(String username);
}
