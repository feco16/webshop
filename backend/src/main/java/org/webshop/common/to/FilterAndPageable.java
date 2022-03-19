package org.webshop.common.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterAndPageable<T> {

    private T filter;
    private Integer page = 0;
    private Integer size = Integer.MAX_VALUE;

    public FilterAndPageable(final T filter) {
        this.filter = filter;
    }
}
