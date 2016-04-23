package testes;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fitas.FitaLimitada;

public class TesteFitaLimitada {
	
	FitaLimitada fitaLimitada;
	
	@Before
	public void testeInicar() {
		List<String> alfabeto = new ArrayList<String>();
		alfabeto.add("numa");
		alfabeto.add("*");
		alfabeto.add("(");
		alfabeto.add(")");
		alfabeto.add("+");
		alfabeto.add("a");
		
		
		fitaLimitada = new FitaLimitada(alfabeto);
		fitaLimitada.iniciar("numa*(numa+a)");
	}
	

	@Test
	public void testeGenerico() {
		
		fitaLimitada.recuar();
		System.out.println(fitaLimitada.configuracao());
		
		fitaLimitada.recuar();
		System.out.println(fitaLimitada.configuracao());
		
		fitaLimitada.avancar();
		System.out.println(fitaLimitada.configuracao());
		
		fitaLimitada.avancar();
		System.out.println(fitaLimitada.configuracao());
		
		fitaLimitada.avancar();
		System.out.println(fitaLimitada.configuracao());
		
		fitaLimitada.avancar();
		System.out.println(fitaLimitada.configuracao());
		
		fitaLimitada.avancar();
		System.out.println(fitaLimitada.configuracao());
		
		System.out.println(fitaLimitada.toString());
	}
}
