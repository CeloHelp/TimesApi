package com.apilearn.TimesApi.services;

import com.apilearn.TimesApi.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
            return response.results().bookInfos(); // Acesse a lista de livros
        } else {
            throw new RuntimeException("Nenhum dado encontrado para a lista: " + listName);
        }
    }
}
