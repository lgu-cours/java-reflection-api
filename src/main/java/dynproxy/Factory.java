package dynproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import basicproxy.Robot;
import basicproxy.RobotImpl;

public class Factory {

	
	public Proxy getTraceProxy(Object obj, Class<?>[] interfaces )
	{
		ClassLoader loader = obj.getClass().getClassLoader() ;
		InvocationHandler handler = new TraceProxyHandler( obj );
		return (Proxy) Proxy.newProxyInstance(loader, interfaces, handler);
	}
	
	public Calcul getCalcul()
	{
		//return new CalculImpl();
		
		//return new CalculProxy( new CalculImpl() );
		
		
//		ClassLoader loader = this.getClass().getClassLoader() ;		
//		Class[] interfaces = { Calcul.class } ;
//		InvocationHandler handler = new DynaProxy( new CalculImpl() );			
//		Proxy proxy = (Proxy) Proxy.newProxyInstance(loader, interfaces, handler);
		
		// Proxy p = new Proxy(null); // Constructor not visible
		
		Class<?>[] interfaces = { Calcul.class } ; // Liste des interfaces que le proxy doit implementer 
		Proxy proxy = getTraceProxy( new CalculImpl() , interfaces );
		return (Calcul) proxy ;
	}
	
	public Robot getRobot()
	{
		//return new RobotImpl();
		
		Class<?>[] interfaces = { Robot.class } ;
		Proxy proxy = getTraceProxy( new RobotImpl() , interfaces );
		InvocationHandler handler = Proxy.getInvocationHandler(proxy);
		System.out.println("Invocation Handler = " + handler.getClass().getName() ) ;
		return (Robot) proxy ;
	}
	
}
