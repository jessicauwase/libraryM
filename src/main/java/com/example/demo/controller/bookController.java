package com.example.demo.controller;


import com.example.demo.entity.BorrowedBook;
import com.example.demo.entity.User;
import com.example.demo.entity.book;
import com.example.demo.service.BorrowedBookService;
import com.example.demo.service.EmailSenderService;
import com.example.demo.service.UserService;
import com.example.demo.service.bookservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class bookController {

	@Autowired
	protected bookservice Bookservice;
	@Autowired
	protected BorrowedBookService borrowedBookService;
	@Autowired
	protected UserService userService;
	@Autowired
	protected EmailSenderService emailSenderService;

	// handler method to handle list students and return mode and view
	@GetMapping("/getbook")
	public String listStudents(Model model) {
		model.addAttribute("book", Bookservice.getAllbook());
		return "book";
	}

	@GetMapping("/book/new")
	public String createBookForm(Model model) {

		// create student object to hold student form data
		book Book = new book();
		model.addAttribute("book", Book);
		return "create_book";
	}

	@GetMapping("/borrow")
	public String borrowBook(Model model) {
		BorrowedBook borrowedBook = new BorrowedBook();
		borrowedBook.setUser(new User());
		borrowedBook.setBook(new book());
		model.addAttribute("borrowedBook", borrowedBook);
		model.addAttribute("books", Bookservice.getAllbook());
		return "borrow_book";
	}

	@PostMapping("/borrow/save")
	public String saveBorrow(@RequestParam("user.email") String email,@RequestParam("user.name") String name,@RequestParam("book_id") Long bookId, @ModelAttribute("borrowedBook") BorrowedBook borrowedBook) {
		User user = userService.getUserByEmail(email);

		if(user == null) {
			user = new User();
			user.setName(name);
			user.setEmail(email);
			userService.saveUser(user);
		}

		book book = Bookservice.getBookById(bookId);

		borrowedBook.setBook(book);
		borrowedBook.setUser(user);
		borrowedBookService.borrow(borrowedBook);

		StringBuilder body = new StringBuilder();
		body.append("You have borrowed a book");
		body.append("\n");
		body.append("Book Title: " + book.getTitle());
		body.append("\n");
		body.append("Book Author: " + book.getAuthor());
		body.append("\n");
		body.append("Book Publisher: " + book.getPublisher());
		body.append("\n");
		body.append("Book Publication Date: " + book.getPublicationdate());
		body.append("\n");
		body.append("Book Genre: " + book.getGenre());
		body.append("\n");
		body.append("Book Language: " + book.getLanguage());
		body.append("\n");
		body.append("Issued Date: " + borrowedBook.getIssuedDate());
		body.append("\n");
		body.append("Return Date: " + borrowedBook.getReturnDate());

		emailSenderService.sendEmail(email, "Book Borrowed", body.toString());

		return "redirect:/getbook";
	}

	@GetMapping("/book/{id}/borrowers")
	public String getBorrowers(@PathVariable Long id, Model model) {
		model.addAttribute("book", Bookservice.getBookById(id));
		return "borrowers";
	}

	@PostMapping("/book")
	public String saveBook(@ModelAttribute("book") book book) {
		Bookservice.saveBook(book);
		return "redirect:/getbook";
	}

	@GetMapping("/book/edit/{id}")
	public String editBookForm(@PathVariable Long id, Model model) {
		model.addAttribute("book", Bookservice.getBookById(id));
		return "edit_book";
	}

	@PostMapping("/book/{id}")
	public String updateBook(@PathVariable Long id,
							 @ModelAttribute("book") book book,
							 Model model) {

		// get student from database by id
		book existingBook = Bookservice.getBookById(id);
		existingBook.setId(id);
		existingBook.setTitle(book.getTitle());
		existingBook.setAuthor(book.getAuthor());
		existingBook.setPublisher(book.getPublisher());
		existingBook.setPublicationdate(book.getPublicationdate());
		existingBook.setGenre(book.getGenre());
		existingBook.setLanguage(book.getLanguage());
		existingBook.setBorrowedBooks(book.getBorrowedBooks());
		existingBook.setAvailableCopies(book.getAvailableCopies());

		// save updated student object
		Bookservice.updateBook(existingBook);
		return "redirect:/getbook";
	}

	@GetMapping("/book/{id}")
	public String deleteBook(@PathVariable Long id) {
		Bookservice.deleteBookById(id);
		return "redirect:/getbook";
	}

}
