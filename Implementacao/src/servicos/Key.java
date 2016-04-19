package servicos;

import java.util.Objects;

public class Key {
	
	protected final String x;
	protected final String y;
	protected final String z;
	
	public Key(String x, String y, String z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof Key)) return false;
		
		Key key = (Key) o;
		
		return this.x.equals(key.x) && this.y.equals(key.y) && this.z.equals(key.z);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}

}
