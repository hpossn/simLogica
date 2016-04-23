package testes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import servicos.Key;
import servicos.TuplaString;
import automatos.Automato;
import automatos.AutomatoFinitoDeterministico;

public class TesteAutomatoFinitoDeterministico {
	
	Automato automatoFD;
	Map<Key, TuplaString> transicoes;
	List<String> estadosFinais;
	String estadoInicial;
	
	@Before
	public void iniciaAutomato() {
		transicoes = new HashMap<Key, TuplaString>();
		
		transicoes.put(new Key("q0", "0", ""), new TuplaString("q0", new ArrayList<String>()));
		transicoes.put(new Key("q0", "1", ""), new TuplaString("q1", new ArrayList<String>()));
		transicoes.put(new Key("q1", "0", ""), new TuplaString("q1", new ArrayList<String>()));
		transicoes.put(new Key("q1", "1", ""), new TuplaString("q2", new ArrayList<String>()));
		transicoes.put(new Key("q2", "0", ""), new TuplaString("q2", new ArrayList<String>()));
		transicoes.put(new Key("q2", "1", ""), new TuplaString("q2", new ArrayList<String>()));
		
		estadosFinais = new ArrayList<String>();
		
		estadosFinais.add("q2");
		estadoInicial = "q0";
		
		List<String> alfabeto = new ArrayList<String>();
		alfabeto.add("0");
		alfabeto.add("1");
		
		automatoFD = new AutomatoFinitoDeterministico(transicoes, estadosFinais, estadoInicial, true, alfabeto);
	}

	@Test
	public void testExecutarCadeia() {
		automatoFD.iniciar("10110000");
		List<Automato> resultadoAutomato= automatoFD.executar();
		
		boolean reconheceu = false;
		
		for(Automato a : resultadoAutomato) {
			if(a.estaEmEstadoFinal())
				if(a.estaEmFinalDeCadeia())
					reconheceu = true;
		}
		
		System.out.println("O automato reconheceu: " + reconheceu);
		
	}

}
