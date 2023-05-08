package com.example.demo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	@ManyToMany(fetch = FetchType.EAGER)
	private List<book> borrowedBooks;
	
	public User() {}
	
	public User(Long id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.borrowedBooks = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public List<book> getBorrowedBooks() {
		return borrowedBooks;
	}
	public void setBorrowedBooks(List<book> borrowedBooks) {
		this.borrowedBooks = borrowedBooks;
	}
	public void borrowBook(book book) {
        this.borrowedBooks.add(book);
        book.getBorrowers().add(this);
    }

    public void returnBook(book book) {
        this.borrowedBooks.remove(book);
        book.getBorrowers().remove(this);
    }
}
