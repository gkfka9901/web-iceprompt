package com.study.web.service;

import com.study.web.entity.Web;
import com.study.web.repository.WebRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WebService {   //@Service 어노테이션을 사용하여 Service Bean으로 등록됨.

    @Autowired  //WebService 클래스는 WebRepositoty 인터페이스를 @Autowired 어노테이션을 사용하여 의존성 주입(Dependency Injection)합니다.
    // 의존성 주입을 통해 WebService는 WebRepositoty의 기능(JpaRepository의 기능인 CRUD)을 사용할 수 있습니다.
    private WebRepository webRepository;

    // 글 작성 처리
    public void write(Web web) {    //WebRepository의 save() 메소드를 사용하여 Web 엔티티 객체를 데이터베이스에 저장

        webRepository.save(web);
    }

    // 게시글 리스트 처리
    public Page<Web> webList(Pageable pageable) {

        return webRepository.findAll(pageable); //webRepository에서 주입받은 기능. 데이터 베이스에서 모든 게시글 가져옴.
    }

    public Page<Web> webSearchList(String searchKeyword, Pageable pageable) {

        return webRepository.findByTitleContaining(searchKeyword, pageable);
    }

    // 특정 게시글 불러오기
    public Web webview(Integer id) {
        Optional<Web> optionalWeb = webRepository.findById(id);
        return optionalWeb.orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
    }



    // 특정 게시글 삭제

    public void webDelete(Integer id) {

        webRepository.deleteById(id);
    }
}
