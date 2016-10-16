package accessibility;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Modification d'une constante de la classe Boolean avec la Reflexion API
 * Copyright (c) 2012 Laurent Guerin - Licence LGPL v3
 */
public class ChangeBooleanTRUE {

	public static void main(String[] args) {
		
		System.out.println("=== AVANT : ");
		System.out.println(" Boolean.TRUE  : " + Boolean.TRUE  );
		System.out.println(" Boolean.FALSE : " + Boolean.FALSE );
		System.out.println(" Boolean.valueOf(true)  : " + Boolean.valueOf(true)  );
		System.out.println(" Boolean.valueOf(false) : " + Boolean.valueOf(false) );
		
		try {
			changeTRUEConstant( false ) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("=== APRES : ");
		System.out.println(" Boolean.TRUE  : " + Boolean.TRUE  ); // false (!)
		System.out.println(" Boolean.FALSE : " + Boolean.FALSE );
		System.out.println(" Boolean.valueOf(true)  : " + Boolean.valueOf(true)  ); // false (!)
		System.out.println(" Boolean.valueOf(false) : " + Boolean.valueOf(false) );
	}

	
	/**
	 * Change la valeur de la constante TRUE (!) 
	 * @param newValue
	 * @throws Exception
	 */
	public static void changeTRUEConstant( boolean newValue ) throws Exception {

		// La constante TRUE ( final static ) 
		Field field = Boolean.class.getDeclaredField("TRUE") ;
		
		field.setAccessible(true) ;
		
		int fieldModifiers = field.getModifiers();
		
		if ( Modifier.isStatic(fieldModifiers) ) {
			if ( Modifier.isFinal(fieldModifiers) ) {
				// Ce champ est "final static" : il faut supprimer "final"
				removeFinalModifier(field);
			}
		}
		// Affectation de la nouvelle valeur 
		field.set(null, newValue) ;
	}
	
	/**
	 * Supprime le modifier "final" du champ 
	 * @param targetField
	 * @throws Exception
	 */
	public static void removeFinalModifier(Field targetField) throws Exception {

		Field modifiersField = Field.class.getDeclaredField("modifiers");
		
		modifiersField.setAccessible(true); // Les "modifiers" sont maintenant accessibles

		int targetFieldModifiers = targetField.getModifiers();
		
		modifiersField.setInt(targetField, targetFieldModifiers & ( ~Modifier.FINAL ) ) ; // Le champ n'est plus "final"
		
	}
}
