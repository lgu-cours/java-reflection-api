package reflect.td1;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import reflect.lib.td1.Factory;

public class TD1 {

	public static Class<?> loadClass(String sClassName) {
		Class<?> c = null ;
		try {
			//c = Class.forName("java.lang.String");
			c = Class.forName(sClassName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Class loaded : " + c.getName());
		return c ;
	}
	
	public static void print( Field[] fields ) {
		for ( Field f : fields ) {
			int modifiers = f.getModifiers();
			System.out.println(" . field : " + Modifier.toString(modifiers) + "  " + f.getName() );
		}
	}
	
	public static void print( Method[] methods ) {
		for ( Method m : methods ) {
			int modifiers = m.getModifiers();
			System.out.println(" . method : " + Modifier.toString(modifiers) + "  " + m.getName() );
		}
	}

	public static void describe( Class<?> c) {

		System.out.println( "---" );
		System.out.println(" . name = " + c.getName() );
		System.out.println(" . primitive ? : " + c.isPrimitive() );
		System.out.println(" . interface ? : " + c.isInterface() );
		System.out.println(" . array ?     : " + c.isArray() );
		
		System.out.println( "--- getFields : " );
		print( c.getFields() ) ;

		System.out.println( "--- getDeclaredFields : " );
		print( c.getDeclaredFields() ) ;

		System.out.println( "--- getMethods : " );
		print( c.getMethods() ) ;

		System.out.println( "--- getDeclaredMethods : " );
		print( c.getDeclaredMethods() ) ;

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("\n========== Book :");
		Class<?> c = loadClass("reflect.lib.td1.Book");
		describe ( c );
		
		System.out.println("\n========== Factory getObject() :");
		Factory f = new Factory();
		Object o = f.getObject();
		describe ( o.getClass() );
	}

}
