package ru.javabegin.training.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.javabegin.training.hibernate.entity.Author;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AuthorHelper {

	private SessionFactory sessionFactory;

	public AuthorHelper() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public List<Author> getAuthorList(){

		// открыть сессию - для манипуляции с персист. объектами
		Session session = sessionFactory.openSession();

		session.get(Author.class, 1L); // получение объекта по id



		// этап подготовки запроса

		// объект-конструктор запросов для Criteria API
		CriteriaBuilder cb = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated

		CriteriaQuery cq = cb.createQuery(Author.class);

		Root<Author> root = cq.from(Author.class);// первостепенный, корневой entity (в sql запросе - from)

		cq.select(root);// необязательный оператор, если просто нужно получить все значения



		// этап выполнения запроса
		Query query = session.createQuery(cq);

		List<Author> authorList = query.getResultList();

		session.close();

		return authorList;

	}

	// добавляют нового автора в таблица Author
	public Author addAuthor(Author author){

		Session session = sessionFactory.openSession();
		session.getTransaction();

		session.beginTransaction();

//		for (int i = 1; i <= 200; i++) {
//
//			Author a = new Author("test_hw11_obj_" + i);
//			if (i % 10 == 0) {
//				session.flush();
//			}
//
//			session.save(a);
//		}



		session.save(author);

		session.close();

		return author;
	}

	public Author getAuthor(String name) { return null; }
}
