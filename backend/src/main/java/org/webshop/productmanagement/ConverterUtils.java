package org.webshop.productmanagement;

import org.webshop.productmanagement.entity.Product;
import org.webshop.to.CreateProductTO;
import org.webshop.to.ProductTO;

public class ConverterUtils {

    public static ProductTO convertInTO(final Product product) {
        return ProductTO.builder()
                .id(product.getId())
                .build();
    }

    public static Product convertFromTO(final CreateProductTO productTO) {
        return Product.builder()
                .name(productTO.getName()).build();
    }

}
