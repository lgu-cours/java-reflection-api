package reflect.td1;

import java.lang.reflect.Field;

import reflect.lib.td1.Book;

public class TD1ChangeFieldValue {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("\n========== Book :");
		Book book = new Book();
		
		book.setTitle("Germinal");
		book.setAvailability(3);
		
		System.out.println("Title : " + book.getTitle() );
		System.out.println("Availability : " + book.getAvailability() );

		if ( changeFieldValue(book, "_sTitle", "Aaaaaaaa") ) {
			System.out.println("Changed");
		}
		else {
			System.out.println("Cannot change :-(");
		}
		System.out.println("Title : " + book.getTitle() );

		if ( changeFieldValue(book, "_iAvailability", 15) ) {
			System.out.println("Changed");
		}
		else {
			System.out.println("Cannot change :-(");
		}
		System.out.println("Availability : " + book.getAvailability() );
	}

	
	public static boolean changeFieldValue(Object object, String fieldName, Object value) {
		Class<?> c = object.getClass();
		Field field ;
		try {
			//field = c.getField(fieldName); // Not found if "private"
			field = c.getDeclaredField(fieldName);
		} catch (NoSuchFieldException | SecurityException e) {
			System.out.println(e);
			return false ;
		}
		
		try {
			// "private" => use "setAccessible" 
			field.setAccessible(true); 
			field.set(object, value);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			System.out.println(e);
			return false ;
		}	
		return true ;
	}
}
