package com.apilearn.TimesApi.services;

import com.apilearn.TimesApi.dto.BookDTO;
import com.apilearn.TimesApi.dto.CategoryListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final RestTemplate restTemplate;
    @Value("${nyt.api.key}")
    private  String api_key;
    private final String BASE_URL = "https://api.nytimes.com/svc/books/v3";

    @Autowired
    public BookService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public List<BookDTO.BookInfo> getBooksByListName(String listName, String date) {
        String url = BASE_URL + "/lists/" + date + "/" + listName + "?api-key=" + api_key;
        BookDTO response = restTemplate.getForObject(url, BookDTO.class);

        if (response != null && response.results() != null) {
            return response.results().stream()
                    .flatMap(results -> results.bookInfos().stream())
                    .toList(); // Acesse a lista de livros
        } else {
            throw new RuntimeException("Nenhum dado encontrado para a lista: " + listName);
        }
    }

    public List<CategoryListDTO.CategoryList> getAllCategories() {
        String url = BASE_URL + "/lists/names.json?api-key=" + api_key;
        try {
            CategoryListDTO response = restTemplate.getForObject(url, CategoryListDTO.class);
            return response.results();
        } catch(Exception e) {
            throw new RuntimeException("Erro ao buscar categorias: " + e.getMessage());
        }
    }

    public List<BookDTO.BookInfo> getBestSellersByDateRange(String listName, String startDate, String endDate) {
        String url = BASE_URL + "/lists.json?list=" + listName + "&published-date=" + startDate +
                "&end-date=" + endDate + "&api-key=" + api_key;

        try {
            BookDTO response = restTemplate.getForObject(url, BookDTO.class);

            if (response != null && response.results() != null) {
                return response.results().stream()
                        .flatMap(results -> results.bookInfos().stream())
                        .toList();
            } else {
                return List.of(); // Retorna uma lista vazia se não houver resultados
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livros mais vendidos no intervalo: " + e.getMessage(), e);
        }
    }

}
