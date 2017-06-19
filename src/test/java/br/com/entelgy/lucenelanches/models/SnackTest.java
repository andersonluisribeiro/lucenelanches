package br.com.entelgy.lucenelanches.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SnackTest {
	
	private Snack snack;
	private SnackDataBuilder dataBuilder = new SnackDataBuilder();
	
	@Test
	public void shouldCalculateTotalPrice() {
		this.snack = this.dataBuilder.get();
		this.snack.calculateTotalPrice();
		assertEquals(new Float(71), this.snack.getTotalPrice());
	}
	
	@Test
	public void shouldCalculateTotalPriceWithDoubleOfCheese() {
		this.snack = this.dataBuilder.getWithDoubleOfCheese();
		this.snack.calculateTotalPrice();
		assertEquals(new Float(82), this.snack.getTotalPrice());
	}
	
	@Test
	public void shouldCalculateTotalPriceWithDoubleOfFilling() {
		this.snack = this.dataBuilder.getWithDoubleOfFilling();
		this.snack.calculateTotalPrice();
		assertEquals(new Float(83), this.snack.getTotalPrice());
	}
	
	@Test
	public void shouldCalculateTotalPriceWithDoubleOfSalad() {
		this.snack = this.dataBuilder.getWithDoubleOfSalad();
		this.snack.calculateTotalPrice();
		assertEquals(new Float(73), this.snack.getTotalPrice());
	}
	
	@Test
	public void shouldCalculateTotalPriceWithoutTypeOfBread() {
		this.snack = this.dataBuilder.getWithoutTypeOfBread();
		this.snack.calculateTotalPrice();
		assertEquals(new Float(61), this.snack.getTotalPrice());
	}
	
	@Test
	public void shouldCalculateTotalPriceWithoutCheese() {
		this.snack = this.dataBuilder.getWithoutCheese();
		this.snack.calculateTotalPrice();
		assertEquals(new Float(60), this.snack.getTotalPrice());
	}
	
	@Test
	public void shouldCalculateTotalPriceWithoutFilling() {
		this.snack = this.dataBuilder.getWithoutFilling();
		this.snack.calculateTotalPrice();
		assertEquals(new Float(59), this.snack.getTotalPrice());
	}
	
	@Test
	public void shouldCalculateTotalPriceWithoutSalad() {
		this.snack = this.dataBuilder.getWithoutSalad();
		this.snack.calculateTotalPrice();
		assertEquals(new Float(69), this.snack.getTotalPrice());
	}
	
	@Test
	public void shouldCalculateTotalPriceWithoutSauces() {
		this.snack = this.dataBuilder.getWithoutSauces();
		this.snack.calculateTotalPrice();
		assertEquals(new Float(53), this.snack.getTotalPrice());
	}
	
	@Test
	public void shouldCalculateTotalPriceWithoutSpices() {
		this.snack = this.dataBuilder.getWithoutSpices();
		this.snack.calculateTotalPrice();
		assertEquals(new Float(53), this.snack.getTotalPrice());
	}
	

}
