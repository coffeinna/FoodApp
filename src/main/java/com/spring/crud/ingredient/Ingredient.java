package com.spring.crud.ingredient;

import javax.persistence.*;

import org.springframework.lang.NonNull;

import com.spring.crud.unit.Unit;
@Entity
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	@Column(length=128, nullable= false, unique=true)
	private String name;
	@NonNull 
	private Double amount;
	
	@ManyToOne
	@JoinColumn(name="unit_id")
	private Unit unit;
	
	public Ingredient() {
	}

	public Ingredient(Integer id) {
		this.id = id;
	}
	
	public Ingredient(String name) {
		this.name = name;
	}
	
	public Ingredient(Integer id, String name, Double amount) {
		this.id = id;
		this.name = name;
		this.amount = amount;
	}
	
	public Ingredient(Integer id, String name, Double amount, Unit unit) {
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.unit = unit;
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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return unit+ " " + name;
	}

	
	
	
}
