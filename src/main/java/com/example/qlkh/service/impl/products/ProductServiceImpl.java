package com.example.qlkh.service.impl.products;

import com.example.qlkh.dto.request.products.ProductDetailReqDto;
import com.example.qlkh.dto.request.products.ProductFileDescriptionReqDto;
import com.example.qlkh.dto.request.products.ProductReqDto;
import com.example.qlkh.dto.request.products.ProductUnitReqDto;
import com.example.qlkh.dto.request.searchs.ProductSearchDto;
import com.example.qlkh.dto.response.products.ProductDetailRespDto;
import com.example.qlkh.dto.response.products.ProductRespDto;
import com.example.qlkh.entity.FileDescription;
import com.example.qlkh.entity.product.*;
import com.example.qlkh.error.status.products.ProductStatus;
import com.example.qlkh.error.status.products.ProductTypeStatus;
import com.example.qlkh.error.status.products.ProductUnitStatus;
import com.example.qlkh.exception.DataRuntimeException;
import com.example.qlkh.repository.products.*;
import com.example.qlkh.service.mapper.products.ProductDetailMapper;
import com.example.qlkh.service.mapper.products.ProductMapper;
import com.example.qlkh.service.products.ProductService;
import com.example.qlkh.utils.EbsConvertUtils;
import com.example.qlkh.utils.EbsFileUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;
    private final ProductUnitRepository productUnitRepository;
    private final ProductUnitConnectRepository productUnitConnectRepository;
    private final ProductGroupRepository productGroupRepository;
    private final ProductGroupConnectRepository productGroupConnectRepository;
    private final ProductFileDescriptionConnectRepository fileDescriptionConnectRepository;
    private final ProductDetailRepository productDetailRepository;
    private final ProductSupplierConnectRepository productSupplierConnectRepository;
    private final SupplierRepository supplierRepository;
    private final ProductMapper productMapper;
    private final ProductDetailMapper productDetailMapper;

    @Override
    public ProductRespDto getById(@NonNull Long id) {
        log.info("ProductServiceImpl method getById, id: {}", id);
        Product entity = productRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(ProductStatus.ID_NOT_FOUND));
        return productMapper.mapEntityToDto(entity);
    }

    @Override
    public Page<ProductRespDto> search(@NonNull ProductSearchDto dto) {
        log.info("ProductServiceImpl method search, condition: {}", EbsConvertUtils.toString(dto));
        List<Product> products = productRepository.search(dto);
        List<ProductRespDto> content = products.stream().map(productMapper::mapEntityToDtoHidden).collect(Collectors.toList());
        long total = productRepository.count(dto);
        return new PageImpl<>(content, dto.getPageable(), total);
    }

    @Override
    public List<ProductRespDto> getBy(@NonNull ProductSearchDto dto) {
        log.info("ProductServiceImpl method getBy, condition: {}", EbsConvertUtils.toString(dto));
        List<Product> products = productRepository.search(dto);
        return products.stream()
                .map(e -> productMapper.mapEntityToDto(e, e.getUnits(), e.getDetails()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDetailRespDto> getDetails(@NonNull Long id) {
        List<ProductDetail> details = productDetailRepository.getByProductId(id);
        return details.stream().map(productDetailMapper::mapEntityToDto).collect(Collectors.toList());
    }

    @Override
    public ProductRespDto create(@NonNull ProductReqDto dto) {
        // validate
        dto.setId(null);
        Product entity = new Product();
        this.mapDtoToEntity(dto, entity);
        entity = productRepository.save(entity);
        ProductRespDto dataResp = productMapper.mapEntityToDto(entity);
        log.info("ProductServiceImpl method create, data: {}", EbsConvertUtils.toString(dataResp));
        return dataResp;
    }

    @Transactional
    @Override
    public ProductRespDto update(@NonNull Long id, @NonNull ProductReqDto dto) {
        // validate
        Product entity = productRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(ProductStatus.ID_NOT_FOUND));
        ProductRespDto dataBeforeUpdate = productMapper.mapEntityToDto(entity);
        log.info("ProductServiceImpl method update [start], data: {}", EbsConvertUtils.toString(dataBeforeUpdate));
        dto.setId(id);
        this.mapDtoToEntity(dto, entity);
        this.deleteUnits(id, dto.getDeleteUnitIds());
        this.deleteGroups(id, dto.getDeleteGroupIds());
        this.deleteImages(id, dto.getDeleteImageIds());
        this.deleteDetails(id, dto.getDeleteDetailIds());
        this.deleteSuppliers(id, dto.getDeleteSupplierIds());
        entity = productRepository.save(entity);
        ProductRespDto dataResp = productMapper.mapEntityToDto(entity);
        log.info("ProductServiceImpl method update [end], data: {}", EbsConvertUtils.toString(dataResp));
        return dataResp;
    }

    @Override
    public boolean deleteById(@NonNull Long id) {
        Product entity = productRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(ProductStatus.ID_NOT_FOUND));
        try {
            ProductRespDto dataLog = productMapper.mapEntityToDto(entity);
            productRepository.delete(entity);
            log.info("ProductServiceImpl method deleteById, id: {}, data: {}", id, EbsConvertUtils.toString(dataLog));
        } catch (Exception e) {
            log.info("ProductServiceImpl method deleteById, id: {}", id, e);
            throw new DataRuntimeException(ProductStatus.HAS_USED);
        }
        return true;
    }


    private void validateDto(@NonNull ProductReqDto dto) {
        // kiểm tra các id có tồn tại không
        // kiểm tra đơn vị quy đổi có trùng không (unitId và ratio)
        // kiểm tra đơn vị quy đổi chỉ có một unit default và chỉ có một unit report
        // kiểm tra trong các đơn vị quy đổi bị xóa có đơn vị nào là default hoặc report không?, nếu có thì những đơn vị thêm mới hoặc update phài có để thay thế
    }

    private void mapDtoToEntity(@NonNull ProductReqDto dto, @NonNull Product entity) {
        if (dto.getId() == null) {
            entity.setCode(this.generateCode());
        }
        entity.setName(dto.getName());
        entity.setNamePrint(dto.getNamePrint());
        entity.setNote(dto.getNote());
        entity.setDeleted(dto.isDeleted());

        ProductType type = productTypeRepository.findById(dto.getTypeId())
                .orElseThrow(() -> new DataRuntimeException(ProductTypeStatus.ID_NOT_FOUND));
        entity.setType(type);

        entity.setDetails(this.createDetails(entity, dto.getAddDetails()));
        entity.setUnits(this.createTargetUnits(entity, dto.getAddUnits()));
        entity.setImages(this.createImages(entity, dto.getAddImages()));
        entity.setGroups(this.createGroups(entity, dto.getAddGroupIds()));
        entity.setSuppliers(this.createSuppliers(entity, dto.getAddSupplierIds()));
    }

    private List<ProductDetail> createDetails(@NonNull Product product, List<ProductDetailReqDto> detailDtos) {
        if (CollectionUtils.isEmpty(detailDtos)) {
            return Collections.emptyList();
        }
        List<ProductDetail> details = new ArrayList<>();
        for (ProductDetailReqDto dto : detailDtos) {
            details.add(this.createDetail(product, dto));
        }
        return details;
    }

    private ProductDetail createDetail(@NonNull Product product, @NonNull ProductDetailReqDto dto) {
        ProductDetail detail = new ProductDetail();
        detail.setProduct(product);
        detail.setSize(dto.getSize());
        detail.setQuality(dto.getQuality());
        detail.setPrice(dto.getPrice());
        return detail;
    }

    private List<ProductFileDescriptionConnect> createImages(@NonNull Product product, List<ProductFileDescriptionReqDto> imageDtos) {
        if (CollectionUtils.isEmpty(imageDtos)) {
            return Collections.emptyList();
        }

        List<ProductFileDescriptionConnect> images = new ArrayList<>();
        for (ProductFileDescriptionReqDto imageDto : imageDtos) {
            images.add(this.createImage(product, imageDto));
        }

        return images;
    }

    private ProductFileDescriptionConnect createImage(@NonNull Product product, @NonNull ProductFileDescriptionReqDto imageDto) {
        ProductFileDescriptionConnect imageConnect = new ProductFileDescriptionConnect();
        imageConnect.setProduct(product);
        imageConnect.setOrderBy(imageDto.getSort());
        FileDescription image = EbsFileUtils.saveFile(imageDto.getFile());
        imageConnect.setFile(image);
        return imageConnect;
    }

    private List<ProductGroupConnect> createGroups(@NonNull Product product, List<Long> dtoIds) {
        if (CollectionUtils.isEmpty(dtoIds)) {
            return Collections.emptyList();
        }

        List<ProductGroup> groups = productGroupRepository.getByIds(dtoIds);

        return groups.stream().map(group -> {
            ProductGroupConnect groupConnect = new ProductGroupConnect();
            groupConnect.setGroup(group);
            groupConnect.setProduct(product);
            return groupConnect;
        }).collect(Collectors.toList());
    }

    private List<ProductUnitConnect> createTargetUnits(@NonNull Product product, List<ProductUnitReqDto> dtos) {
        if (CollectionUtils.isEmpty(dtos)) {
            return Collections.emptyList();
        }
        List<ProductUnitConnect> targetUnits = new ArrayList<>();
        for (ProductUnitReqDto dto : dtos) {
            targetUnits.add(this.createProductUnit(product, dto));
        }
        return targetUnits;
    }

    /**
     * cho thêm mới và chỉ cho updata trạng thái default và report
     */
    private ProductUnitConnect createProductUnit(@NonNull Product product, @NonNull ProductUnitReqDto dto) {
        if (product.getId() == null || dto.getId() == null) {
            ProductUnitConnect unitConnect = new ProductUnitConnect();

            ProductUnit unit = productUnitRepository.findById(dto.getUnitId())
                    .orElseThrow(() -> new DataRuntimeException(ProductUnitStatus.ID_NOT_FOUND));
            unitConnect.setUnit(unit);
            unitConnect.setProduct(product);
            unitConnect.setRatio(dto.getRatio());
            unitConnect.setDefault(dto.isDefault());
            unitConnect.setUseReport(dto.isUseReport());

            return unitConnect;
        }

        ProductUnitConnect unitConnect = productUnitConnectRepository.getByIdAndProductId(dto.getId(), product.getId());
        unitConnect.setDefault(dto.isDefault());
        unitConnect.setUseReport(dto.isUseReport());

        return unitConnect;
    }


    private List<ProductSupplierConnect> createSuppliers(@NonNull Product product, List<Long> supplierIds) {
        if (CollectionUtils.isEmpty(supplierIds)) {
            return Collections.emptyList();
        }

        List<Supplier> suppliers = supplierRepository.getByIds(supplierIds);

        return suppliers.stream().map(supplier -> {
            ProductSupplierConnect supplierConnect = new ProductSupplierConnect();
            supplierConnect.setSupplier(supplier);
            supplierConnect.setProduct(product);
            return supplierConnect;
        }).collect(Collectors.toList());
    }

    private void deleteGroups(@NonNull Long productId, List<Long> groupIds) {
        if (CollectionUtils.isEmpty(groupIds)) {
            return;
        }

        productGroupConnectRepository.deleteByProductIdAndGroupIds(productId, groupIds);
    }

    private void deleteUnits(@NonNull Long productId, List<Long> unitIds) {
        if (CollectionUtils.isEmpty(unitIds)) {
            return;
        }

        // cần kiểm tra xem đơn vị có được dùng không để đặt trạng thái deleted
        // ver 1: không cho khôi phục
        // ver 2: cho khôi phục: cần xác định lại trạng thái default, report và ratio

        productUnitConnectRepository.deleteByIdAndProductIds(productId, unitIds);
    }

    private void deleteImages(@NonNull Long productId, List<Long> imageIds) {
        if (CollectionUtils.isEmpty(imageIds)) {
            return;
        }

        for (Long imageId : imageIds) {
            this.deleteImage(imageId, productId);
        }
    }

    private void deleteImage(@NonNull Long connectId, @NonNull Long productId) {
        ProductFileDescriptionConnect imageConnect = fileDescriptionConnectRepository.getByIdAndFileId(connectId, productId);

        if (imageConnect != null) {
            EbsFileUtils.delete(imageConnect.getFile());
            fileDescriptionConnectRepository.delete(imageConnect);
        }
    }

    private void deleteDetails(@NonNull Long productId, List<Long> detailIds) {
        if (CollectionUtils.isEmpty(detailIds)) {
            return;
        }

        // cần kiểm tra xem đơn vị có được dùng không để đặt trạng thái deleted

        productDetailRepository.deleteByProductIdAndDetailIds(productId, detailIds);
    }

    private void deleteSuppliers(@NonNull Long productId, List<Long> supplierIds) {
        if (CollectionUtils.isEmpty(supplierIds)) {
            return;
        }

        productSupplierConnectRepository.deleteByProductIdAndSupplierIds(productId, supplierIds);
    }

    private String generateCode() {
        String maxCode = productRepository.getMaxCode();
        if (StringUtils.isEmpty(maxCode)) {
            maxCode = "0";
        }
        return String.format("%05d", Integer.parseInt(maxCode) + 1);
    }
}
