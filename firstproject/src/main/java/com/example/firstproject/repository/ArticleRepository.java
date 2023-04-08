package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article,Long> {//JPA에서 제공하는 리포지토리 인터페이스를 활용해서 쉽게 만들 수 있다. <관리대상 Entitiy,대푯값의 타입>

    @Override
    ArrayList<Article> findAll();
}
