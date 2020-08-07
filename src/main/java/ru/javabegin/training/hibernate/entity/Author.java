package ru.javabegin.training.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author extends BaseEntity{

	@Column(name = "second_name")
	private String secondName;

	@Embedded
	private CommonFields commonFields;

	@OneToMany(targetEntity = Book.class, mappedBy = "author")
	private List<Book> books = new ArrayList<>();

}
