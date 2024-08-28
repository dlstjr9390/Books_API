package com.example.aladin.Service;

import com.example.aladin.Dto.BooksDto;
import com.example.aladin.Dto.ProductDto;
import com.example.aladin.Entity.Books;
import com.example.aladin.Entity.Product;
import com.example.aladin.Mapper.BooksMapper;
import com.example.aladin.Mapper.ProductMapper;
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

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final RestTemplate restTemplate;

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

  public List<ProductDto> searchProductByMybatis (String type, String content){
    List<Product> poductList;

    if(type.equals("lprice")){
      int intContent = Integer.parseInt(content);
      poductList = productMapper.searchProductByPrice(type, intContent);
    } else {
      poductList = productMapper.searchProduct(type,content);
    }

    List<ProductDto> productDtoList = new ArrayList<>();

    for(Product product : poductList){
      ProductDto productDto = new ProductDto(product);
      productDtoList.add(productDto);
    }

    return productDtoList;

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

  @Transactional
  public String deleteProduct(List<Integer> productId) {
    String idxList = productId.toString()
        .replace("[","")
        .replace("]","");
    String[] arrIdxStr = idxList.split(",");
    ArrayList<String> deletedProductTitles = new ArrayList<>();
    try {
      for (String idxStr : arrIdxStr) {
        Long idx = Long.valueOf(idxStr.trim());
        Product product = productRepository.findProductByProductId(idx);
        if (product == null) {
          throw new RuntimeException();
        }
        deletedProductTitles.add(product.getTitle());
        productMapper.deleteProduct(idx);
      }
    } catch (Exception e){
      throw new RuntimeException();
    }

    return deletedProductTitles.get(0) +" 외 "+ (deletedProductTitles.size()-1) +"개가 목록에서 삭제되었습니다.";
  }
}

