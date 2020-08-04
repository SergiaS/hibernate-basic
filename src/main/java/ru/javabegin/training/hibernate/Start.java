package ru.javabegin.training.hibernate;

import org.hibernate.Session;
import ru.javabegin.training.hibernate.entity.Author;

import org.jboss.logging.Logger;

public class Start {

	private static final Logger LOG = Logger.getLogger(AuthorHelper.class.getName());

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		for (Author author : new AuthorHelper().getAuthorList()) {
//			System.out.println("Book name: " + book.getName());
			LOG.warn(author.getName());
		}

		HibernateUtil.getSessionFactory().close(); // закрываем фабрику, иначе программа останется в "зависнутом состоянии"

	}
}
