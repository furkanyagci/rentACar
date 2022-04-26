package com.etiya.rentACar.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cars")
public class Car {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="dailyPrice")
	private double dailyPrice;
	
	@Column(name="description")
	private String description;
	
	@Column(name="modelYear")
	private double modelYear;

	@Column(name="kilometer")
	private double kilometer;

	@Column(name="carState")
	private CarStates carStates;
	
	
	@ManyToOne//Many den bir'e demek. Bir arabanın birden fazla rengi olabilir.
	@JoinColumn(name="color_id")//name="color_id" bunun anlamı Color tablosundaki id'ye göre mapleyeceğiz.
	private Color color;
	
	@ManyToOne
	@JoinColumn(name="brand_id")
	private Brand brand;
	
	@OneToMany(mappedBy = "car")
	private List<Damage> damages;
	
	@OneToMany(mappedBy = "car")
	private List<Maintanance> maintanances;
	
	@OneToMany(mappedBy = "car")
	private List<Rental> rentals;
	
	@ManyToOne
	@JoinColumn(name="city_id")
	private City city;
	
	
	
}