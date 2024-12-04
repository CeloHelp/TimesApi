package com.apilearn.TimesApi.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ReviewDTO(@JsonAlias ("results") List<Review> results) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Review(
            @JsonAlias("book_title") String bookTitle,
            @JsonAlias("book_author") String bookAuthor,
            @JsonAlias("summary") String summary,
            @JsonAlias("url") String url
    ) {}
}
