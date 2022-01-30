package org.webshop.productmanagement.resourceaccess;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.webshop.common.utils.CollectionUtils;
import org.webshop.common.utils.QueryDSLUtils;
import org.webshop.productmanagement.entity.Product;
import org.webshop.productmanagement.entity.QProduct;
import org.webshop.to.ProductFilterTO;

@RequiredArgsConstructor
@Repository
public class ReadProductRepository {

    private final QueryDSLUtils queryDSLUtils;
    private final JPAQueryFactory queryFactory;

    public Page<Product> findAll(final ProductFilterTO filter, final Pageable pageable) {
        final QProduct product = QProduct.product;
        final JPQLQuery<Product> jpaQuery = queryFactory.selectFrom(product);
        applyFilters(jpaQuery, product, filter);
        return queryDSLUtils.getPaginated(jpaQuery, pageable, Product.class);
    }

    private void applyFilters(final JPQLQuery<Product> query, final QProduct product, final ProductFilterTO filter) {
        if (CollectionUtils.isNotEmpty(filter.getIds())) {
            query.where(product.id.in(filter.getIds()));
        }
    }

}
