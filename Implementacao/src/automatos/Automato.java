package automatos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fitas.Fita;
import servicos.Key;
import servicos.TuplaString;

public abstract class Automato {
	
	protected Map<Key, TuplaString> transicoes;
	protected List<String> estadosFinais;
	protected String estadoCorrente;
	protected Fita entrada;
	
	protected boolean ativarTrace;
	
	public Automato(Map<Key, TuplaString> transicoes, List<String> estadosFinais, String estadoInicial, boolean ativarTrace) {
		instanciarEstruturaEspecifica();
		instanciarEntrada();
		instanciarTransicoes();
		instanciarEstadosFinais(estadosFinais);
		instanciarEstadoCorrente(estadoInicial);
		this.ativarTrace = ativarTrace;
	}
	
	public boolean extrapolou() {
		return false;
	}
	
	// Interface de instancializacao
	
	protected abstract void instanciarEstruturaEspecifica(); // Para Automatos que necessitem de estruturas especificas
	protected abstract void instanciarEntrada(); // Instancia o tipo de fita a ser utilizada
	protected abstract void adicionarTransicoes(Map<Key, TuplaString> transicoes); // Adicionas as transicoes que serao utilizadas
	
	protected void instanciarTransicoes() {
		transicoes = new HashMap<Key, TuplaString>();
	}
	
	private void instanciarEstadosFinais(List<String> estadosFinais) {
		this.estadosFinais = estadosFinais;
	}
	
	private void instanciarEstadoCorrente(String estadoInicial) {
		this.estadoCorrente = estadoInicial;
	}
	
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
	
	public boolean estaEmFinalDeCadeia() {
		return entrada.ler() == '>';
	}
	
	// Interface de movimento
	
	public abstract void moverAutomato(String proximoEstado);
	
	// Interface de clonagem
	
	public void clonar() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	
	public abstract boolean isPilhaVazia();

}
