package automatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fitas.FitaLimitada;
import servicos.Key;
import servicos.TuplaString;

public class AutomatoFinitoDeterministico extends Automato {
	

	public AutomatoFinitoDeterministico(Map<Key, TuplaString> transicoes, List<String> estadosFinais,
			String estadoInicial, boolean ativarTrace, List<String> alfabeto) {
		super(transicoes, estadosFinais, estadoInicial, ativarTrace, alfabeto);
		
		adicionarTransicoes(transicoes);
		
	}

	@Override
	protected void instanciarEstruturaEspecifica() {}

	@Override
	protected void instanciarEntrada() {
		entrada = new FitaLimitada(alfabeto);
	}

	@Override
	protected void adicionarTransicoes(Map<Key, TuplaString> transicoes) {
		this.transicoes.putAll(transicoes);
	}

	@Override
	public List<Automato> executar() {
		boolean finalizaLeitura = false;
		
		while(!finalizaLeitura) {
			TuplaString tupla = transicoes.get(new Key(estadoCorrente, String.valueOf(entrada.ler()), ""));
			
			if(ativarTrace)
				System.out.println(configuracao());
			
			if(tupla == null) {
					finalizaLeitura = true;
			} else {
				estadoCorrente = tupla.getEstado();
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
	
	@Override
	public boolean isPilhaVazia() {
		return true;
	}

}
