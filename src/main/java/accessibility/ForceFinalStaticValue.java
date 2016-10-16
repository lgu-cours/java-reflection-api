package accessibility;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


public class ForceFinalStaticValue {

	public static void main(String[] args) {
		
		System.out.println(" Boolean.TRUE  : " + Boolean.TRUE  );
		System.out.println(" Boolean.FALSE : " + Boolean.FALSE );
		System.out.println(" Boolean.valueOf(true)  : " + Boolean.valueOf(true)  );
		System.out.println(" Boolean.valueOf(false) : " + Boolean.valueOf(false) );
		
		ClassUtil.exploreClass(Boolean.class);  
		
		try {
			forceFinalStaticFieldValue( Boolean.class, "TRUE", false ) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(" Boolean.TRUE  : " + Boolean.TRUE  );
		System.out.println(" Boolean.FALSE : " + Boolean.FALSE );
		System.out.println(" Boolean.valueOf(true)  : " + Boolean.valueOf(true)  );
		System.out.println(" Boolean.valueOf(false) : " + Boolean.valueOf(false) );
	}

	
	public static void forceFinalStaticFieldValue(Class<?> clazz, String fieldName, boolean newValue) throws Exception {

		Field field = clazz.getDeclaredField(fieldName) ;
		
		field.setAccessible(true) ;
		
		int fieldModifiers = field.getModifiers();
		
		if ( Modifier.isStatic(fieldModifiers) ) {
			if ( Modifier.isFinal(fieldModifiers) ) {
				removeFinalModifier(field);
			}
		}
		// Affectation de la nouvelle valeur 
		field.set(null, newValue) ;
	}
	
	public static void removeFinalModifier(Field targetField) throws Exception {

		Field modifiersField = Field.class.getDeclaredField("modifiers");
		
		modifiersField.setAccessible(true); // Les "modifiers" sont maintenant accessibles

		int targetFieldModifiers = targetField.getModifiers();
		
		modifiersField.setInt(targetField, targetFieldModifiers & ( ~Modifier.FINAL ) ) ; // Le champ n'est plus "final"
		
	}
}
