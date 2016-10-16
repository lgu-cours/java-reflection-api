package dynproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TraceProxyHandler implements InvocationHandler {

	private Object obj = null ;
	
	public TraceProxyHandler(Object obj) {
		this.obj = obj;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable 
	{
		Class<?> proxyClass = proxy.getClass();
		
		
		System.out.println("Dynamic proxy : invoke() : "
				+ "proxy = " + proxy.getClass().getName() 
				+ ", method " + method.getName() );
		
		System.out.println("Dynamic proxy : invoke() : is proxy synthetic ? " + proxyClass.isSynthetic() ) ;
				
		Object result = method.invoke(obj, args);
		
		return result;
	}

}
