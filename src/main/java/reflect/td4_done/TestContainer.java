package reflect.td4_done;

import reflect.bean.Chat;
import reflect.bean.Chien;
import reflect.bean.Voiture;

public class TestContainer {
	
	private static void searchObject(ContainerDone container, String name) {
		System.out.println("\nSearching '" + name + "' in container...");
		Object o = container.getBean(name);
		if ( o != null ) {
			System.out.println("Found : class is " + o.getClass().getCanonicalName() );
		}
		else {
			System.out.println("Not found :-(");
		}
	}

	private static void printValue(ContainerDone container, String s)
	{
		System.out.println("---");
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
		
		ContainerDone container = new ContainerDone();
		container.addBean("chien", new Chien("medor") );
		container.addBean("chat", new Chat("felix"));
		container.addBean("voiture", new Voiture("Peugeot", "205", 1995, 1234.50 ));
		
		searchObject(container, "chien");
		searchObject(container, "voiture");
		searchObject(container, "aaaaaaa");

		printValue(container, "chien.name");
		printValue(container, "voiture.prix");
		printValue(container, "voiture.modele");
	}

}
