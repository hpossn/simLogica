package reconhecedores;

import java.util.List;
import java.util.Map;

import automatos.Automato;
import automatos.AutomatoFinitoDeterministico;
import servicos.Key;
import servicos.TuplaString;

public class ReconhecedorFinitoDeterministico extends Reconhecedor {

	public ReconhecedorFinitoDeterministico(String estadoInicial, List<String> estadosFinais, Map<Key, TuplaString> transicoes) {
		super(estadoInicial, estadosFinais, transicoes);
	}

	@Override
	protected void instanciarEstruturasEspecificas() {}

	@Override
	protected void instanciarAutomato(String estadoInicial, List<String> estadosFinais, Map<Key, TuplaString> transicoes) {
		automato = new AutomatoFinitoDeterministico(transicoes, estadosFinais, estadoInicial, true);
	}

	@Override
	public boolean reconheceu() {
		for(Automato a : subEspacoAutomato) {
			if (a.estaEmEstadoFinal())
				return a.estaEmFinalDeCadeia();
		}
		
		return false;
	}

}
