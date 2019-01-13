package com.kometsales.flowers.flowers.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.kometsales.flowers.exception.ServiceException;
import com.kometsales.flowers.flowers.dto.FlowerReadDto;
import com.kometsales.flowers.utils.Utils;
import com.kometsales.flowers.utils.ValidationUtils;

@Entity
@Table(name = "FLOWERS")
public class Flower {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "flowers_sec_id")
	@SequenceGenerator(name = "flowers_sec_id", sequenceName = "flower_sec", allocationSize = 1, initialValue = 1)
	@Column(name = "ID", length = 6, nullable = false, updatable = false, unique = true)
	private Integer id;

	@Column(name = "NAME", length = 50, unique = true)
	private String name;

	@Column(name = "FAMILY", nullable = false, length = 50)
	private String family;
	
	@Column(name = "COLOR", nullable = false, length = 50)
	private String color;
	
	@Column(name = "SPECIES", nullable = false, length = 50)
	private String species;
	
	@Column(name = "AVERAGE_HEIGHT", nullable = false, length = 50)
	private String averageHeight;
	
	@Column(name = "AVAILABLE", nullable = false)
	private boolean available;
	
	@Column(name = "DISCOVERY_DATE", nullable = false)
	private Date discoveryDate;

	
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

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getAverageHeight() {
		return averageHeight;
	}

	public void setAverageHeight(String averageHeight) {
		this.averageHeight = averageHeight;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean abailable) {
		this.available = abailable;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Date getDiscoveryDate() {
		return discoveryDate;
	}

	public void setDiscoveryDate(Date discoveryDate) {
		this.discoveryDate = discoveryDate;
	}

	public static Flower convertDtoToFlower(FlowerReadDto flowerDto) throws ServiceException {
		Flower flower = new Flower();
		flower.setName(flowerDto.getName());
		flower.setSpecies(flowerDto.getSpecies());
		flower.setFamily(flowerDto.getFamily());
		flower.setDiscoveryDate(Utils.convertStringToDate(flowerDto.getDiscoveryDate(), ValidationUtils.DATE_FORMAT) );
		flower.setAvailable(flowerDto.getAvailable().equals("true")?true:false);
		flower.setAverageHeight(flowerDto.getAverageHeight());
		flower.setColor(flowerDto.getColor());
		return flower;
	}
}
