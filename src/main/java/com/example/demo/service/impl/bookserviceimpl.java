package com.example.demo.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.book;
import com.example.demo.repository.bookrepository;
import com.example.demo.service.bookservice;

@Service
public class bookserviceimpl implements bookservice {
    
	private bookrepository Bookrepository;
    
	
	

	public bookserviceimpl(bookrepository Bookrepository) {
		super();
		this.Bookrepository = Bookrepository;
	}


	

	@Override
	public List<book> getAllbook() {
	
		return Bookrepository.findAll();
	}




	@Override
	public book saveBook(book book) {
		// TODO Auto-generated method stub
		return Bookrepository.save(book);
	}




	@Override
	public book getBookById(Long id) {
		
		return Bookrepository.findById(id).get();
	}




	@Override
	public book updateBook(book book) {
		
		return Bookrepository.save(book);
	}




	@Override
	public void deleteBookById(Long id) {
		Bookrepository.deleteById(id);
		
	}
	





	 
}
