package reflect.td4;

import reflect.bean.Chat;
import reflect.bean.Chien;
import reflect.bean.Voiture;

public class TestContainer {

	private static void printValue(Container container, String s)
	{
		try {
			Object val = container.getValue(s);
			System.out.println("value of '" + s + "' : " + val );
		} catch (RuntimeException e) {
			System.out.println("value of '" + s + "' : ERROR " + e.getMessage() );
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Container container = new Container();
		container.addBean("chien", new Chien("medor") );
		container.addBean("chat", new Chat("felix"));
		container.addBean("voiture", new Voiture("Peugeot", "205", 1995, 1234.50 ));
		
		printValue(container, "chien.name");
		printValue(container, "voiture.prix");
		printValue(container, "voiture.modele");
	}

}
