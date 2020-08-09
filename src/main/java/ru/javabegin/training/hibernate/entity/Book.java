package ru.javabegin.training.hibernate.entity;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "ru.javabegin.training.hibernate.entity.Book")
public class Book extends BaseEntity {

	@ManyToOne
	@JoinColumn
	private Author author;

}
