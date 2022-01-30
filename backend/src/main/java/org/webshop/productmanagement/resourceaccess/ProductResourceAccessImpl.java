package org.webshop.productmanagement.resourceaccess;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webshop.productmanagement.ConverterUtils;
import org.webshop.to.ProductFilterTO;
import org.webshop.to.ProductTO;

@RequiredArgsConstructor
@Service
public class ProductResourceAccessImpl implements ProductResourceAccessInternal {

    private final ReadProductRepository productRepository;

    @Override
    public Page<ProductTO> getAll(final ProductFilterTO filter, final Pageable pageable) {
        return productRepository.findAll(filter, pageable).map(ConverterUtils::convertInTO);
    }
}
