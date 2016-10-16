package reflect.td3;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		Factory f = new Factory();

		Calcul calc = f.getCalcul();

		Robot robot = f.getRobot();

		System.out.println( "--- use Calcul : " ) ;
		System.out.println( " . 2 + 3 = " + calc.add(2, 3));

		System.out.println( "--- use Robot : " ) ;
		robot.move(120, 260);
		
		System.out.println( "--- " ) ;
	}

}
