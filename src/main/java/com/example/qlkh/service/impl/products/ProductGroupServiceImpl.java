package com.example.qlkh.service.impl.products;

import com.example.qlkh.dto.request.products.ProductGroupReqDto;
import com.example.qlkh.dto.response.products.ProductGroupRespDto;
import com.example.qlkh.dto.request.searchs.SearchDto;
import com.example.qlkh.entity.product.ProductGroup;
import com.example.qlkh.error.status.products.ProductGroupStatus;
import com.example.qlkh.exception.DataRuntimeException;
import com.example.qlkh.repository.products.ProductGroupRepository;
import com.example.qlkh.service.mapper.products.ProductGroupMapper;
import com.example.qlkh.service.products.ProductGroupService;
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
public class ProductGroupServiceImpl implements ProductGroupService {
    private final ProductGroupRepository productGroupRepository;
    private final ProductGroupMapper productGroupMapper;

    @Override
    public ProductGroupRespDto getById(@NonNull Long id) {
        log.info("ProductGroupServiceImpl method getById, id:{}", id);
        ProductGroup entity = productGroupRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(ProductGroupStatus.ID_NOT_FOUND));
        return productGroupMapper.mapEntityToDto(entity);
    }

    @Override
    public Page<ProductGroupRespDto> search(@NonNull SearchDto dto) {
        log.info("ProductGroupServiceImpl method search, dto:{}", EbsConvertUtils.toString(dto));
        List<ProductGroup> groups = productGroupRepository.search(dto);
        List<ProductGroupRespDto> content = groups.stream()
                .map(productGroupMapper::mapEntityToDto).collect(Collectors.toList());

        long total = productGroupRepository.count(dto);

        return new PageImpl<>(content, dto.getPageable(), total);
    }

    @Override
    public ProductGroupRespDto create(@NonNull ProductGroupReqDto dto) {
        // validate
        log.info("ProductGroupServiceImpl method create, data: {}", EbsConvertUtils.toString(dto));
        dto.setId(null);
        ProductGroup entity = new ProductGroup();
        this.mapDtoToEntity(dto, entity);
        entity = productGroupRepository.save(entity);

        return productGroupMapper.mapEntityToDto(entity);
    }

    @Override
    public ProductGroupRespDto update(@NonNull Long id, @NonNull ProductGroupReqDto dto) {
        // validate
        log.info("ProductGroupServiceImpl method update, id:{}, data:{}", id, EbsConvertUtils.toString(dto));
        dto.setId(id);
        ProductGroup entity = productGroupRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(ProductGroupStatus.ID_NOT_FOUND));
        this.mapDtoToEntity(dto, entity);
        entity = productGroupRepository.save(entity);
        return productGroupMapper.mapEntityToDto(entity);
    }

    @Override
    public boolean deleteById(@NonNull Long id) {
        ProductGroup entity = productGroupRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(ProductGroupStatus.ID_NOT_FOUND));
        try {
            productGroupRepository.delete(entity);
            ProductGroupRespDto dataLog = productGroupMapper.mapEntityToDto(entity);
            log.info("ProductGroupServiceImpl method deleteById, id: {}, data: {}", id, EbsConvertUtils.toString(dataLog));
        } catch (Exception e) {
            log.error("ProductGroupServiceImpl method deleteById, id: {}", id, e);
            throw new DataRuntimeException(ProductGroupStatus.HAS_USED);
        }
        return true;
    }

    private void mapDtoToEntity(@NonNull ProductGroupReqDto dto, @NonNull ProductGroup entity) {
        entity.setName(dto.getName());
        entity.setDeleted(dto.isDeleted());
        if (dto.getId() == null) {
            entity.setCode(this.generateCode());
        }
    }

    private String generateCode() {
        String maxCode = productGroupRepository.getMaxCode();
        if (StringUtils.isEmpty(maxCode)) {
            maxCode = "0";
        }
        return String.format("%05d", Integer.parseInt(maxCode) + 1);
    }
}
