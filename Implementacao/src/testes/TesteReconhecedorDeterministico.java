package testes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import reconhecedores.ReconhecedorFinitoDeterministico;
import servicos.Key;
import servicos.TuplaString;

public class TesteReconhecedorDeterministico {

	ReconhecedorFinitoDeterministico reconhecedorFD;
	Map<Key, TuplaString> transicoes;
	List<String> estadosFinais;
	String estadoInicial;
	
	@Before
	public void iniciaReconhecedor() {
		transicoes = new HashMap<Key, TuplaString>();
		
		transicoes.put(new Key("q0", "0", ""), new TuplaString("q0", new ArrayList<String>()));
		transicoes.put(new Key("q0", "1", ""), new TuplaString("q1", new ArrayList<String>()));
		transicoes.put(new Key("q0", "2", ""), new TuplaString("q3", new ArrayList<String>()));
		transicoes.put(new Key("q1", "0", ""), new TuplaString("q3", new ArrayList<String>()));
		transicoes.put(new Key("q1", "1", ""), new TuplaString("q1", new ArrayList<String>()));
		transicoes.put(new Key("q1", "2", ""), new TuplaString("q2", new ArrayList<String>()));
		transicoes.put(new Key("q2", "0", ""), new TuplaString("q3", new ArrayList<String>()));
		transicoes.put(new Key("q2", "1", ""), new TuplaString("q3", new ArrayList<String>()));
		transicoes.put(new Key("q2", "2", ""), new TuplaString("q2", new ArrayList<String>()));
		transicoes.put(new Key("q3", "0", ""), new TuplaString("q3", new ArrayList<String>()));
		transicoes.put(new Key("q3", "1", ""), new TuplaString("q3", new ArrayList<String>()));
		transicoes.put(new Key("q3", "2", ""), new TuplaString("q3", new ArrayList<String>()));
		
		
		estadosFinais = new ArrayList<String>();
		
		estadosFinais.add("q1");
		estadosFinais.add("q2");
		estadoInicial = "q0";
		
		reconhecedorFD = new ReconhecedorFinitoDeterministico(estadoInicial, estadosFinais, transicoes);
		
	}

	@Test
	public void testExecutarCadeia() {
		reconhecedorFD.iniciar("1");
		reconhecedorFD.analisar();
		
		System.out.println("reconheceu: " + reconhecedorFD.reconheceu());
		
	}

}
