package fitas;

import java.util.ArrayList;
import java.util.List;

public class FitaLimitada extends Fita {
	
	// Interface de instancializacao
	
	public FitaLimitada(List<String> alfabeto) {
		super(alfabeto);
	}
	
	@Override
	protected void iniciarCelulas() {}
	
	// Interface caracteristica
	
	@Override
	public void iniciar(String cadeia) {
		celulas = new ArrayList<String>();
		celulas.add("<");
		
		int index = 0;

		while(index < cadeia.length()) {
			boolean procura = true;
			for(int deslocamento = index; procura; deslocamento++) {
				String subString = cadeia.substring(index, deslocamento + 1);

				if(alfabeto.contains(subString)) {
					celulas.add(subString);
					index = deslocamento + 1;
					procura = false;
				}	
			}
		}
		
		celulas.add(">");
		
		cursor = 1;
	}
	
	@Override
	public String ler() {
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
		return celulas.get(cursor).equals("<");
	}
	
	@Override
	protected boolean atingiuEOF() {
		return celulas.get(cursor).equals(">");
	}
	
	@Override
	public String configuracao() {
		StringBuilder prefixo = new StringBuilder();
		StringBuilder sufixo = new StringBuilder();
		
		int n = celulas.size();
		
		for(int i = 0; i < n; i++) {
			if(i < cursor)
				prefixo.append(celulas.get(i));
			if(i > cursor)
				sufixo.append(celulas.get(i));
		}
		
		return String.format("(%d, %s[%s]%s", cursor, prefixo, celulas.get(cursor), sufixo);
	}
	
	@Override
	public String toString() {
		String cadeia = "";
		
		for(int i = 1; i < celulas.size() - 1; i++)
			cadeia += celulas.get(i);
		
		return cadeia;
	}

	@Override
	public void escrever(String c) {}

}
