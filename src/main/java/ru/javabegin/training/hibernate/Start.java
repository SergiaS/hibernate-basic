package ru.javabegin.training.hibernate;

import org.hibernate.Session;
import ru.javabegin.training.hibernate.entity.Author;
import ru.javabegin.training.hibernate.entity.Book;

public class Start {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		for (Book book : new BookHelper().getBookList()) {
			System.out.println("Book name: " + book.getName());
		}

		HibernateUtil.getSessionFactory().close(); // закрываем фабрику, иначе программа останется в "зависнутом состоянии"

	}
}
