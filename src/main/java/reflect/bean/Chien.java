package reflect.bean;

import java.io.Serializable;

public class Chien extends Canide implements Serializable
{
	private static final long serialVersionUID = 1L;

	public Chien() {
		super("???");
	}
	
	public Chien(String name) {
		super(name);
	}

	public void aboyer()
	{
		System.out.println("waf waf");
	}
	
	public String toString() {
		return "Chien : " + getName() ;
	}

}
