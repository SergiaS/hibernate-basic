package ru.javabegin.training.hibernate;

import org.jboss.logging.Logger;
import ru.javabegin.training.hibernate.entity.Book;

import java.util.List;

public class Start {

	private static final Logger LOG = Logger.getLogger(AuthorHelper.class.getName());

	public static void main(String[] args) {


//		new AuthorHelper().getAuthor(202L);

		List<Book> bookList = new BookHelper().getBookList();

	}
}
