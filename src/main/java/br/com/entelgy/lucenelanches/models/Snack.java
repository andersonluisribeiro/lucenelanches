package br.com.entelgy.lucenelanches.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity
public class Snack {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Request request;
	
	@ManyToOne
	private TypeOfBread typeOfBread;
	
	@ManyToOne
	private Cheese cheese;
	
	@ManyToOne
	private Filling filling;
	
	@ManyToOne
	private Salad salad;
	
	private boolean doubleCheese;
	
	private boolean doubleFilling;
	
	private boolean doubleSalad;
	
	@ManyToOne
	private TypeOfSnack type;
	
	@ManyToMany
	private List<Sauce> sauces = new ArrayList<Sauce>();
	
	@ManyToMany
	private List<Spice> spices = new ArrayList<Spice>();
	
	private Float totalPrice;

	public Snack() {
	}
	
	public Snack(Request request, TypeOfBread typeOfBread, Cheese cheese, Filling filling, Salad salad,
			TypeOfSnack type, List<Sauce> sauces, List<Spice> spices) {
		this.request = request;
		this.typeOfBread = typeOfBread;
		this.cheese = cheese;	
		this.filling = filling;
		this.salad = salad;
		this.type = type;
		this.sauces = sauces;
		this.spices = spices;
	}
	
	public Snack(Request request, TypeOfBread typeOfBread, Cheese cheese, boolean doubleCheese, Filling filling, boolean doubleFilling,
			Salad salad,  boolean doubleSalad, TypeOfSnack type, List<Sauce> sauces, List<Spice> spices) {
		this.request = request;
		this.typeOfBread = typeOfBread;
		this.cheese = cheese;	
		this.doubleCheese = doubleCheese;
		this.filling = filling;
		this.doubleFilling = doubleFilling;
		this.salad = salad;
		this.doubleSalad = doubleSalad;
		this.type = type;
		this.sauces = sauces;
		this.spices = spices;
	}

	public Long getId() {
		return id;
	}

	public Request getRequest() {
		return request;
	}

	public TypeOfBread getTypeOfBread() {
		if(typeOfBread == null){
			return new NullTypeOfBread();
		}
		return typeOfBread;
	}
	
	public Cheese getCheese() {
		if(cheese == null){
			return new NullCheese();
		}
		return cheese;
	}

	public Filling getFilling() {
		if(filling == null){
			return new NullFilling();
		}
		return filling;
	}

	public Salad getSalad() {
		if(salad == null){
			return new NullSalad();
		}
		return salad;
	}
	
	public boolean getDoubleCheese() {
		return doubleCheese;
	}

	public boolean getDoubleFilling() {
		return doubleFilling;
	}

	public boolean getDoubleSalad() {
		return doubleSalad;
	}

	public TypeOfSnack getType() {
		return type;
	}

	public List<Sauce> getSauces() {
		if(sauces == null){
			return new ArrayList<Sauce>();
		}
		return sauces;
	}

	public List<Spice> getSpices() {
		if(spices == null){
			return new ArrayList<Spice>();
		}
		return spices;
	}
	
	public Float getTotalPrice(){
		return totalPrice;
	}
	
	@PrePersist
	public void calculateTotalPrice(){
		Float total = 0f;
		total += getTypeOfBreadPrice();
		total += getCheesePrice();
		total += getSaladPrice();
		total += getFillingPrice();
		total += getSaucesPrice();
		total += getSpicesPrice();
		this.totalPrice = total;
	}
	
	public Float getTypeOfBreadPrice(){
		return getTypeOfBread().getPrice();
	}
	
	public Float getCheesePrice(){
		Float total = getCheese().getPrice();
		if( getDoubleCheese() ){
			total += total;
		}
		return total;
	}
	
	public Float getSaladPrice(){
		Float total = getSalad().getPrice();
		if( getDoubleSalad() ){
			total += total;
		}
		return total;
	}
	
	public Float getFillingPrice(){
		Float total = getFilling().getPrice();
		if( getDoubleFilling() ){
			total += total;
		}
		return total;
	}
	
	public Float getSaucesPrice(){
		Float total = 0f;
		for(Sauce sauce : getSauces()){
			total += sauce.getPrice();
		}
		return total;
	}
	
	public Float getSpicesPrice(){
		Float total = 0f;
		for(Spice spice : getSpices()){
			total += spice.getPrice();
		}
		return total;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Snack other = (Snack) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
