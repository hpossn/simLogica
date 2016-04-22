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
import automatos.AutomatoMTFI;

public class TesteAutomatoMTFI {
	
	Automato automatoMTFI;
	Map<Key, TuplaString> transicoes;
	List<String> estadosFinais;
	String estadoInicial;
	
	@Before
	public void iniciaAutomato() {
		transicoes = new HashMap<Key, TuplaString>();
		
		List<String> trans1 = new ArrayList<String>();
		trans1.add("<");
		trans1.add("D");
		
		List<String> trans2 = new ArrayList<String>();
		trans2.add("a");
		trans2.add("D");
		
		List<String> trans3 = new ArrayList<String>();
		trans3.add("b");
		trans3.add("D");
		
		List<String> trans4 = new ArrayList<String>();
		trans4.add("c");
		trans4.add("D");
		
		List<String> trans5 = new ArrayList<String>();
		trans5.add("a");
		trans5.add("D");
		
		List<String> trans6 = new ArrayList<String>();
		trans6.add("b");
		trans6.add("D");
		
		List<String> trans7 = new ArrayList<String>();
		trans7.add("c");
		trans7.add("D");
		
		List<String> trans8 = new ArrayList<String>();
		trans8.add("B");
		trans8.add("D");

		transicoes.put(new Key("q0", "<", ""), new TuplaString("q1", trans1));
		transicoes.put(new Key("q1", "a", ""), new TuplaString("q2", trans2));
		transicoes.put(new Key("q2", "b", ""), new TuplaString("q3", trans3));
		transicoes.put(new Key("q3", "c", ""), new TuplaString("q4", trans4));
		transicoes.put(new Key("q4", "a", ""), new TuplaString("q4", trans5));
		transicoes.put(new Key("q4", "b", ""), new TuplaString("q4", trans6));
		transicoes.put(new Key("q4", "c", ""), new TuplaString("q4", trans7));
		transicoes.put(new Key("q4", "B", ""), new TuplaString("q5", trans8));
		
		estadosFinais = new ArrayList<String>();
		
		estadosFinais.add("q5");
		estadoInicial = "q1";
		
		automatoMTFI = new AutomatoMTFI(transicoes, estadosFinais, estadoInicial, true);
	}

	@Test
	public void testExecutarCadeia() {
		automatoMTFI.iniciar("abc");
		List<Automato> resultadoAutomato= automatoMTFI.executar();
		
		boolean reconheceu = false;
		
		for(Automato a : resultadoAutomato) {
			if(a.extrapolou()) {
				reconheceu = false;
			} else {
				reconheceu = true;
			}
		}
		
		System.out.println("O automato reconheceu: " + reconheceu);
		
	}

}
