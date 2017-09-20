package reflect.td4_done;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;

public class ContainerDone {

	private Hashtable<String,Object> beans = new Hashtable<String,Object>();
		
	/**
	 * Adds a BEAN in the container
	 * @param name
	 * @param bean
	 */
	public void addBean(String name, Object bean) {
		beans.put(name, bean);
	}

	/**
	 * Returns the BEAN for the given name
	 * @param name
	 * @return the BEAN if found or null if not found
	 */
	public Object getBean(String name) {
		return beans.get(name);
	}

	public Object getValue(String name ) {
		System.out.println("getValue(" + name + ")");
		// 1) isoler le nom du bean et le nom de l'attribut 
		// ex "chien.name" --> "chien" & "name" 
		String beanName = "" ;
		String attributeName = "" ;
		if ( name.indexOf('.') > 0 ) {
			String[] tokens = name.split("\\.");
			beanName = tokens[0];
			attributeName = tokens[1];
		}
		else {
			throw new RuntimeException("Invalid name '" + name + "'");
		}
		System.out.println("Ready : " + beanName + " - " + attributeName);
		
		// 2) rechercher le bean dans le container 
		Object bean = beans.get(beanName);
		if ( bean == null )
		{
			System.out.println("Bean '" + beanName + "' not found :-( " );
			throw new RuntimeException("Bean '" + beanName + "' not found in the container");
		}
		System.out.println("Bean '" + beanName + "' found :-) " );

		// 3) si bean OK : rechercher le "getter" correspondant au nom de l'attribut
		// ex "name" --> getName()
		Object v = getValue(bean, attributeName);
		
		// 4) call "getter" + return value
		return v ;
	}
	
	private Object getValue(Object instance, String valueName) {
		Class<?> clazz = instance.getClass() ;
		Method method = null ;
		String getterName = getGetterMethodName(valueName);
		try {
			method = clazz.getMethod(getterName, (Class<?>[]) null);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("Cannot get method " + getterName, e );
		} catch (SecurityException e) {
			throw new RuntimeException("Cannot get method " + getterName, e );
		}
		
		Object result = null ;
		try {
			result = method.invoke(instance, (Object[]) null);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Cannot invoke method " + getterName, e );
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Cannot invoke method " + getterName, e );
		} catch (InvocationTargetException e) {
			throw new RuntimeException("Cannot invoke method " + getterName, e );
		}
		
		return result ;
	}
	
	private String getGetterMethodName(String valueName) {
		return "get" + capitalize(valueName);
	}
	
	private String capitalize(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
//	private String uncapitalize(String s) {
//		return s.substring(0, 1).toLowerCase() + s.substring(1);
//	}
}
