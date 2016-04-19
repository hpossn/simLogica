package testes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import servicos.TuplaString;

public class TesteTuplaString {
	
	@Test
	public void testTuplaString() {
		List<String> entrada = new ArrayList<String>();
		
		entrada.add("a");
		entrada.add("b");
		entrada.add("c");
		
		
		TuplaString tupla1 = new TuplaString("q0", entrada);
		
		List<String> entrada2 = new ArrayList<String>();
		
		entrada2.add("a");
		entrada2.add("b");
		entrada2.add("c");
		
		
		TuplaString tupla2 = new TuplaString("q0", entrada2);
		
		assertTrue(tupla1.equals(tupla2));
		
		
	}

}
