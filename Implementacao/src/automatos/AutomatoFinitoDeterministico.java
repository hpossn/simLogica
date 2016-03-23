package automatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fitas.FitaLimitada;
import servicos.Key;

public class AutomatoFinitoDeterministico extends Automato {

	public AutomatoFinitoDeterministico(Map<Key, String> transicoes, List<String> estadosFinais,
			String estadoInicial) {
		super(transicoes, estadosFinais, estadoInicial);
		
		adicionarTransicoes(transicoes);
		
	}

	@Override
	protected void instanciarEstruturaEspecifica() {}

	@Override
	protected void instanciarEntrada() {
		entrada = new FitaLimitada();
	}

	@Override
	protected void adicionarTransicoes(Map<Key, String> transicoes) {
		this.transicoes.putAll(transicoes);
	}

	@Override
	public List<Automato> executar() {
		boolean finalizaLeitura = false;
		
		while(!finalizaLeitura) {
			String proximoEstado = transicoes.get(new Key(estadoCorrente, String.valueOf(entrada.ler())));
			
			if(proximoEstado == null) {
					finalizaLeitura = true;
			} else {
				estadoCorrente = proximoEstado;
				entrada.avancar();
			}
			
		}
		
		List<Automato> listaDeAutomatos = new ArrayList<Automato>();
		listaDeAutomatos.add(this);
		
		return listaDeAutomatos;
	}

	@Override
	public void moverAutomato(String proximoEstado) {
		estadoCorrente = proximoEstado;
		entrada.avancar();

	}

}
