package fitas;

import java.util.ArrayList;
import java.util.List;

public abstract class Fita {
	
	// Estrutura de colaboradores
	protected List<String> celulas;
	protected int cursor;
	
	protected List<String> alfabeto;
	
	// Interface de instancializacao
	
	public Fita(List<String> alfabeto) {
		this.alfabeto = new ArrayList<String>(alfabeto);
		iniciarCelulas();
		
	}
	
	protected abstract void iniciarCelulas();

	// Interface caracteristica
	
	public abstract void iniciar(String cadeia);
	public abstract String ler();
	public abstract void avancar();
	public abstract void recuar();
	
	
	// Interface de consulta
	
	protected abstract boolean atingiuBOF();
	protected abstract boolean atingiuEOF();
	public abstract String configuracao();
	
	// Interface de clonagem
	
	public final Character clonar() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	public abstract void escrever(String valor);
	
}
