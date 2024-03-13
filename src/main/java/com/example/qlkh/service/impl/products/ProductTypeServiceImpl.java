package com.example.qlkh.service.impl.products;

import com.example.qlkh.dto.request.products.ProductTypeReqDto;
import com.example.qlkh.dto.response.products.ProductTypeRespDto;
import com.example.qlkh.dto.request.searchs.SearchDto;
import com.example.qlkh.entity.product.ProductType;
import com.example.qlkh.error.status.products.ProductTypeStatus;
import com.example.qlkh.exception.DataRuntimeException;
import com.example.qlkh.repository.products.ProductTypeRepository;
import com.example.qlkh.service.mapper.products.ProductTypeMapper;
import com.example.qlkh.service.products.ProductTypeService;
import com.example.qlkh.utils.EbsConvertUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductTypeServiceImpl implements ProductTypeService {
    private final ProductTypeRepository productTypeRepository;
    private final ProductTypeMapper productTypeMapper;

    @Override
    public ProductTypeRespDto getBy(@NonNull Long id) {
        log.info("ProductTypeServiceImpl method getBy, id: {}", id);
        ProductType entity = productTypeRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(ProductTypeStatus.ID_NOT_FOUND));
        return productTypeMapper.mapEntityToDto(entity);
    }

    @Override
    public Page<ProductTypeRespDto> search(@NonNull SearchDto dto) {
        log.info("ProductTypeServiceImpl method search, data: {}", EbsConvertUtils.toString(dto));
        List<ProductType> types = productTypeRepository.search(dto);
        List<ProductTypeRespDto> content = types.stream()
                .map(productTypeMapper::mapEntityToDto).collect(Collectors.toList());
        long total = productTypeRepository.count(dto);
        return new PageImpl<>(content, dto.getPageable(), total);
    }

    @Override
    public ProductTypeRespDto create(@NonNull ProductTypeReqDto dto) {
        log.info("ProductTypeServiceImpl method create, data: {}", EbsConvertUtils.toString(dto));
        // validate
        dto.setId(null);
        ProductType entity = new ProductType();
        this.mapDtoToEntity(dto, entity);
        entity = productTypeRepository.save(entity);
        dto.setId(entity.getId());
        return productTypeMapper.mapEntityToDto(entity);
    }

    @Override
    public ProductTypeRespDto update(@NonNull Long id, @NonNull ProductTypeReqDto dto) {
        log.info("ProductTypeServiceImpl method update, data: {}", EbsConvertUtils.toString(dto));
        // validate
        ProductType entity = productTypeRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(ProductTypeStatus.ID_NOT_FOUND));
        dto.setId(id);
        this.mapDtoToEntity(dto, entity);
        entity = productTypeRepository.save(entity);
        return productTypeMapper.mapEntityToDto(entity);
    }

    @Override
    public Boolean delete(@NonNull Long id) {
        if (!productTypeRepository.existsById(id)) {
            throw new DataRuntimeException(ProductTypeStatus.ID_NOT_EXISTS);
        }

        try {
            productTypeRepository.deleteById(id);
        } catch (Exception e) {
            log.error("ProductTypeServiceImpl method deleteById, id: {}", id, e);
            throw new DataRuntimeException(ProductTypeStatus.HAS_USED);
        }
        log.info("ProductTypeServiceImpl method deleteById, id: {}", id);
        return true;
    }

    private void mapDtoToEntity(@NonNull ProductTypeReqDto dto, @NonNull ProductType entity) {
        entity.setName(dto.getName());
        entity.setDeleted(dto.isDeleted());
        if (dto.getId() == null) {
            entity.setCode(this.generateTypeCode());
        }
    }

    public String generateTypeCode() {
        String maxCode = productTypeRepository.getMaxCode();
        if (StringUtils.isEmpty(maxCode)) {
            maxCode = "0";
        }
        return String.format("%05d", Integer.parseInt(maxCode) + 1);
    }
}
