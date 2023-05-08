package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.entity.BorrowedBook;
import com.example.demo.repository.BorrowedBookRepository;
import com.example.demo.service.BorrowedBookService;

@Service
public class BorrowedBookServiceImpl implements BorrowedBookService {
	
	private BorrowedBookRepository borrowedBookRepository;

	public BorrowedBookServiceImpl(BorrowedBookRepository borrowedBookRepository) {
		super();
		this.borrowedBookRepository = borrowedBookRepository;
	}

	@Override
	public BorrowedBook borrow(BorrowedBook borrowedBook) {
		return borrowedBookRepository.save(borrowedBook);
	}

}
