package testes;

import org.junit.Test;

import servicos.Key;

public class TesteKey {

	@Test
	public void test() {
		Key key1 = new Key("q0", "1");
		Key key2 = new Key("q0", "1");
		
		System.out.println("key1: " + key1.hashCode());
		System.out.println("key1: " + key2.hashCode());
		
		System.out.println("iguals: " + key1.equals(key2));
	}

}
