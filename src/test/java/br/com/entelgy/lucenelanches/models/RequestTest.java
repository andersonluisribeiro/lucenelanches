package br.com.entelgy.lucenelanches.models;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class RequestTest {

	@Test
	public void shouldReturnTotalPrice() {
		Request request = new Request("teste", "teste", getMockedSnacks());
		assertEquals(new Float(25), request.getTotalPrice());
	}
	
	@Test
	public void shouldReturnTotalPriceWithoutSnacks() {
		Request request = new Request("teste", "teste");
		assertEquals(new Float(0), request.getTotalPrice());
	}
	
	private List<Snack> getMockedSnacks(){
		Snack one = mock(Snack.class);
		Snack two = mock(Snack.class);
		when(one.getTotalPrice()).thenReturn(10f);
		when(two.getTotalPrice()).thenReturn(15f);
		return Arrays.asList( one, two );
	}
	
	

}
