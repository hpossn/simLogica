package pilhas;

import java.util.ArrayList;
import java.util.List;

public class Pilha {
	
	private List <String> pilha;
	
	public Pilha() {
		pilha = new ArrayList<String>();
		pilha.add("Z0");
	}
	
	public String topo() {
		return !pilha.isEmpty() ? pilha.get(pilha.size() - 1) : "";
	}
	
	public String pop() {
		return pilha.remove(pilha.size() - 1);
	}
	
	public void push(List<String> lista) {
		pilha.addAll(lista);
	}
	
	public boolean isVazia() {
		return pilha.isEmpty();
	}
	
	public String configuracao() {
		return "(" + pilha.toString() + ")";
	}

}
