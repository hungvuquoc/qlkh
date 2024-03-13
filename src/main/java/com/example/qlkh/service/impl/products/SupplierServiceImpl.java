package com.example.qlkh.service.impl.products;

import com.example.qlkh.dto.request.products.SupplierReqDto;
import com.example.qlkh.dto.response.products.SupplierRespDto;
import com.example.qlkh.dto.request.searchs.SearchDto;
import com.example.qlkh.entity.product.Supplier;
import com.example.qlkh.error.status.products.SupplierStatus;
import com.example.qlkh.exception.DataRuntimeException;
import com.example.qlkh.repository.products.SupplierRepository;
import com.example.qlkh.service.mapper.products.SupplierMapper;
import com.example.qlkh.service.products.SupplierService;
import com.example.qlkh.utils.EbsConvertUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    @Override
    public SupplierRespDto getById(@NonNull Long id) {
        log.info("SupplierServiceImpl method getById, id: {}", id);
        Supplier entity = supplierRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(SupplierStatus.ID_NOT_FOUND));
        return supplierMapper.mapEntityToDto(entity);
    }

    @Override
    public Page<SupplierRespDto> search(SearchDto dto) {
        log.info("SupplierServiceImpl method search, condition: {}", EbsConvertUtils.toString(dto));
        List<Supplier> suppliers = supplierRepository.search(dto);
        List<SupplierRespDto> content = suppliers.stream()
                .map(supplierMapper::mapEntityToDto).collect(Collectors.toList());
        long total = supplierRepository.count(dto);
        return new PageImpl<>(content, dto.getPageable(), total);
    }

    @Override
    public SupplierRespDto create(@NonNull SupplierReqDto dto) {
        log.info("SupplierServiceImpl method create, data: {}", EbsConvertUtils.toString(dto));
        // validate
        dto.setId(null);
        Supplier entity = new Supplier();
        this.mapDtoToEntity(dto, entity);
        entity = supplierRepository.save(entity);
        return supplierMapper.mapEntityToDto(entity);
    }

    @Override
    public SupplierRespDto update(@NonNull Long id, @NonNull SupplierReqDto dto) {
        log.info("SupplierServiceImpl method update, id: {}, data: {}", id, EbsConvertUtils.toString(dto));
        // validate
        Supplier entity = supplierRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(SupplierStatus.ID_NOT_FOUND));
        dto.setId(id);
        this.mapDtoToEntity(dto, entity);
        entity = supplierRepository.save(entity);
        return supplierMapper.mapEntityToDto(entity);
    }

    @Override
    public boolean deleteById(@NonNull Long id) {
        Supplier entity = supplierRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(SupplierStatus.ID_NOT_FOUND));
        try {
            supplierRepository.delete(entity);
            SupplierRespDto dataLog = supplierMapper.mapEntityToDto(entity);
            log.info("SupplierServiceImpl method deleteById, id: {}, data: {}", id, EbsConvertUtils.toString(dataLog));
        } catch (Exception e) {
            log.info("SupplierServiceImpl method deleteById, id: {}", id, e);
            throw new DataRuntimeException(SupplierStatus.HAS_USED);
        }
        return true;
    }

    private void mapDtoToEntity(@NonNull SupplierReqDto dto, @NonNull Supplier entity) {
        entity.setName(dto.getName());
        entity.setDeleted(dto.isDeleted());
        if (dto.getId() == null) {
            entity.setCode(this.generateCode());
        }
    }

    private String generateCode() {
        String maxCode = supplierRepository.getMaxCode();
        if (StringUtils.isEmpty(maxCode)) {
            maxCode = "0";
        }
        return String.format("%05d", Integer.parseInt(maxCode) + 1);
    }
}
