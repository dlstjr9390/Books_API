package com.example.aladin.Service;

import com.example.aladin.Dto.BooksDto;
import com.example.aladin.Entity.Books;
import com.example.aladin.Repository.BooksRepository;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@AllArgsConstructor

public class ProductService {

  private final RestTemplate restTemplate;
  private final BooksRepository booksRepository;

  public List<BooksDto> searchQuery(String query){

    String API_KEY = "ttbpsu022028001";
    String BASE_URL = "http://www.aladin.co.kr/ttb/api/ItemList.aspx";

    URI uri = UriComponentsBuilder
        .fromUriString(BASE_URL)
        .queryParam("ttbkey", API_KEY)
        .queryParam(query)
        .build()
        .toUri();

    RequestEntity<Void> requestEntity = RequestEntity
        .get(uri)
        .build();

    ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

    List<BooksDto> booksDtoList = fromJSONtoItems(responseEntity.getBody());

    for(BooksDto booksDto : booksDtoList){
      insertBooks(booksDto);
    }

    return booksDtoList;
  }

  public void insertBooks(BooksDto booksDto){

    Books books = new Books(booksDto);
    booksRepository.save(books);

  }

  public List<BooksDto> fromJSONtoItems(String responseEntity){
    JSONObject jsonObject = new JSONObject(responseEntity);
    JSONArray books = jsonObject.getJSONArray("item");
    List<BooksDto> booksDtoList = new ArrayList<>();

    for(Object book : books) {
      BooksDto booksDto = new BooksDto((JSONObject) book);
      booksDtoList.add(booksDto);
    }

    return booksDtoList;
  }

}
