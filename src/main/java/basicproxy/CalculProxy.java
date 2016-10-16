package basicproxy;

public class CalculProxy implements Calcul {
	
	private CalculImpl c = new CalculImpl();
	
	public int add(int a, int b) {
		System.out.println( "TRACE : before call : add("+a+","+b+")" );
		int r = c.add(a, b) ;
		System.out.println( "TRACE : after call : result = " + r );
		return r ;
	}
	
}
