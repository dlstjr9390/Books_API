package com.example.aladin.Service;

import com.example.aladin.Dto.BooksDto;
import com.example.aladin.Dto.ProductDto;
import com.example.aladin.Entity.Books;
import com.example.aladin.Entity.Product;
import com.example.aladin.Mapper.BooksMapper;
import com.example.aladin.Repository.BooksRepository;
import com.example.aladin.Repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Table;
import java.awt.print.Book;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

  private final RestTemplate restTemplate;
  private final BooksRepository booksRepository;
  private final BooksMapper booksMapper;
  private final ProductRepository productRepository;



  public int[] searchQuery(String query){

    String API_KEY = "ttbpsu022028001";
    String BASE_URL = "http://www.aladin.co.kr/ttb/api/ItemList.aspx";
    int booksCount = 0;
    int categoryCount = 0;

    int[] categories = {1230,90452,53471,53489,53513,53511,53509,53510,53512,53476,53495,53493,53491,53492,53494,53490,53474,53478,53480,53485,53500,53502,53503,53501,53473,53484,53479,53472,53482,53497,53496,53499,53498,53487,53483,53477,53475,53481,53486,90456,90455,53488,53507,53504,53506,53508,53505,55890,53516,53537,53538,53539,53536,53521,53562,55182,53558,53719,53560,53561,53559,53557,53556,53805,53522,53524,53532,53568,53570,53571,53569,53525,54711,54709,54710,54708,161753,53528,53514,53520,53529,53526,53530,53534,53523,147647,53527,53567,53566,53565,53564,53533,53573,53574,53572,140262,53517};

    for(int categoryId : categories){
      URI uri = UriComponentsBuilder
          .fromUriString(BASE_URL)
          .queryParam("ttbkey", API_KEY)
          .queryParam(query)
          .queryParam("categoryId", categoryId)
          .build()
          .toUri();

      RequestEntity<Void> requestEntity = RequestEntity
          .get(uri)
          .build();

      ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

      List<BooksDto> booksDtoList = fromJSONtoBooks(responseEntity.getBody());

      for(BooksDto booksDto : booksDtoList){
        insertBooks(booksDto);
        booksCount++;
      }

      categoryCount++;
    }
    return new int[]{booksCount,categoryCount};
  }

  public void insertBooks(BooksDto booksDto){

    Books books = new Books(booksDto);
    booksRepository.save(books);

  }

  public List<BooksDto> fromJSONtoBooks(String responseEntity){
    JSONObject jsonObject = new JSONObject(responseEntity);
    JSONArray books = jsonObject.getJSONArray("item");
    List<BooksDto> booksDtoList = new ArrayList<>();

    for(Object book : books) {
      BooksDto booksDto = new BooksDto((JSONObject) book);
      booksDtoList.add(booksDto);
    }

    return booksDtoList;
  }

  public List<BooksDto> searchBooks (String type, String content){

      List<Books> booksList = booksRepository.findBooks(type,content);
      List<BooksDto> booksDtoList = new ArrayList<>();

      for(Books book : booksList){
        BooksDto booksDto = new BooksDto(book);
        booksDtoList.add(booksDto);
      }

      return booksDtoList;

  }


  public List<BooksDto> searchBooksByMybatis (String type, String content){

    List<Books> booksList = booksMapper.searchBooks(type,content);
    List<BooksDto> booksDtoList = new ArrayList<>();

    for(Books book : booksList){
      BooksDto booksDto = new BooksDto(book);
      booksDtoList.add(booksDto);
    }

    return booksDtoList;

  }

  @Transactional
  public String deleteBooks(List<Integer> booksId) {
    String idxList = booksId.toString()
        .replace("[","")
        .replace("]","");
    String[] arrIdxStr = idxList.split(",");
    ArrayList<String> deletedBooksTitles = new ArrayList<>();
    try {
      for (String idxStr : arrIdxStr) {
        Long idx = Long.valueOf(idxStr.trim());
        Books books = booksRepository.findBooksByBooksId(idx);
        if (books == null) {
          throw new RuntimeException();
        }
        deletedBooksTitles.add(books.getTitle());
        booksMapper.deleteBooks(idx);
      }
    } catch (Exception e){
      throw new RuntimeException();
    }

    return deletedBooksTitles.get(0) +" 외 "+ (deletedBooksTitles.size()-1) +"권이 목록에서 삭제되었습니다.";
  }

  public List<ProductDto> searchProducts(String query) {

    String NAVER_ID = System.getenv("NAVER_ID");
    String NAVER_SECRET = System.getenv("NAVER_SECRET");
    // 요청 URL 만들기
    URI uri = UriComponentsBuilder
        .fromUriString("https://openapi.naver.com")
        .path("/v1/search/shop.json")
        .queryParam("display", 100)
        .queryParam("query", query)
        .encode()
        .build()
        .toUri();
    log.info("uri = " + uri);

    RequestEntity<Void> requestEntity = RequestEntity
        .get(uri)
        .header("X-Naver-Client-Id", NAVER_ID)
        .header("X-Naver-Client-Secret", NAVER_SECRET)
        .build();


    ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

    List<ProductDto> productDtoList = fromJSONtoProducts(responseEntity.getBody());

    for(ProductDto productDto : productDtoList){
      insertProducts(productDto);
    }

    return fromJSONtoProducts(responseEntity.getBody());
  }

  public List<ProductDto> fromJSONtoProducts(String responseEntity) {
    JSONObject jsonObject = new JSONObject(responseEntity);
    JSONArray products  = jsonObject.getJSONArray("items");
    List<ProductDto> productDtoList = new ArrayList<>();

    for (Object product : products) {
      ProductDto productDto = new ProductDto((JSONObject) product);
      productDtoList.add(productDto);
    }

    return productDtoList;
  }

  public void insertProducts(ProductDto productDto){

    Product products = new Product(productDto);
    productRepository.save(products);

  }
}

