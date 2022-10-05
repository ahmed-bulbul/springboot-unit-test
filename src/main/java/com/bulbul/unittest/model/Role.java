package com.bulbul.unittest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "ACL_ROLE")
public class Role  {

	@Id
	@GeneratedValue
	private long id;
	
	private String authority;
	private String description;

	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
	private List<User> users;

	public Role(String authority, String description) {
		this.authority = authority;
		this.description = description;

	}
}