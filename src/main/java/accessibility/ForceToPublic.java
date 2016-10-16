package accessibility;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


public class ForceToPublic {

	public static void main(String[] args) {
		
		ClassUtil.exploreClass(Boolean.class);  

		Boolean b1 = new Boolean(true) ;
		
		System.out.println(" b1  : " + b1 + ", b1.booleanValue() : " + b1.booleanValue() );
		
		System.out.println("Change the private field value...");
		
		try {
			Field field = Boolean.class.getDeclaredField("value") ;
			System.out.println(" value modifiers : " + Modifier.toString(field.getModifiers()) );
			//forceToPublic( field ) ;
			System.out.println(" value modifiers : " + Modifier.toString(field.getModifiers()) );
			field.set(b1, false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(" b1  : " + b1 + ", b1.booleanValue() : " + b1.booleanValue() );
		
		ClassUtil.exploreClass(Boolean.class);  
	}

	public static void forceToPublic(Field targetField) throws Exception {

		int targetFieldModifiers = targetField.getModifiers();

		if ( ! Modifier.isPublic( targetFieldModifiers) ) {
			
			// Not public => force it to public 
			Field modifiersField = Field.class.getDeclaredField("modifiers");
			
			modifiersField.setAccessible(true); // Les "modifiers" sont maintenant modifiables
		
			int newModifiers = Modifier.PUBLIC ;
			
			modifiersField.setInt(targetField, newModifiers ) ; // Le champ est public
		}
		
	}
}
