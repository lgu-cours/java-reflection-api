package accessibility;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Modification d'une instance de String avec la Reflexion API
 * Copyright (c) 2012 Laurent Guerin - Licence LGPL v3
 */
public class ChangeString {

	public static void main(String[] args) {
		
		String s = "Hello world !" ;
		String s2 = new String(s) ;
		
		System.out.println("s = " + s  + " s.hashCode = " + s.hashCode());
		System.out.println("s2 = " + s  + " s2.hashCode = " + s2.hashCode());
		System.out.println("s == s2 ?  " + ( s == s2 ) );
		System.out.println("s.equals(s2) ?  " + ( s.equals(s2) ) );
		
		try {
			System.out.println("Convert to uppercase...");
			toUpperCase( s ) ;
			System.out.println("s  = " + s  + " hashCode = " + s.hashCode());
			
			System.out.println("Change value ...");
			populate(s, "ABCDEFGHIJ") ;
			System.out.println("s = " + s  );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("s  = " + s  + " s.hashCode = " + s.hashCode());
		System.out.println("s2 = " + s2  + " s2.hashCode = " + s2.hashCode());
		System.out.println("s.equals(s2) ?  " + ( s.equals(s2) ) );
		System.out.println("s.equals('ABCDEFGHIJ') ?  " + ( s.equals("ABCDEFGHIJ") ) );
	}

	/**
	 * Converti la chaine en majuscules
	 * @param s 
	 * @throws Exception
	 */
	public static void toUpperCase ( String s ) throws Exception {

		// Les caracteres qui composent une String sont dans le champ "value" (private)  
		// qui pointe sur un tableaux de "char" 
		Field valueField = String.class.getDeclaredField("value");
		// Hash code de la chaine 
		Field hashField   = String.class.getDeclaredField("hash"); 
			
		int targetFieldModifiers = valueField.getModifiers();
		if ( Modifier.isFinal(targetFieldModifiers) ) System.out.println(" field is 'final' ");
		if ( Modifier.isPrivate(targetFieldModifiers) ) System.out.println(" field is 'private' ");
		
		valueField.setAccessible(true) ; 
		hashField.setAccessible(true) ;

		String sUpperCase = s.toUpperCase() ;
		char[] newValue = sUpperCase.toCharArray(); // Same array size => no pb

		valueField.set(s, newValue ); 
		hashField.setInt(s, newValue.hashCode()); // Hash code
		// La conversion en majuscules conserve le meme nombre de caracteres 
		// il n'y a donc rien d'autre a changer
	}
	
	/**
	 * Change la valeur d'une chaine
	 * @param s chaine a modifier
	 * @param newString chaine a affecter
	 * @throws Exception
	 */
	public static void populate ( String s, String newString ) throws Exception {
		// Caracteres de la chaine
		Field valueField  = String.class.getDeclaredField("value");
		// Offset sur le tableau de char
		Field offsetField = String.class.getDeclaredField("offset");
		// Nombre de caracteres dans le tableau
		Field countField  = String.class.getDeclaredField("count");
		// Hash code de la chaine 
		Field hashField   = String.class.getDeclaredField("hash"); 
		
		valueField.setAccessible(true) ;
		offsetField.setAccessible(true) ;
		countField.setAccessible(true) ;
		hashField.setAccessible(true) ;

		// Affichage des valeurs d'origine 
		char[] value = (char[]) valueField.get(s);
		String sValue = new String(value);
		System.out.println("value  = '" + sValue + "' ( length = " + value.length + ", hashCode = " + s.hashCode() + " )" );
		System.out.println("offset = " + offsetField.getInt(s) );
		System.out.println("count  = " + countField.getInt(s) );
		System.out.println("hash   = " + hashField.getInt(s) );

		// Nouvelle chaine a affecter 
		String newValue = newString ;
		valueField.set(s, newValue.toCharArray() );
		// Le nombre de caracteres change => mettre a jour les autres informations 
		offsetField.setInt(s, 0); // Offset : on demarre a 0
		countField.setInt(s, newValue.length()); // Nombre de caracteres
		hashField.setInt(s, newValue.hashCode()); // Hash code		
	}
}
