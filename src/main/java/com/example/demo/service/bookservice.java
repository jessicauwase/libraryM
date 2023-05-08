package com.example.demo.service;


import java.util.List;

import com.example.demo.entity.book;

public interface bookservice {
   List<book> getAllbook();
   book saveBook(book book);
   
   book getBookById(Long id);
	
	book updateBook(book book);
	
	void deleteBookById(Long id);
   
}
