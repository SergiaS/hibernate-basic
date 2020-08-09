package ru.javabegin.training.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "ru.javabegin.training.hibernate.entity.Author")
public class Author extends BaseEntity{

	@Column(name = "second_name")
	private String secondName;

	@OneToMany(targetEntity = Book.class, mappedBy = "author", fetch = FetchType.EAGER) // change to LAZY
	private List<Book> books = new ArrayList<>();

}
