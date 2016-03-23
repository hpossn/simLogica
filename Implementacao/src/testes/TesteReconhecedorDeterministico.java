package testes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import reconhecedores.ReconhecedorFinitoDeterministico;
import servicos.Key;

public class TesteReconhecedorDeterministico {

	ReconhecedorFinitoDeterministico reconhecedorFD;
	Map<Key, String> transicoes;
	List<String> estadosFinais;
	String estadoInicial;
	
	@Before
	public void iniciaReconhecedor() {
		transicoes = new HashMap<Key, String>();
		
		transicoes.put(new Key("q0", "0"), "q0");
		transicoes.put(new Key("q0", "1"), "q1");
		transicoes.put(new Key("q0", "2"), "q3");
		transicoes.put(new Key("q1", "0"), "q3");
		transicoes.put(new Key("q1", "1"), "q1");
		transicoes.put(new Key("q1", "2"), "q2");
		transicoes.put(new Key("q2", "0"), "q3");
		transicoes.put(new Key("q2", "1"), "q3");
		transicoes.put(new Key("q2", "2"), "q2");
		transicoes.put(new Key("q3", "0"), "q3");
		transicoes.put(new Key("q3", "1"), "q3");
		transicoes.put(new Key("q3", "2"), "q3");
		
		
		estadosFinais = new ArrayList<String>();
		
		estadosFinais.add("q1");
		estadosFinais.add("q2");
		estadoInicial = "q0";
		
		reconhecedorFD = new ReconhecedorFinitoDeterministico(estadoInicial, estadosFinais, transicoes);
		
	}

	@Test
	public void testExecutarCadeia() {
		reconhecedorFD.iniciar("222");
		reconhecedorFD.analisar();
		
		System.out.println("reconheceu: " + reconhecedorFD.reconheceu());
		
	}

}
