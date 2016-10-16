package basicproxy;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//Calcul calcul = new CalculImpl();
		//Robot robot = new RobotImpl();
		
		Factory f = new Factory();
		Calcul calcul = f.getCalcul();
		Robot robot = f.getRobot();
		
		System.out.println( " . 2 + 3 = " + calcul.add(2, 3));

		robot.move(12, 50);

	}

}
