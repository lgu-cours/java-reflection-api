package reflect.td2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class TD2Utils {

	public static Object callGetter(Object instance, String getterMethodName) {
		Object result = null ;
		Class<?> c = instance.getClass() ;
		try {
			Method m = c.getMethod(getterMethodName, (Class[]) null);
			if ( m != null )
			{
				System.out.println( getterMethodName + " existe dans la classe " + c.getName() );
				try {
					result = m.invoke(instance, (Object[])null);
					System.out.println( getterMethodName + "() ==> '" + result + "' ");
				} catch (IllegalArgumentException e) {
					System.out.println( getterMethodName + "() ==> ERROR : IllegalArgumentException ");
				} catch (IllegalAccessException e) {
					System.out.println( getterMethodName + "() ==> ERROR : IllegalAccessException ");
					System.out.println("Method " + m.getName() + " : setAccessible(true) ");
					m.setAccessible(true);
					try {
						result = m.invoke(instance, (Object[])null);
						System.out.println( getterMethodName + "() ==> '" + result + "' ");
					} catch (IllegalArgumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvocationTargetException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (InvocationTargetException e) {
					System.out.println( getterMethodName + "() ==> ERROR : InvocationTargetException ");
				}
			}
		} catch (SecurityException e) {
			System.out.println( "SecurityException : " + getterMethodName + " security error" );
		} catch (NoSuchMethodException e) {
			System.out.println( "NoSuchMethodException : " + getterMethodName + " not in the class " + c.getName() );
		}
		return result ;
	}
	

	public static void callSetter(Object instance, String setterMethodName, Object value) {
		Class<?> c = instance.getClass() ;
		Class<?>[] paramTypes  = { value.getClass() } ;
		Object[]   paramValues = { value } ;
		try {
			Method m = c.getMethod(setterMethodName, paramTypes );
			if ( m != null )
			{
				System.out.println( setterMethodName + " existe dans la classe " + c.getName() );
				try {
					m.invoke(instance, paramValues );
					System.out.println( setterMethodName + "() ");
				} catch (IllegalArgumentException e) {
					System.out.println( setterMethodName + "() ==> ERROR : IllegalArgumentException ");
				} catch (IllegalAccessException e) {
					System.out.println( setterMethodName + "() ==> ERROR : IllegalAccessException ");
					System.out.println("Method " + m.getName() + " : setAccessible(true) ");
					m.setAccessible(true);
					try {
						m.invoke(instance, paramValues );
						System.out.println( setterMethodName + "() ");
					} catch (IllegalArgumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvocationTargetException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (InvocationTargetException e) {
					System.out.println( setterMethodName + "() ==> ERROR : InvocationTargetException ");
				}
			}
		} catch (SecurityException e) {
			System.out.println( "SecurityException : " + setterMethodName + " security error" );
		} catch (NoSuchMethodException e) {
			System.out.println( "NoSuchMethodException : " + setterMethodName + " not in the class " + c.getName() );
		}
	}
	
}
