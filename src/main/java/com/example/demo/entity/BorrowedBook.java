package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class BorrowedBook {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private book book;
    @ManyToOne
    private User user;
    private LocalDate issuedDate;
    private LocalDate returnDate;

	public BorrowedBook() {
	}
	
	public BorrowedBook(book book, User user, LocalDate issuedDate) {
        this.book = book;
        this.user = user;
        this.issuedDate = issuedDate;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public book getBook() {
		return book;
	}

	public void setBook(book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(LocalDate issuedDate) {
		this.issuedDate = issuedDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
}
