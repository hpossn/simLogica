package testes;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pilhas.Pilha;

public class TestePilha {

	@Test
	public void testPilha() {
		Pilha pilha = new Pilha();
		
		List<String> entrada = new ArrayList<String>();
		
		entrada.add("a");
		entrada.add("b");
		entrada.add("c");
		
		pilha.push(entrada);
		
		System.out.println(pilha.configuracao());
	}

}
