package org.webshop.common.utils;

import org.springframework.lang.Nullable;

import java.util.Collection;

public class CollectionUtils {

    public static boolean isNotEmpty(@Nullable final Collection<?> collection) {
        return !org.springframework.util.CollectionUtils.isEmpty(collection);
    }
}
