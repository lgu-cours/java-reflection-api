package basicproxy;

public class Factory {

	public Calcul getCalcul()
	{
		//return new CalculImpl();
		return new CalculProxy();
	}
	
	public Robot getRobot()
	{
		return new RobotImpl();
	}
}
