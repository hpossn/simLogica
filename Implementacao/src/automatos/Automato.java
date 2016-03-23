package automatos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fitas.Fita;
import servicos.Key;

public abstract class Automato {
	
	protected Map<Key, String> transicoes;
	protected List<String> estadosFinais;
	protected String estadoCorrente;
	protected Fita entrada;
	
	public Automato(Map<Key, String> transicoes, List<String> estadosFinais, String estadoInicial) {
		instanciarEstruturaEspecifica();
		instanciarEntrada();
		instanciarTransicoes();
		adicionarTransicao(transicoes);
		instanciarEstadosFinais(estadosFinais);
		instanciarEstadoCorrente(estadoInicial);
		instanciarServicos();
	}
	
	// Interface de instancializacao
	
	protected abstract void instanciarEstruturaEspecifica(); // Para Automatos que necessitem de estruturas especificas
	protected abstract void instanciarEntrada(); // Instancia o tipo de fita a ser utilizada
	protected abstract void adicionarTransicao(Map<Key, String> transicoes); // Adicionas as transicoes que serao utilizadas
	
	protected void instanciarTransicoes() {
		transicoes = new HashMap<Key, String>();
	}
	
	private void instanciarEstadosFinais(List<String> estadosFinais) {
		this.estadosFinais.addAll(estadosFinais);
	}
	
	private void instanciarEstadoCorrente(String estadoInicial) {
		this.estadoCorrente = estadoInicial;
	}
	
	private void instanciarServicos() {
		instanciarConsulta();
		instanciarMovimentacao();
	}
	
	// Interface de servicos
	
	private void instanciarConsulta() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	protected abstract void instanciarMovimentacao();
	
	// Interface caracteristica
	
	public void iniciar(String cadeia) {
		entrada.iniciar(cadeia);
	}
	
	public abstract List<Automato> executar();
	
	// Interface de consulta
	
	public String configuracao() {
		return String.format("(%s, %s))", estadoCorrente, entrada.configuracao());
	}
	
	public boolean estaEmEstadoFinal() {
		return estadosFinais.contains(estadoCorrente);
	}
	
	// Interface de movimento
	
	public abstract void moverAutomato();
	
	// Interface de clonagem
	
	public void clonar() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
		

}
