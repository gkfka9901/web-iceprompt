package com.study.web.repository;

import com.study.web.entity.Web;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebRepository extends JpaRepository<Web, Integer> {

    Page<Web> findByTitleContaining(String searchKeyword, Pageable pageable);

}

//ebRepositoty는 JpaRepository 인터페이스를 상속받고 있습니다. JpaRepository는 Spring Data JPA에서 제공하는 인터페이스로,
// 기본적인 CRUD(Create, Read, Update, Delete) 메소드를 제공합니다.
// JpaRepository의 제네릭 타입으로는 엔티티 클래스(Web)와 기본 키 타입(Integer)을 지정합니다.