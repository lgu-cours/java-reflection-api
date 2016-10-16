package reflect.td3;

public class Factory {

	
	public Calcul getCalcul()
	{
		//return new CalculImpl();
		return new CalculProxy();
	}
	
	public Robot getRobot()
	{
		//return new RobotImpl();
		return new RobotProxy();
	}
	
}
