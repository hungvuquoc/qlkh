package com.example.qlkh.service.impl.products;

import com.example.qlkh.dto.request.products.ProductUnitReqDto;
import com.example.qlkh.dto.response.products.ProductUnitRespDto;
import com.example.qlkh.dto.request.searchs.SearchDto;
import com.example.qlkh.entity.product.ProductUnit;
import com.example.qlkh.error.status.products.ProductUnitStatus;
import com.example.qlkh.exception.DataRuntimeException;
import com.example.qlkh.repository.products.ProductUnitRepository;
import com.example.qlkh.service.mapper.products.ProductUnitMapper;
import com.example.qlkh.service.products.ProductUnitService;
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
public class ProductUnitServiceImpl implements ProductUnitService {
    private final ProductUnitRepository productUnitRepository;
    private final ProductUnitMapper productUnitMapper;

    @Override
    public ProductUnitRespDto getById(@NonNull Long id) {
        log.info("ProductUnitServiceImpl method getById, id: {}", id);
        ProductUnit entity = productUnitRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(ProductUnitStatus.ID_NOT_FOUND));
        return productUnitMapper.mapEntityToDto(entity);
    }

    @Override
    public ProductUnitRespDto getDefault(@NonNull Long productId) {
        return productUnitMapper.mapEntityToDto(productUnitRepository.getDefault(productId));
    }

    @Override
    public Page<ProductUnitRespDto> search(SearchDto dto) {
        log.info("ProductUnitServiceImpl method search, condition: {}", EbsConvertUtils.toString(dto));
        List<ProductUnit> units = productUnitRepository.search(dto);
        List<ProductUnitRespDto> content = units.stream()
                .map(productUnitMapper::mapEntityToDto).collect(Collectors.toList());
        long total = productUnitRepository.count(dto);
        return new PageImpl<>(content, dto.getPageable(), total);
    }

    @Override
    public ProductUnitRespDto create(@NonNull ProductUnitReqDto dto) {
        log.info("ProductUnitServiceImpl method create, data: {}", EbsConvertUtils.toString(dto));
        // validate
        dto.setId(null);
        ProductUnit entity = new ProductUnit();
        this.mapDtoToEntity(dto, entity);
        entity = productUnitRepository.save(entity);
        return productUnitMapper.mapEntityToDto(entity);
    }

    @Override
    public ProductUnitRespDto update(@NonNull Long id, @NonNull ProductUnitReqDto dto) {
        log.info("ProductUnitServiceImpl method update, id: {}, data: {}", id, EbsConvertUtils.toString(dto));
        //validate
        dto.setId(id);
        ProductUnit entity = productUnitRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(ProductUnitStatus.ID_NOT_FOUND));
        this.mapDtoToEntity(dto, entity);
        entity = productUnitRepository.save(entity);
        return productUnitMapper.mapEntityToDto(entity);
    }

    @Override
    public boolean deleteById(@NonNull Long id) {
        ProductUnit entity = productUnitRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(ProductUnitStatus.ID_NOT_FOUND));
        try {
            productUnitRepository.delete(entity);
            ProductUnitRespDto dataLog = productUnitMapper.mapEntityToDto(entity);
            log.info("ProductUnitServiceImpl method deleteById, id: {}, data: {}", id, EbsConvertUtils.toString(dataLog));
        } catch (Exception e) {
            log.error("ProductUnitServiceImpl method deleteById, id: {}", id, e);
            throw new DataRuntimeException(ProductUnitStatus.HAS_USED);
        }
        return true;
    }

    private void mapDtoToEntity(@NonNull ProductUnitReqDto dto, @NonNull ProductUnit entity) {
        entity.setName(dto.getName());
        entity.setDeleted(dto.isDeleted());
        if (dto.getId() == null) {
            entity.setCode(this.generateCode());
        }
    }

    private String generateCode() {
        String maxCode = productUnitRepository.getMaxCode();
        if (StringUtils.isEmpty(maxCode)) {
            maxCode = "0";
        }
        return String.format("%05d", Integer.parseInt(maxCode) + 1);
    }
}
