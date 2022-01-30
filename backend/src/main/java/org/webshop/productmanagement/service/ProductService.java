package org.webshop.productmanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webshop.common.to.IdResponse;
import org.webshop.productmanagement.ConverterUtils;
import org.webshop.productmanagement.entity.Product;
import org.webshop.to.CreateProductTO;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final WriteProductRepository productRepository;

    public IdResponse createProduct(final CreateProductTO productTO) {
        final Product product = ConverterUtils.convertFromTO(productTO);
        final Product createdProduct = productRepository.save(product);
        return new IdResponse(createdProduct.getId());

    }
}
