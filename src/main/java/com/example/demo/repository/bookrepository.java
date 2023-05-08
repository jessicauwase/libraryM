package com.example.demo.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.book;

public interface bookrepository extends JpaRepository<book, Long> {

}
