package ru.javabegin.training.hibernate.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
public class Author implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // генерация ID через Autoincrement в MySQL
	private long id;

	@NonNull
	private String name;

	@Column(name = "second_name")
	private String secondName;

	public Author(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Author(String name) {
		this.name = name;
	}
}
