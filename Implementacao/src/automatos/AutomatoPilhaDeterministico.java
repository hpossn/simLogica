package automatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fitas.FitaLimitada;
import pilhas.Pilha;
import servicos.TuplaString;
import servicos.Key;

public class AutomatoPilhaDeterministico extends Automato{
	

	
	public AutomatoPilhaDeterministico(Map<Key, TuplaString> transicoes, List<String> estadosFinais, String estadoInicial, boolean ativarTrace, List<String> alfabeto) {
		super(transicoes, estadosFinais, estadoInicial, ativarTrace, alfabeto);
		
		adicionarTransicoes(transicoes);
		
	}

	protected Pilha pilha;


	@Override
	public String configuracao() {
		return String.format("(%s, %s)", super.configuracao(), pilha.configuracao());
	}

	@Override
	protected void instanciarEstruturaEspecifica() {
		pilha = new Pilha();
		
	}

	@Override
	protected void instanciarEntrada() {
		entrada = new FitaLimitada(alfabeto);
	}

	@Override
	protected void adicionarTransicoes(Map<Key, TuplaString> transicoes) {
		this.transicoes.putAll(transicoes);
		
	}
	
	@Override
	public boolean isPilhaVazia() {
		return pilha.isVazia();
	}

	@Override
	public List<Automato> executar() {
		boolean finalizaLeitura = false;
		
		while(!finalizaLeitura) {
			TuplaString tupla = transicoes.get(new Key(estadoCorrente, String.valueOf(entrada.ler()), pilha.topo()));
			
			if(ativarTrace)
				System.out.println(configuracao());
			
			if(tupla == null) {
				TuplaString tupla2 = transicoes.get(new Key(estadoCorrente, "", pilha.topo()));
				if(tupla2 == null)
					finalizaLeitura = true;
				else {
					estadoCorrente = tupla2.getEstado();
					pilha.pop();
					pilha.push(tupla2.getPilha());
				}
				
			} else {
				estadoCorrente = tupla.getEstado();
				pilha.pop();
				pilha.push(tupla.getPilha());
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
