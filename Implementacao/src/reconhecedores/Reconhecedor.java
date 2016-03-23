package reconhecedores;

import java.util.List;

import automatos.Automato;

public abstract class Reconhecedor {
	
	// Estrutura de colaboradores
	
	protected Automato automato;
	protected List<Automato> subEspacoAutomato;
	
	// Interface de instanciacao
	
	public Reconhecedor(String estadoInicial, List<String> estadosFinais) {
		instanciarEstruturasEspecificas();
		instanciarAutomato(estadoInicial, estadosFinais);
	}

	protected abstract void instanciarEstruturasEspecificas();
	protected abstract void instanciarAutomato(String estadoInicial, List<String> estadosFinais);

	// Interface caracteristica
	
	public void iniciar(String cadeia) {
		automato.iniciar(cadeia);
	}
	
	public void analisar() {
		subEspacoAutomato.addAll(automato.executar());
	}
	
	public abstract boolean reconheceu();
}
