package reflect.td1;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

import reflect.lib.td1.Book;

public class TD1bis {

	public static Class<?> loadClass(String sClassName) {
		Class<?> c = null ;
		try {
			// Chargement de la classe
			c = Class.forName(sClassName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
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
			for ( Type type : m.getGenericParameterTypes() ) {
				System.out.println("   . type : " + type );
			}
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

		findField(c, "_sTitle") ;
		
		Book book = new Book();
		System.out.println("----------");
		System.out.println("book title : " + book.getTitle());
		setFieldValue(book, "_sTitle", "Germinal");
		System.out.println("book title : " + book.getTitle());
		
	}

	private static Field findField(Class<?> c, String fieldName) {
		System.out.println("Find ... : " + fieldName);
		try {
			//Field field = c.getField(fieldName);
			Field field = c.getDeclaredField(fieldName);
			System.out.println("Field found");
			return field ;
		} catch (NoSuchFieldException e) {
			System.out.println("Field not found : NoSuchFieldException");
			return null ;
		} catch (SecurityException e) {
			System.out.println("Field error : SecurityException ");
			return null ;
		}
	}
	private static boolean setFieldValue(Object obj, String fieldName, String val) {
		try {
			Class<?> c = obj.getClass();
			Field field = c.getDeclaredField(fieldName);
			if ( field != null ) {
				System.out.println("Field found");
				field.setAccessible(true);
				field.set(obj, val);
				return true;
			}
			else {
				return false ;
			}
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e ) {
			throw new RuntimeException(e);
		}
	}
}
