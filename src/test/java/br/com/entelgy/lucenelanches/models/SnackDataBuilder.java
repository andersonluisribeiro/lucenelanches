package br.com.entelgy.lucenelanches.models;

import java.util.Arrays;
import java.util.List;

public class SnackDataBuilder {
	
	public Snack get(){
		TypeOfBread typeOfBread = new TypeOfBread(10f);
		Cheese cheese = new Cheese(11f);
		Filling filling = new Filling(12f);
		Salad salad = new Salad(2f);
		TypeOfSnack type = new TypeOfSnack("normal");
		List<Sauce> sauces = (List<Sauce>) Arrays.asList( new Sauce(10f), new Sauce(8f) );
		List<Spice> spices = (List<Spice>) Arrays.asList( new Spice(10f), new Spice(8f) );
		return new Snack(null, typeOfBread, cheese, filling, salad, type, sauces, spices);
	}
	
	
	public Snack getWithDoubleOfCheese(){
		TypeOfBread typeOfBread = new TypeOfBread(10f);
		Cheese cheese = new Cheese(11f);
		Filling filling = new Filling(12f);
		Salad salad = new Salad(2f);
		TypeOfSnack type = new TypeOfSnack("normal");
		List<Sauce> sauces = (List<Sauce>) Arrays.asList( new Sauce(10f), new Sauce(8f) );
		List<Spice> spices = (List<Spice>) Arrays.asList( new Spice(10f), new Spice(8f) );
		return new Snack(null, typeOfBread, cheese, true, filling, false, salad, false, type, sauces, spices);
	}
	
	public Snack getWithDoubleOfFilling(){
		TypeOfBread typeOfBread = new TypeOfBread(10f);
		Cheese cheese = new Cheese(11f);
		Filling filling = new Filling(12f);
		Salad salad = new Salad(2f);
		TypeOfSnack type = new TypeOfSnack("normal");
		List<Sauce> sauces = (List<Sauce>) Arrays.asList( new Sauce(10f), new Sauce(8f) );
		List<Spice> spices = (List<Spice>) Arrays.asList( new Spice(10f), new Spice(8f) );
		return new Snack(null, typeOfBread, cheese, false, filling, true, salad, false, type, sauces, spices);
	}
	
	public Snack getWithDoubleOfSalad(){
		TypeOfBread typeOfBread = new TypeOfBread(10f);
		Cheese cheese = new Cheese(11f);
		Filling filling = new Filling(12f);
		Salad salad = new Salad(2f);
		TypeOfSnack type = new TypeOfSnack("normal");
		List<Sauce> sauces = (List<Sauce>) Arrays.asList( new Sauce(10f), new Sauce(8f) );
		List<Spice> spices = (List<Spice>) Arrays.asList( new Spice(10f), new Spice(8f) );
		return new Snack(null, typeOfBread, cheese, false, filling, false, salad, true, type, sauces, spices);
	}
	
	public Snack getWithoutTypeOfBread(){
		Cheese cheese = new Cheese(11f);
		Filling filling = new Filling(12f);
		Salad salad = new Salad(2f);
		TypeOfSnack type = new TypeOfSnack("normal");
		List<Sauce> sauces = (List<Sauce>) Arrays.asList( new Sauce(10f), new Sauce(8f) );
		List<Spice> spices = (List<Spice>) Arrays.asList( new Spice(10f), new Spice(8f) );
		return new Snack(null, null, cheese, filling, salad, type, sauces, spices);
	}
	
	public Snack getWithoutCheese(){
		TypeOfBread typeOfBread = new TypeOfBread(10f);
		Filling filling = new Filling(12f);
		Salad salad = new Salad(2f);
		TypeOfSnack type = new TypeOfSnack("normal");
		List<Sauce> sauces = (List<Sauce>) Arrays.asList( new Sauce(10f), new Sauce(8f) );
		List<Spice> spices = (List<Spice>) Arrays.asList( new Spice(10f), new Spice(8f) );
		return new Snack(null, typeOfBread, null, filling, salad, type, sauces, spices);
	}
	
	public Snack getWithoutFilling(){
		TypeOfBread typeOfBread = new TypeOfBread(10f);
		Cheese cheese = new Cheese(11f);
		Salad salad = new Salad(2f);
		TypeOfSnack type = new TypeOfSnack("normal");
		List<Sauce> sauces = (List<Sauce>) Arrays.asList( new Sauce(10f), new Sauce(8f) );
		List<Spice> spices = (List<Spice>) Arrays.asList( new Spice(10f), new Spice(8f) );
		return new Snack(null, typeOfBread, cheese, null, salad, type, sauces, spices);
	}
	
	public Snack getWithoutSalad(){
		TypeOfBread typeOfBread = new TypeOfBread(10f);
		Cheese cheese = new Cheese(11f);
		Filling filling = new Filling(12f);
		TypeOfSnack type = new TypeOfSnack("normal");
		List<Sauce> sauces = (List<Sauce>) Arrays.asList( new Sauce(10f), new Sauce(8f) );
		List<Spice> spices = (List<Spice>) Arrays.asList( new Spice(10f), new Spice(8f) );
		return new Snack(null, typeOfBread, cheese, filling, null, type, sauces, spices);
	}
	
	public Snack getWithoutSauces(){
		TypeOfBread typeOfBread = new TypeOfBread(10f);
		Cheese cheese = new Cheese(11f);
		Filling filling = new Filling(12f);
		Salad salad = new Salad(2f);
		TypeOfSnack type = new TypeOfSnack("normal");
		List<Spice> spices = (List<Spice>) Arrays.asList( new Spice(10f), new Spice(8f) );
		return new Snack(null, typeOfBread, cheese, filling, salad, type, null, spices);
	}
	
	public Snack getWithoutSpices(){
		TypeOfBread typeOfBread = new TypeOfBread(10f);
		Cheese cheese = new Cheese(11f);
		Filling filling = new Filling(12f);
		Salad salad = new Salad(2f);
		TypeOfSnack type = new TypeOfSnack("normal");
		List<Sauce> sauces = (List<Sauce>) Arrays.asList( new Sauce(10f), new Sauce(8f) );
		return new Snack(null, typeOfBread, cheese, filling, salad, type, sauces, null);
	}
	
	

}
