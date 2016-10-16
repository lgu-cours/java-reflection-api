package accessibility;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Modification d'une instance de Boolean avec la Reflexion API
 * Copyright (c) 2012 Laurent Guerin - Licence LGPL v3
 */
public class ChangeBoolean {

	public static void main(String[] args) {
		
		Boolean b1 = new Boolean(true) ;
		
		System.out.println(" b1  : " + b1 + ", b1.booleanValue() : " + b1.booleanValue() );
		
		try {
			changeValue( b1, false ) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(" b1  : " + b1 + ", b1.booleanValue() : " + b1.booleanValue() );		
	}

	/**
	 * Change la valeur d'un booleen
	 * @param instance du booleen a modifier 
	 * @param newValue valeur a affecter
	 * @throws Exception
	 */
	public static void changeValue(Boolean instance, boolean newValue) throws Exception {

		// La valeur est portee par le champ "value" de la classe "Boolean"
		Field field = Boolean.class.getDeclaredField("value") ;
			
		int targetFieldModifiers = field.getModifiers();
		if ( Modifier.isFinal(targetFieldModifiers) ) System.out.println(" field is 'final' ");
		if ( Modifier.isPrivate(targetFieldModifiers) ) System.out.println(" field is 'private' ");
		// Le champ "value" est "final" et "private"
		
		field.setAccessible(true) ;

		System.out.println(" original value : " + field.get(instance) );
		System.out.println(" change value to : " + newValue);
		field.set(instance, newValue) ;
	}
}
