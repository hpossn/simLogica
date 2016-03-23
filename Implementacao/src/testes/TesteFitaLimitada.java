package testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fitas.FitaLimitada;

public class TesteFitaLimitada {
	
	FitaLimitada fitaLimitada;
	
	@Before
	public void testeInicar() {
		fitaLimitada = new FitaLimitada();
		fitaLimitada.iniciar("abc");
	}
	
	@Test
	public void testeLer() {
		assertEquals('a', fitaLimitada.ler());		
	}
	
	@Test
	public void testeConfiguracao() {
		assertEquals("(1, <[a]bc>)", fitaLimitada.configuracao());
	}
	

	@Test
	public void testeGenerico() {
		assertEquals("(1, <[a]bc>)", fitaLimitada.configuracao());
		
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
