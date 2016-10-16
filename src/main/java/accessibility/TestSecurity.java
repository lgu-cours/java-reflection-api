package accessibility;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.sun.org.apache.xpath.internal.operations.Bool;

import accessibility.bean.Employee;


public class TestSecurity {

	public static void main(String[] args) {
		
		
		SecurityManager secMan = System.getSecurityManager() ;
		if ( secMan != null ) {
			System.out.println("SecurityManager is present");
		}
		else {
			System.out.println("No SecurityManager");
		}
				
		Employee employee = new Employee(123, "John", "Wayne");
		System.out.println("Salary = " + employee.getSalary() );
		
		Class clazz = Employee.class ;
		
		Field field = null ;
		try {
			//Field field = clazz.getField("salary"); // NoSuchFieldException 
			field = clazz.getDeclaredField("salary");
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Field '" + field.getName() + "' found.");
		
		//double salaryToSet = 1234.56 ;
		try {
			field.setAccessible(true); // A faire pour chaque instance de field
			//field.set(employee, salaryToSet);
			field.setDouble(employee, 1234.56);
			// IllegalAccessException 
			// Class .... can not access a member of class .... with modifiers "private"
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Field '" + field.getName() + "' set.");

		System.out.println("Salary = " + employee.getSalary() );
		
		int fieldModifiers = field.getModifiers();
		
		// pas de methode field.setModifiers(xx);
		
		System.out.println("Field '" + field.getName() + "' modifiers : " + Modifier.toString(fieldModifiers) );
		
		exploreClass(Field.class);
		
		//alterTheFieldClass();
		
		changeTheFieldModifiers( field, Modifier.FINAL + Modifier.PUBLIC );

		exploreClass(Boolean.class);  
		
		System.out.println(" Boolean.TRUE  : " + Boolean.TRUE  );
		System.out.println(" Boolean.FALSE : " + Boolean.FALSE );
		
		
	}

	public static void exploreClass(Class clazz) {
		System.out.println("Class " + clazz.getCanonicalName() );
		System.out.println(" Fields :  "  );
		Field fields[] = clazz.getDeclaredFields() ;
		for ( Field field : fields ) {
			System.out.println("  . " 
					+ Modifier.toString(field.getModifiers())
					+ " " + field.getType().getCanonicalName() 
					+ " " + field.getName() 
					);
		}
		System.out.println(" Methods : " );
		Method methods[] = clazz.getDeclaredMethods() ;
		for ( Method method : methods ) {
			System.out.println("  . " 
					+ Modifier.toString(method.getModifiers())
					+ " " + method.getGenericReturnType() 
					+ " " + method.getName() );
		}
	}
	
	
//	public static void alterTheFieldClass() {
//		
//		Field modifiersField = null ;
//		try {
//			modifiersField = Field.class.getDeclaredField("modifiers");
//		} catch (SecurityException e) {
//			e.printStackTrace();
//		} catch (NoSuchFieldException e) {
//			e.printStackTrace();
//		}
//		
//		modifiersField.setAccessible(true); // Les "modifiers" sont maintenant accessibles
//	}

	public static void changeTheFieldModifiers(Field targetField, int newModifiers) {

		Field modifiersField = null ;
		try {
			modifiersField = Field.class.getDeclaredField("modifiers");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		modifiersField.setAccessible(true); // Les "modifiers" sont maintenant accessibles

		// Modifiers du champ 
		try {
			int targetModifiers = modifiersField.getInt(targetField) ;
			System.out.println("Current modifiers : " + Modifier.toString(targetModifiers) );
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Changement des modifiers du champ 
		try {
			System.out.println("Set new modifiers..." );
			modifiersField.setInt(targetField, newModifiers) ;
			int targetModifiers = modifiersField.getInt(targetField) ;
			System.out.println("Current modifiers : " + Modifier.toString(targetModifiers) );
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);		
	}
}
