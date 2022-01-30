package org.webshop.productmanagement.resourceaccess;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.webshop.to.ProductFilterTO;
import org.webshop.to.ProductTO;

public interface ProductResourceAccess {

    Page<ProductTO> getAll(ProductFilterTO filter, Pageable pageable);

}
