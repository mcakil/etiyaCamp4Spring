package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.ProductService;
import com.etiya.northwind.business.requests.productRequests.CreateProductRequest;
import com.etiya.northwind.business.requests.productRequests.UpdateProductRequest;
import com.etiya.northwind.business.responses.PageDataResponse;
import com.etiya.northwind.business.responses.products.ProductListResponse;
import com.etiya.northwind.core.exceptions.BusinessException;
import com.etiya.northwind.core.mapping.ModelMapperService;
import com.etiya.northwind.core.results.DataResult;
import com.etiya.northwind.core.results.Result;
import com.etiya.northwind.core.results.SuccessDataResult;
import com.etiya.northwind.core.results.SuccessResult;
import com.etiya.northwind.dataAccess.abstracts.ProductRepository;
import com.etiya.northwind.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.stream.Collectors;

@Service
public class ProductManager implements ProductService {
    private ProductRepository productRepository;
    private ModelMapperService modelMapperService;

    @Autowired
    public ProductManager(ProductRepository productRepository, ModelMapperService modelMapperService) {
        this.productRepository = productRepository;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public Result add(CreateProductRequest createProductRequest) {
        checkIfProductNameExists(createProductRequest.getProductName());
        checkIfCategoryLimitExceeded(createProductRequest.getCategoryId());
        Product product = this.modelMapperService.forRequest().map(createProductRequest, Product.class);
        productRepository.save(product);
        return new SuccessResult("Added");
    }

    @Override
    public Result update(UpdateProductRequest updateProductRequest) {
        Product product = this.modelMapperService.forRequest().map(updateProductRequest, Product.class);
        productRepository.save(product);
        return new SuccessResult("Updated");
    }

    @Override
    public Result delete(int productId) {
        checkProductExists(productId);
        this.productRepository.deleteById(productId);
        return new SuccessResult("Deleted successfully.");
    }

    @Override
    public DataResult<List<ProductListResponse>> getAll() {
        List<Product> result = this.productRepository.findAll();
        List<ProductListResponse> response = result.stream()
                .map(product -> this.modelMapperService.forResponse()
                        .map(product, ProductListResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(response);
    }

    @Override
    public DataResult<ProductListResponse> getById(int productId) {
        Product product = this.productRepository.findById(productId).orElseThrow(() -> new BusinessException("Product not found."));
        ProductListResponse response = this.modelMapperService.forResponse().map(product, ProductListResponse.class);

        return new SuccessDataResult<>(response);
    }

    @Override
    public DataResult<PageDataResponse<ProductListResponse>> getByPage(int pageNumber, int productAmountInPage) {
        Pageable pageable = PageRequest.of(pageNumber-1,productAmountInPage);
        return new SuccessDataResult<>(getPageDataResponse(pageNumber, pageable));
    }

    @Override
    public DataResult<PageDataResponse<ProductListResponse>> getByPageWithSorting(int pageNumber, int productAmountInPage, String fieldName, boolean isAsc) {
        Pageable pageable;
        if (isAsc){
            pageable = PageRequest.of(pageNumber-1,productAmountInPage, Sort.by(fieldName).ascending());
        }else {
            pageable = PageRequest.of(pageNumber-1,productAmountInPage, Sort.by(fieldName).descending());
        }
        return new SuccessDataResult<>(getPageDataResponse(pageNumber, pageable));
    }

    private PageDataResponse<ProductListResponse> getPageDataResponse(int pageNumber, Pageable pageable) {
        Page<Product> pages = this.productRepository.findAllProducts(pageable);
        List<ProductListResponse> response =
                pages.getContent().stream().map(product -> this.modelMapperService.forResponse().map(product, ProductListResponse.class)).collect(Collectors.toList());

        return new PageDataResponse<ProductListResponse>(response, pages.getTotalPages(), pages.getTotalElements(), pageNumber);
    }

    private void checkIfCategoryLimitExceeded(int categoryId) {
        if (productRepository.getCategoryProducts(categoryId).size() >= 15) {
            throw new BusinessException("Category limit exceeded.");
        }
    }

    private void checkProductExists(int productId) {
        if (!productRepository.existsById(productId)){
            throw new BusinessException("Product does not exist.");
        }
    }

    private void checkIfProductNameExists(String productName) {
        if (this.productRepository.isProductNameExists(productName)){
            throw new BusinessException("Product name already exists in the database.");
        }
    }

}
