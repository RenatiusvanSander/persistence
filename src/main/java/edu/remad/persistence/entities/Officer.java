package edu.remad.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "officers")
public class Officer {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Enumerated(EnumType.STRING)
	private Rank rank;
	private String firstName;
	private String lastName;
	
	public Officer() {
	}

	public Officer(Rank rank, String firstName, String lastName) {
		super();
		this.rank = rank;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Officer(Integer id, Rank rank, String firstName, String lastName) {
		super();
		this.id = id;
		this.rank = rank;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Officer [id=" + id + ", rank=" + rank + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}	
}
