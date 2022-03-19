package org.webshop.common.to;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

public class RestPageImpl<T> extends PageImpl<T> {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public RestPageImpl(@JsonProperty("content") final List<T> content,
                        @JsonProperty("number") final int number,
                        @JsonProperty("size") final int size,
                        @JsonProperty("totalElements") final Long totalElements,
                        @JsonProperty("pageable") final JsonNode pageable,
                        @JsonProperty("last") final boolean last,
                        @JsonProperty("totalPages") final int totalPages,
                        @JsonProperty("sort") final JsonNode sort,
                        @JsonProperty("first") final boolean first,
                        @JsonProperty("numberOfElements") final int numberOfElements) {

        super(content, PageRequest.of(number, size), totalElements);
    }

    public RestPageImpl() {
        super(new ArrayList<>());
    }
}