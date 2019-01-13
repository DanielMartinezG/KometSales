package com.kometsales.flowers.flowers.dto;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kometsales.flowers.utils.ValidationUtils;

@JsonPropertyOrder({"name","family","color","species","averageHeight","available","discoveryDate"})
public class FlowerReadDto {
	
	
	@NotNull(message="Name is empty")
	@Size(max=50, message = "name has too many characters")
	@Pattern(regexp= ValidationUtils.STRING_PATTERN, message = "Invalid type")
	private String name;
	@NotNull(message="Family is empty")
	@Size(max=50, message = "family has too many characters")
	private String family;
	@NotNull(message="Color is empty")
	@Pattern(regexp= ValidationUtils.STRING_PATTERN, message = "Invalid type")
	@Size(max=50, message = "color has too many characters")
	private String color;
	@NotNull(message="Species is empty")
	@Pattern(regexp= ValidationUtils.STRING_PATTERN, message = "Invalid type")
	@Size(max=50, message = "species has too many characters")
	private String species;
	@NotNull(message="Average Height is empty")
	@Pattern(regexp= ValidationUtils.NUMERIC_PATTERN, message = "Invalid type")
	@Size(max=50, message = "height has too many characters")
	private String averageHeight;
	@NotNull(message="Available is empty")
	@Pattern(regexp= ValidationUtils.STRING_PATTERN, message = "Invalid type")
	@Size(max=5, message = "available has too many characters")
	private String available;
	@NotNull(message="Discovery date is empty")
	@Pattern(regexp= ValidationUtils.DATE_PATTERN, message = "Invalid type")
	private String discoveryDate;
	private Set<ConstraintViolation<FlowerReadDto>> violations;
	
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
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
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	public String getDiscoveryDate() {
		return discoveryDate;
	}
	public void setDiscoveryDate(String discoveryDate) {
		this.discoveryDate = discoveryDate;
	}
	public Set<ConstraintViolation<FlowerReadDto>> getViolations() {
		return violations;
	}
	public void setViolations(Set<ConstraintViolation<FlowerReadDto>> violations) {
		this.violations = violations;
	}
	
}
