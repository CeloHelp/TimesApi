package com.apilearn.TimesApi.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CategoryListDTO(@JsonAlias("results") List<CategoryList> results) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record CategoryList(
            @JsonProperty("list_name") String listName,
            @JsonProperty("display_name") String displayName,
            @JsonProperty("oldest_published_date") String oldestPublishedDate,
            @JsonProperty("newest_published_date") String newestPublishedDate,
            @JsonProperty("updated") String updated
    ) {}
}
