package ru.javabegin.training.hibernate;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import ru.javabegin.training.hibernate.entity.Author;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;


public class AuthorHelper {

	private static Statistics stat;
	private SessionFactory sessionFactory;

	public AuthorHelper() {
		sessionFactory = HibernateUtil.getSessionFactory();
		stat = sessionFactory.getStatistics();
		stat.clear();
		stat.setStatisticsEnabled(true);
	}

	public List<Author> getAuthorList() {

		// открыть сессию - для манипуляции с персист. объектами
		Session session = sessionFactory.openSession();

		// этап подготовки запроса

		// объект-конструктор запросов для Criteria API
		CriteriaBuilder cb = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated

		CriteriaQuery cq = cb.createQuery(Author.class);

		Root<Author> root = cq.from(Author.class);// первостепенный, корневой entity (в sql запросе - from)
		root.fetch("books", JoinType.INNER);

//		Selection[] selection = {root.get("id"), root.get("name")}; // выборка полей, в классе Author должен быть конструктор с этими параметрами

		ParameterExpression<String> nameParam = cb.parameter(String.class, "name");

//		cq.select(cb.construct(Author.class, selection));
		//  .where(cb.like(root.get(Author_.name), nameParam));


		cq.select(root);


		// этап выполнения запроса
		Query query = session.createQuery(cq);
//        query.setParameter("name", "%имя%");


		List<Author> authorList = query.getResultList();

//		Author a1 = session.get(Author.class, 2L);
//		session.evict(a1); // удаляем объект из кеша 1го уровня
//		Author a2 = session.get(Author.class, 2L);


		session.close();

		printStat();


		return authorList;

	}

	// добавляют нового автора в таблица Author
	public void addAuthors() {


		Session session = sessionFactory.openSession();
		session.beginTransaction();

		for (int i = 1; i <= 10; i++) {
			Author author = new Author();
			author.setName(i + "_name");

			if (i % 2 == 0) {
				session.flush();
			}
			session.save(author); // сгенерит ID и вставит в объект
		}

		session.getTransaction().commit();

		session.close();
	}


	public void delete() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Author a = session.get(Author.class, 1L);

		session.delete(a);

		session.getTransaction().commit();

		session.close();
	}

	public void deleteByCriteria() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaDelete<Author> criteriaDelete = cb.createCriteriaDelete(Author.class);
		Root<Author> root = criteriaDelete.from(Author.class);

		ParameterExpression<String> nameParam = cb.parameter(String.class, "name");
		ParameterExpression<String> secondNameParam = cb.parameter(String.class, "secondName");

		criteriaDelete.where(cb.or(
				cb.and(
						cb.like(root.get("name"), nameParam),
						cb.like(root.get("secondName"), secondNameParam)
				),
				cb.equal(root.get("name"), "sec_name83")
				)
		);

		// этап выполнения запроса
		Query query = session.createQuery(criteriaDelete);
		query.setParameter("name", "%2%");
		query.setParameter("secondName", "%t%");

		query.executeUpdate();

//		session.getTransaction().commit();

		session.close();
	}


	// HW of lesson 17
	public void updateIt() {

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		CriteriaBuilder cb = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated

		CriteriaUpdate<Author> criteriaUpdate = cb.createCriteriaUpdate(Author.class);

		Root<Author> root = criteriaUpdate.from(Author.class);// первостепенный, корневой entity (в sql запросе - from)

		ParameterExpression<String> nameParam = cb.parameter(String.class, "name");

		Expression<Integer> length = cb.length(root.get("secondName"));

		criteriaUpdate.set(root.get("name"), java.util.Optional.ofNullable(nameParam))
				.where(cb.equal(length, 9));


		// этап выполнения запроса
		Query query = session.createQuery(criteriaUpdate);
		query.setParameter("name", "1111");

		query.executeUpdate();// вместо возврата результата - используется метод обновления


		session.getTransaction().commit();

		session.close();
	}

	public Author getAuthor(Long id) {
		Session session = sessionFactory.openSession();

		Author author = session.get(Author.class, id);

//		printStat();

		return author;
	}

	private static void printStat() {
		System.out.println("Выполнено запросов в БД: "+stat.getQueryExecutionCount());
		System.out.println("Найдено в кэше: "+stat.getSecondLevelCacheHitCount());
		System.out.println("Добавлено в кэш: "+stat.getSecondLevelCachePutCount());
		stat.clear();
	}

}
