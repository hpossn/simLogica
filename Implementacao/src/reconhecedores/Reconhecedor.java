package reconhecedores;

import java.util.List;
import java.util.Map;

import automatos.Automato;
import servicos.Key;
import servicos.TuplaString;

public abstract class Reconhecedor {
	
	// Estrutura de colaboradores
	
	protected Automato automato;
	protected List<Automato> subEspacoAutomato;
	
	// Interface de instanciacao
	
	public Reconhecedor(String estadoInicial, List<String> estadosFinais, Map<Key, TuplaString> transicoes, List<String> alfabeto) {
		instanciarEstruturasEspecificas();
		instanciarAutomato(estadoInicial, estadosFinais, transicoes, alfabeto);
	}

	protected abstract void instanciarEstruturasEspecificas();
	protected abstract void instanciarAutomato(String estadoInicial, List<String> estadosFinais, Map<Key, TuplaString> transicoes, List<String> alfabeto);

	// Interface caracteristica
	
	public void iniciar(String cadeia) {
		automato.iniciar(cadeia);
	}
	
	public void analisar() {
		subEspacoAutomato = automato.executar();
	}
	
	public abstract boolean reconheceu();
}
