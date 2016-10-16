package dynproxy;

import java.lang.reflect.Proxy;

import basicproxy.Robot;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		Factory f = new Factory();
		Calcul c = f.getCalcul();
		//Calcul c = new CalculImpl();

		Robot robot = f.getRobot();

		System.out.println( "--- use Calcul : " ) ;
		System.out.println( " . 2 + 3 = " + c.add(2, 3));

		System.out.println( "--- use Robot : " ) ;
		robot.move(120, 260);
		
		System.out.println( "--- " ) ;
		System.out.println( "is robot a proxy ? " + Proxy.isProxyClass( robot.getClass() ) ) ;
		System.out.println( "is calcul a proxy ? " + Proxy.isProxyClass( c.getClass() ) ) ;
	}

}
