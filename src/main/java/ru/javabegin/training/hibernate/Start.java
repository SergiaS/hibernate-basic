package ru.javabegin.training.hibernate;

import org.jboss.logging.Logger;
import ru.javabegin.training.hibernate.entity.Author;

import java.util.List;

public class Start {

	private static final Logger LOG = Logger.getLogger(AuthorHelper.class.getName());

	public static void main(String[] args) {

		AuthorHelper authorHelper = new AuthorHelper();

//        new AuthorHelper().getAuthor(202);
		List<Author> list = authorHelper.getAuthorList();

		authorHelper.getAuthor(1L);

//        List<Book> list = new BookHelper().getBookList();
//        new AuthorHelper().getAuthor(1L).g


//        new AuthorHelper().update("test22");

//        new AuthorHelper().addAuthors();

		HibernateUtil.getSessionFactory().close(); // закрываем фабрику, иначе программа останется в "зависнутом состоянии"

	}
}
