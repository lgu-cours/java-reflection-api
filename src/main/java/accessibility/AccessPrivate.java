package accessibility;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import accessibility.bean.Employee;


public class AccessPrivate {

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
		
		Field field = null ;
		try {
			field = Employee.class.getDeclaredField("salary"); 
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		
		System.out.println("Field '" + field.getName() + "' found.");
		
		try {
			field.setAccessible(true);
			field.setDouble(employee, 1234.56);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		System.out.println("Field '" + field.getName() + "' set.");

		System.out.println("Salary = " + employee.getSalary() );
		
		int fieldModifiers = field.getModifiers();
		
		// pas de methode field.setModifiers(xx);
		
		System.out.println("Field '" + field.getName() + "' modifiers : " + Modifier.toString(fieldModifiers) );
		
		ClassUtil.exploreClass(Field.class);
		
	}

}
