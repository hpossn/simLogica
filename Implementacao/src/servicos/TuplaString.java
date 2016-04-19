package servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TuplaString {
	
	protected final String x;
	protected final List<String> y;
	
	public TuplaString(String estado, List<String> pilha) {
		this.x = estado;
		this.y = new ArrayList<String>(pilha);
	}
	
	public String getEstado() {
		return x;
	}
	
	public List<String> getPilha() {
		return new ArrayList<String>(y);
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof TuplaString)) return false;
		
		TuplaString tupla = (TuplaString) o;
		
		return this.x.equals(tupla.x) && this.y.equals(tupla.y);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

}
