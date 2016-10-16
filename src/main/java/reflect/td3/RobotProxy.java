package reflect.td3;

public class RobotProxy implements Robot {

	private final Robot robot = new RobotImpl();
	
	public void move(int x, int y) {
		System.out.println("LOG : before move");
		robot.move(x, y);
		System.out.println("LOG : after move");
	}

}
