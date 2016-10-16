package accessibility;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


public class ForceFinalValue {

	public static void main(String[] args) {
		
		ClassUtil.exploreClass(Boolean.class);  

		Boolean b1 = new Boolean(true) ;
		
		System.out.println(" b1  : " + b1 + ", b1.booleanValue() : " + b1.booleanValue() );
		
		System.out.println("Change the private field value...");
		
		try {
			forceFinalFieldValue( b1, "value", false ) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(" b1  : " + b1 + ", b1.booleanValue() : " + b1.booleanValue() );
	}

	
	public static void forceFinalFieldValue(Object instance, String fieldName, boolean newValue) throws Exception {

		Field targetField = instance.getClass().getDeclaredField(fieldName) ;
			
		int targetFieldModifiers = targetField.getModifiers();
		if ( Modifier.isFinal(targetFieldModifiers) ) System.out.println(" field is 'final' ");
		if ( Modifier.isPrivate(targetFieldModifiers) ) System.out.println(" field is 'private' ");
		
		targetField.setAccessible(true) ;

		System.out.println(" original value = " + targetField.get(instance) );
		
		System.out.println(" set value to " + newValue);
		
		targetField.set(instance, newValue) ;
	}
	
}
