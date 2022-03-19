package org.webshop.productmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webshop.common.to.IdResponse;
import org.webshop.common.to.FilterAndPageable;
import org.webshop.productmanagement.resourceaccess.ProductResourceAccessImpl;
import org.webshop.productmanagement.service.ProductService;
import org.webshop.to.CreateProductTO;
import org.webshop.to.ProductFilterTO;
import org.webshop.to.ProductTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductResourceAccessImpl productResourceAccess;
    private final ProductService productService;

    @PostMapping("/filter")
    public Page<ProductTO> getProducts(@Valid @NotNull @RequestBody final FilterAndPageable<ProductFilterTO> body) {
        return productResourceAccess.getAll(body.getFilter(), PageRequest.of(body.getPage(), body.getSize()));
    }

    @PostMapping
    public IdResponse createProduct(final CreateProductTO createProductTO) {
        return productService.createProduct(createProductTO);
    }
}
