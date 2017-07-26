package edu.mum.hw2.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Actor {
	
	private String name;
	@Column(name="characters")
	private String character;
	private int rating;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
}
