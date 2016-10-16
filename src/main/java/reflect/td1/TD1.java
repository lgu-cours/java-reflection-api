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
	
	public static void describe( Class<?> c) {

		System.out.println( "---" );
		System.out.println(" . name = " + c.getName() );
		System.out.println(" . primitive ? : " + c.isPrimitive() );
		System.out.println(" . interface ? : " + c.isInterface() );
		System.out.println(" . array ?     : " + c.isArray() );
		
		System.out.println( "--- Fields : " );
		Field[] fields = c.getFields();
		for ( Field f : fields ) {
			int m = f.getModifiers();
			System.out.println(" . field : " + Modifier.toString(m) + "  " + f.getName() );
		}

		System.out.println( "--- Declared Fields : " );
		Field[] declaredFields = c.getDeclaredFields();
		for ( Field f : declaredFields ) {
			int m = f.getModifiers();
			System.out.println(" . field  : " + Modifier.toString(m) + "  " + f.getName()  );
		}

		System.out.println( "--- Methods : " );
		Method[] methods = c.getMethods();
		for ( Method m : methods ) {
			int modifiers = m.getModifiers();
			System.out.println(" . method : " + Modifier.toString(modifiers) + "  " + m.getName() );
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Class<?> c = loadClass("reflect.lib.td1.Book");
		describe ( c );
		
		Factory f = new Factory();
		Object o = f.getObject();
		describe ( o.getClass() );
	}

}
