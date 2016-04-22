package automatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fitas.FitaIlimitada;
import servicos.Key;
import servicos.TuplaString;

public class AutomatoMTFI extends Automato {
	
	private boolean rejeita = false;

	public AutomatoMTFI(Map<Key, TuplaString> transicoes, List<String> estadosFinais,
			String estadoInicial, boolean ativarTrace) {
		super(transicoes, estadosFinais, estadoInicial, ativarTrace);
		
		adicionarTransicoes(transicoes);
		
	}

	@Override
	protected void instanciarEstruturaEspecifica() {}

	@Override
	protected void instanciarEntrada() {
		entrada = new FitaIlimitada();
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
			
			
			if(estaEmEstadoFinal()) {
				finalizaLeitura = true;
			} else if(tupla == null) {
				finalizaLeitura = true;
				rejeita = true;
			} else {
				estadoCorrente = tupla.getEstado();
				entrada.escrever(tupla.getPilha().get(0).charAt(0));
				
				if(tupla.getPilha().get(1).charAt(0) == 'D') {
					try {
						entrada.avancar();
					} catch (IndexOutOfBoundsException e) {
						finalizaLeitura = true;
						rejeita = true;
					}
				}
					
				if(tupla.getPilha().get(1).charAt(0) == 'E')
					entrada.recuar();
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
	public boolean extrapolou() {
		return rejeita;
	}
	
	@Override
	public boolean isPilhaVazia() {
		return true;
	}

}
