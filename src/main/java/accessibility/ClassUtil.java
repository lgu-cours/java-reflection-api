package accessibility;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ClassUtil {

	public static void exploreClass(Class<?> clazz) 
	{
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
	

}
