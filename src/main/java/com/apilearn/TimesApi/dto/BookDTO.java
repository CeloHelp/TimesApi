package com.apilearn.TimesApi.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public record BookDTO(
        @JsonAlias("results") Results results)
 { @JsonIgnoreProperties(ignoreUnknown = true)
 public record Results(
         @JsonAlias("books") List<BookInfo> bookInfos
 ){}

     @JsonIgnoreProperties(ignoreUnknown = true)
     public record BookInfo(
             @JsonAlias("title") String title,
             @JsonAlias("author") String author,
             @JsonAlias("description") String description,
             @JsonAlias("publisher") String publisher,
             @JsonAlias("imageUrl") String bookImage,
             @JsonAlias("buyUrl") String amazonProductUrl,
             @JsonAlias("isbn13") String isbn
     ){}
}
