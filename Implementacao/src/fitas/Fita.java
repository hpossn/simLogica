package fitas;

import java.util.List;

public abstract class Fita {
	
	// Estrutura de colaboradores
	protected List<Character> celulas;
	protected int cursor;
	
	// Interface de instancializacao
	
	public Fita() {
		iniciarCelulas();
	}
	
	protected abstract void iniciarCelulas();

	// Interface caracteristica
	
	public abstract void iniciar(String cadeia);
	public abstract char ler();
	public abstract void avancar();
	public abstract void recuar();
	
	
	// Interface de consulta
	
	protected abstract boolean atingiuBOF();
	protected abstract boolean atingiuEOF();
	public abstract String configuracao();
	
	// Interface de clonagem
	
	public final String clonar() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
}
