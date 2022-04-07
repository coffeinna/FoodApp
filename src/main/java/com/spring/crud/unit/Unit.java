package com.spring.crud.unit;

import javax.persistence.*;

@Entity
public class Unit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	@Column(length=45, nullable= false, unique=true)
	private String name;
	
	public Unit() {
	}
	public Unit(String name) {
		this.name = name;
	}
	public Unit(Integer id) {
		this.id = id;
	}
	public Unit(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return name;
	} 
	
	
}
