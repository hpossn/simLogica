package fitas;

import java.util.ArrayList;

public class FitaIlimitada extends Fita {
	
	private int tamMaxB = 50; // teoricamente seria ilimitado, mas para a simulacao, estabelece-se um limite
	
	// Interface de instancializacao
	
	public FitaIlimitada() {
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
		
		celulas.add('B');
		tamMaxB--;
		
		cursor = 1;
	}
	
	@Override
	public char ler() {
		return celulas.get(cursor);
	}
	
	@Override
	public void avancar() throws IndexOutOfBoundsException {
		if(cursor == celulas.size() - 1) {
			celulas.add('B');
			tamMaxB--;
		}
		
		if(tamMaxB == 0) throw new IndexOutOfBoundsException("Limite da fita");
		cursor++;
	}
	
	@Override
	public void recuar() {
		int n = celulas.size() - 1;
		cursor -= (cursor >= 1) & (cursor <= n) ? 1 : 0; // Cursor recua 1 caso seja maior do que 0 e menor ou igual a n
	}
	
	public void escrever(char c) {
		celulas.remove(cursor);
		celulas.add(cursor, c);
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
		
		return String.format("(%d, %s[%c]%s", cursor, prefixo, celulas.get(cursor), sufixo);
	}
	
	@Override
	public String toString() {
		String cadeia = "";
		
		for(int i = 1; i < celulas.size() - 1; i++)
			cadeia += celulas.get(i);
		
		return cadeia;
	}

}
