package testes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import servicos.Key;
import automatos.Automato;
import automatos.AutomatoFinitoDeterministico;

public class TesteAutomatoFinitoDeterministico {
	
	Automato automatoFD;
	Map<Key, String> transicoes;
	List<String> estadosFinais;
	String estadoInicial;
	
	@Before
	public void iniciaAutomato() {
		transicoes = new HashMap<Key, String>();
		
		transicoes.put(new Key("q0", "0"), "q0");
		transicoes.put(new Key("q0", "1"), "q1");
		transicoes.put(new Key("q1", "0"), "q1");
		transicoes.put(new Key("q1", "1"), "q2");
		transicoes.put(new Key("q2", "0"), "q2");
		transicoes.put(new Key("q2", "1"), "q2");
		
		estadosFinais = new ArrayList<String>();
		
		estadosFinais.add("q2");
		estadoInicial = "q0";
		
		automatoFD = new AutomatoFinitoDeterministico(transicoes, estadosFinais, estadoInicial);
	}

	@Test
	public void testExecutarCadeia() {
		automatoFD.iniciar("00010001");
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
