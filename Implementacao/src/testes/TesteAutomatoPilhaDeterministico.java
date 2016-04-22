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
import automatos.AutomatoPilhaDeterministico;

public class TesteAutomatoPilhaDeterministico {
	
	Automato automatoPD;
	Map<Key, TuplaString> transicoes;
	List<String> estadosFinais;
	String estadoInicial;
	
	@Before
	public void iniciaAutomato() {
		transicoes = new HashMap<Key, TuplaString>();
		
		List<String> trans1 = new ArrayList<String>();
		trans1.add("Z0");
		trans1.add("C");
		trans1.add("C");
		
		List<String> trans2 = new ArrayList<String>();
		trans2.add("C");
		trans2.add("C");
		trans2.add("C");
		
		List<String> trans3 = new ArrayList<String>();
		trans3.add("Z0");
		
		List<String> trans4 = new ArrayList<String>();
		trans4.add("C");
		
		List<String> trans5 = new ArrayList<String>();
		
		List<String> trans6 = new ArrayList<String>();

		transicoes.put(new Key("q0", "a", "Z0"), new TuplaString("q0", trans1));
		transicoes.put(new Key("q0", "a", "C"), new TuplaString("q0", trans2));
		transicoes.put(new Key("q0", "b", "Z0"), new TuplaString("q1", trans3));
		transicoes.put(new Key("q0", "b", "C"), new TuplaString("q1", trans4));
		transicoes.put(new Key("q1", "c", "C"), new TuplaString("q1", trans5));
		transicoes.put(new Key("q1", ">", "Z0"), new TuplaString("q1", trans6));
		
		estadosFinais = new ArrayList<String>();
		
		estadosFinais.add("q1");
		estadoInicial = "q0";
		
		automatoPD = new AutomatoPilhaDeterministico(transicoes, estadosFinais, estadoInicial, true);
	}

	@Test
	public void testExecutarCadeia() {
		automatoPD.iniciar("aabccc");
		List<Automato> resultadoAutomato= automatoPD.executar();
		
		boolean reconheceu = false;
		
		for(Automato a : resultadoAutomato) {
			if(a.estaEmEstadoFinal())
				if(a.estaEmFinalDeCadeia())
					reconheceu = true;
			if(a.isPilhaVazia()) {
				reconheceu = true;
			}
		}
		
		System.out.println("O automato reconheceu: " + reconheceu);
		
	}

}
