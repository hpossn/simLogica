package reconhecedores;

import java.util.List;
import java.util.Map;

import automatos.Automato;
import servicos.Key;

public abstract class Reconhecedor {
	
	// Estrutura de colaboradores
	
	protected Automato automato;
	protected List<Automato> subEspacoAutomato;
	
	// Interface de instanciacao
	
	public Reconhecedor(String estadoInicial, List<String> estadosFinais, Map<Key, String> transicoes) {
		instanciarEstruturasEspecificas();
		instanciarAutomato(estadoInicial, estadosFinais, transicoes);
	}

	protected abstract void instanciarEstruturasEspecificas();
	protected abstract void instanciarAutomato(String estadoInicial, List<String> estadosFinais, Map<Key, String> transicoes);

	// Interface caracteristica
	
	public void iniciar(String cadeia) {
		automato.iniciar(cadeia);
	}
	
	public void analisar() {
		subEspacoAutomato = automato.executar();
	}
	
	public abstract boolean reconheceu();
}
