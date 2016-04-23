package testes;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fitas.FitaIlimitada;

public class TesteFitaIlimitada {
	
	FitaIlimitada fitaIlimitada;
	
	@Before
	public void testeInicar() {
		List<String> alfabeto = new ArrayList<String>();
		alfabeto.add("numa");
		alfabeto.add("*");
		alfabeto.add("(");
		alfabeto.add(")");
		alfabeto.add("+");
		alfabeto.add("a");
		
		
		fitaIlimitada = new FitaIlimitada(alfabeto);
		fitaIlimitada.iniciar("numa*(numa+a)");
	}
	
	@Test
	public void testeLer() {
		System.out.println(fitaIlimitada.ler());		
	}
	
	@Test
	public void testeConfiguracao() {
		System.out.println(fitaIlimitada.configuracao());
	}
	

	@Test
	public void testeGenerico() {
		System.out.println(fitaIlimitada.configuracao());
		
		fitaIlimitada.recuar();
		System.out.println(fitaIlimitada.configuracao());
		
		fitaIlimitada.recuar();
		System.out.println(fitaIlimitada.configuracao());
		
		fitaIlimitada.avancar();
		System.out.println(fitaIlimitada.configuracao());
		
		fitaIlimitada.avancar();
		System.out.println(fitaIlimitada.configuracao());
		
		fitaIlimitada.avancar();
		System.out.println(fitaIlimitada.configuracao());
		
		fitaIlimitada.avancar();
		System.out.println(fitaIlimitada.configuracao());
		
		fitaIlimitada.avancar();
		System.out.println(fitaIlimitada.configuracao());
	}
}
