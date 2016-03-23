package fitas;

import java.util.ArrayList;

public class FitaLimitada extends Fita {
	
	// Interface de instancializacao
	
	public FitaLimitada() {
		super();
	}
	
	@Override
	protected void iniciarCelulas() {}
	
	// Interface caracteristica
	
	@Override
	public void iniciar(String cadeia) {
		celulas = new ArrayList<Character>();
		celulas.add('<');
		
		for(char c : cadeia.toCharArray())
			celulas.add(c);
		
		celulas.add('>');
		
		cursor = 1;
	}
	
	@Override
	public char ler() {
		return celulas.get(cursor);
	}
	
	@Override
	public void avancar() {
		int n = celulas.size() - 2;
		cursor += (cursor >= 0) & (cursor <= n) ? 1 : 0; // Cursor avanca 1 caso seja maior ou igual a zero e menor ou igual a n
	}
	
	@Override
	public void recuar() {
		int n = celulas.size() - 1;
		cursor -= (cursor >= 1) & (cursor <= n) ? 1 : 0; // Cursor recua 1 caso seja maior do que 0 e menor ou igual a n
	}
				
	// Interface de consulta
		
	@Override
	protected boolean atingiuBOF() {
		return celulas.get(cursor) == '<';
	}
	
	@Override
	protected boolean atingiuEOF() {
		return celulas.get(cursor) == '>';
	}
	
	@Override
	public String configuracao() {
		String prefixo = "";
		String sufixo = "";
		
		int n = celulas.size();
		
		for(int i = 0; i < n; i++) {
			if(i < cursor)
				prefixo += celulas.get(i);
			if(i > cursor)
				sufixo += celulas.get(i);
		}
		
		return String.format("(%d, %s[%c]%s)", cursor, prefixo, celulas.get(cursor), sufixo);
	}
	
	@Override
	public String toString() {
		String cadeia = "";
		
		for(int i = 1; i < celulas.size() - 1; i++)
			cadeia += celulas.get(i);
		
		return cadeia;
	}

}
