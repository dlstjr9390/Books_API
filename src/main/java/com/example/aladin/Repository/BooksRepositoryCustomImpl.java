package com.example.aladin.Repository;

import com.example.aladin.Entity.Books;
import com.example.aladin.Entity.QBooks;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class BooksRepositoryCustomImpl implements BooksRepositoryCustom{

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Books> findBooks(String type, String content){
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    QBooks books = QBooks.books;


    BooleanExpression predicate = getPredicateByType(type, "%"+content+"%", books);

    return queryFactory.selectFrom(books).where(predicate).fetch();

  }

  private BooleanExpression getPredicateByType(String type, String likeContent, QBooks books){

    if ("title".equalsIgnoreCase(type)) {
      return books.title.likeIgnoreCase(likeContent);
    } else if ("author".equalsIgnoreCase(type)) {
      return books.author.likeIgnoreCase(likeContent);
    } else if ("publisher".equalsIgnoreCase(type)) {
      return books.publisher.likeIgnoreCase(likeContent);
    } else {

      return null;
    }
  }
}
