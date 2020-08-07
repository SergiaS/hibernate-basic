package ru.javabegin.training.hibernate;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.javabegin.training.hibernate.entity.Book;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class BookHelper {

	private SessionFactory sessionFactory;

	public BookHelper() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public List<Book> getBookList(){

//		Statistics statistics = sessionFactory.getStatistics();
//		statistics.clear();
//		statistics.setStatisticsEnabled(true);

		// открыть сессию - для манипуляции с персист. объектами
		Session session = sessionFactory.openSession();

		// этап подготовки запроса

		// объект-конструктор запросов для Criteria API
		CriteriaBuilder cb = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated

		CriteriaQuery cq = cb.createQuery(Book.class);

		Root<Book> root = cq.from(Book.class);// первостепенный, корневой entity (в sql запросе - from)

		cq.select(root);// необязательный оператор, если просто нужно получить все значения



		// этап выполнения запроса
		Query query = session.createQuery(cq);

		List<Book> bookList = query.getResultList();



		Book b1 = session.get(Book.class, 2L);

		session.evict(b1); // удаляем объект из кеша 1го уровня

		Book b2 = session.get(Book.class, 2L);



		session.close();

//		statistics.logSummary();
//		System.out.println(statistics.getQueries());

		return bookList;

	}

}
