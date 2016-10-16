package reflect.td3;

public class CalculProxy implements Calcul {

	private final Calcul calcul = new CalculImpl();
	
	public int add(int a, int b) {
		
		System.out.println("LOG : before add");
		int ret = calcul.add(a,b);
		System.out.println("LOG : after add");
		return ret ;
	}

}
