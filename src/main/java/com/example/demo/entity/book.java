package com.example.demo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "publisher")
	private String publisher;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "publicationdate")
	private LocalDate publicationdate;
	
	@Column(name = "genre")
	private String genre;
	
	@Column(name = "language")
	private String language;
	
	private int availableCopies;
	
	@OneToMany(mappedBy = "book", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<BorrowedBook> borrowedBooks;

	public book(){
		
	}
	public book(String title, String author, String publisher, LocalDate publicationdate, String genre, String language, int availableCopies) {
		super();
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.publicationdate = publicationdate;
		this.genre = genre;
		this.language = language;
		this.availableCopies = availableCopies;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public LocalDate getPublicationdate() {
		return publicationdate;
	}

	public void setPublicationdate(LocalDate publicationdate) {
		this.publicationdate = publicationdate;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	public int getAvailableCopies() {
		return availableCopies;
	}
	public void setAvailableCopies(int availableCopies) {
		this.availableCopies = availableCopies;
	}
	
	public List<BorrowedBook> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<BorrowedBook> borrowedBooks) {
		this.borrowedBooks = borrowedBooks;
	}
    
	// Helper methods
    public List<User> getBorrowers() {
        List<User> borrowers = new ArrayList<>();
        for (BorrowedBook borrowedBook : borrowedBooks) {
            borrowers.add(borrowedBook.getUser());
        }
        return borrowers;
    }
	
	public void addBorrowedBook(BorrowedBook borrowedBook) {
        borrowedBooks.add(borrowedBook);
    }

    public void removeBorrowedBook(BorrowedBook borrowedBook) {
        borrowedBooks.remove(borrowedBook);
    }	
}
