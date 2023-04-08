package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j //로깅을 위한 어노테이션 가나다라
public class ArticleController {
    @Autowired//스프링 부트가 미리 생성해 놓은 객체를 가져다가 연결해줘서 객체 생성 안해도 됨
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
//        System.out.println(form.toString());->로깅 기능으로 대체
        log.info(form.toString());
        //1.DTO를 Entitiy로 변환
        Article article = form.toEntity();
//        System.out.println(article.toString());
        log.info(article.toString());
        //2. Repository에게 Entity를 DB안에 저장하게 함
        Article saved = articleRepository.save(article);
//        System.out.println(saved.toString());
        log.info(saved.toString());
        return "redirect:/articles/"+saved.getId();
    }
        @GetMapping("/articles/{id}")
        public String show(@PathVariable Long id, Model model){
            log.info(("id="+id));
            //1. id로 데이터를 가져욤
            Article articleEntity=articleRepository.findById(id).orElse(null);

            //2. 가져온 데이터를 모델에 등록
            model.addAttribute("article",articleEntity);

            //3. 보여줄 페이지를 설정
            return "articles/show";
        }
        @GetMapping("/articles")
        public String index(Model model){
            //1.모든 Article을 가져온다.
            List<Article> articleEntityList= articleRepository.findAll();
            //2. 가져온 묶음을 View로 전달
            model.addAttribute("articleList",articleEntityList);
            //3. 뷰페이지를 설정한다.
            return "articles/index";
        }
        @GetMapping("/articles/{id}/edit")
        public String edit(@PathVariable Long id, Model model){
            //수정할 데이터 가져오기
            Article articleEntity = articleRepository.findById(id).orElse(null);
            //모델에 데이터 등록
            model.addAttribute("article",articleEntity);
            //뷰 페이지 설정
            return "articles/edit";
        }
        @PostMapping("/articles/update")
        public String update(ArticleForm form){
        log.info(form.toString());
        //1.dto를 entity로 변환
        Article articleEntity=form.toEntity();
        log.info(articleEntity.toString());
        //2.entity를 db로 저장
        //2-1:db에 기존 데이터를 가져온다
        Article target= articleRepository.findById(articleEntity.getId()).orElse(null);
        //2-2: 기존 데이터에 값을 갱신
        if(target !=null){
            articleRepository.save(articleEntity); //엔티티가 db로 갱신됨
        }
        //3. 수정 결과 페이지로 리다이렉트
        return "redirect:/articles/"+articleEntity.getId();
        }
}

