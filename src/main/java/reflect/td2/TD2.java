package reflect.td2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import reflect.lib.td2.Client;
import reflect.lib.td2.Factory;
import reflect.lib.td2.IDescribe;
import reflect.lib.td2.Personne;


public class TD2 {

	public static Object create( Class<?> c, String p1, String p2 ) {
		
		System.out.println("create class = " + c.getName() );
		
		Constructor<?> constructor = null ;
		if ( c != null )
		{
			Class<?>[] parameterTypes = new Class[2];
			parameterTypes[0] = String.class ;
			parameterTypes[1] = String.class ;
			try {
				constructor = c.getConstructor(parameterTypes);
			} catch (SecurityException e) {
				System.out.println( "create : getConstructor() ==> ERROR : SecurityException ");
			} catch (NoSuchMethodException e) {
				System.out.println( "create : getConstructor() ==> ERROR : NoSuchMethodException ");
			}
		}
		
		Object ret = null ;
		if ( constructor != null )
		{
			Object[] params = new Object[2];
			params[0] = p1 ;
			params[1] = p2 ;
			try {
				ret = constructor.newInstance(params);
				// ERROR : can not access a member of class reflect.lib.td2.Employe with modifiers "public"
				// because Employe visibility is "package"

			} catch (IllegalArgumentException e) {
				System.out.println( "create : constructor.newInstance() ==> ERROR : IllegalArgumentException ");
			} catch (InstantiationException e) {
				System.out.println( "create : constructor.newInstance() ==> ERROR : InstantiationException ");
			} catch (IllegalAccessException e) {
				System.out.println( "create : constructor.newInstance() ==> ERROR : IllegalAccessException ");
			} catch (InvocationTargetException e) {
				System.out.println( "create : constructor.newInstance() ==> ERROR : InvocationTargetException ");
			}
		}
		
		return ret ;
	}
	
	public static Object create( Class<?> c, int i ) {
		
		System.out.println("create class = " + c.getName() );
		
		Constructor<?> constructor = null ;
		if ( c != null )
		{
			Class<?>[] parameterTypes = new Class[1];
			parameterTypes[0] = int.class ;
			try {
				constructor = c.getConstructor(parameterTypes);
			} catch (SecurityException e) {
				System.out.println( "create : getConstructor() ==> ERROR : SecurityException ");
			} catch (NoSuchMethodException e) {
				System.out.println( "create : getConstructor() ==> ERROR : NoSuchMethodException ");
			}
		}
		
		Object ret = null ;
		if ( constructor != null )
		{
			Object[] params = new Object[1];
			params[0] = i ;
			try {
				ret = constructor.newInstance(params);
			} catch (IllegalArgumentException e) {
				System.out.println( "create : constructor.newInstance() ==> ERROR : IllegalArgumentException ");
			} catch (InstantiationException e) {
				System.out.println( "create : constructor.newInstance() ==> ERROR : InstantiationException ");
			} catch (IllegalAccessException e) {
				System.out.println( "create : constructor.newInstance() ==> ERROR : IllegalAccessException ");
			} catch (InvocationTargetException e) {
				System.out.println( "create : constructor.newInstance() ==> ERROR : InvocationTargetException ");
			}
		}
		
		return ret ;
	}
	
//	public static void hierarchie(Object o) {
//		System.out.println( o.getClass() );
//		hierarchie()
//	}
	
	public static void hierarchie(Class<?> clazz, int level ) {
		
		if ( clazz != null ) {
			System.out.print( " " );
			for ( int i = 0 ; i < level ; i++ ) {
				System.out.print( "." );
			}
			System.out.println( clazz.getCanonicalName());
			hierarchie( clazz.getSuperclass(), level+1);
		}
	}
	
	public static void showFields(Class<?> c) {
		System.out.println(" Fields for " + c.getCanonicalName() );
		Field[] fields = c.getDeclaredFields();
		for ( Field f : fields ) {
			int m = f.getModifiers();
			
			System.out.println(" . " + f.getName() + " " + Modifier.toString(m) );			
		}
	}
	
	public static void showFieldSalaire(Object obj) {
		Class<?> c = obj.getClass();
		System.out.println(" showFieldSalaire for " + c.getCanonicalName() );
		Field[] fields = c.getDeclaredFields();
		for ( Field f : fields ) {
			if ( "Salaire".equals(f.getName()) ){
				System.out.println("Salaire found : type = " + f.getType() );
				
				//TODO : cf SecurityManager
				//f.setAccessible(true);
				
				double val = 0 ;
				try {
					val = f.getDouble(obj);
					System.out.println("Value = " + val ) ;
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					f.setDouble(obj,9999.00);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static void instance(Object p) {
		System.out.println("--- Instance type : " + p.getClass().getName() );
		// Tenter d'utiliser instanceof sur p1 et p2 
		if ( p instanceof Personne )
		{
			System.out.println("p is an instance of Personne ");
		}
		if ( p instanceof Client )
		{
			System.out.println("p is an instance of Client ");
		}
		
//		if ( p instanceof Employe ) // Cannot be resolved to a type ( visibility "package" )
//		{
//			System.out.println("p is an instance of Employe ");
//		}
		
		if ( p instanceof IDescribe )
		{
			System.out.println("p is an instance of IDescribe ");
			IDescribe d = (IDescribe) p ;
			System.out.println( d.desc() );
		}
	}
	
	public static void getSalaire(Object p) {
		Class<?> c = p.getClass() ;
		try {
			Method m = c.getMethod("getSalaire", (Class[]) null);
			if ( m != null )
			{
				System.out.println("getSalaire existe dans la classe " + c.getName() );
				try {
					Object salaire = m.invoke(p, (Object[])null);
					System.out.println(" Salaire = " + salaire );
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// can not access a member of class reflect.lib.td2.Employe with modifiers "public"
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			System.out.println("getSalaire n'existe pas dans la classe " + c.getName() );
			//e.printStackTrace();
		}
	}
	
	public static void callGetter(String methodName, Object p) {
		Class<?> c = p.getClass() ;
		try {
			Method m = c.getMethod(methodName, (Class[]) null);
			if ( m != null )
			{
				System.out.println( methodName + " existe dans la classe " + c.getName() );
				try {
					Object ret = m.invoke(p, (Object[])null);
					System.out.println( methodName + "() ==> '" + ret + "' ");
				} catch (IllegalArgumentException e) {
					System.out.println( methodName + "() ==> ERROR : IllegalArgumentException ");
				} catch (IllegalAccessException e) {
					System.out.println( methodName + "() ==> ERROR : IllegalAccessException ");
					System.out.println("Method " + m.getName() + " : setAccessible(true) ");
					m.setAccessible(true);
					try {
						Object ret = m.invoke(p, (Object[])null);
						System.out.println( methodName + "() ==> '" + ret + "' ");
					} catch (IllegalArgumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvocationTargetException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (InvocationTargetException e) {
					System.out.println( methodName + "() ==> ERROR : InvocationTargetException ");
				}
			}
		} catch (SecurityException e) {
			System.out.println( "SecurityException : " + methodName + " security error" );
		} catch (NoSuchMethodException e) {
			System.out.println( "NoSuchMethodException : " + methodName + " not in the class " + c.getName() );
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Factory f = new Factory();
		Personne p1 = f.getPersonne(1);
		Personne p2 = f.getPersonne(2);
		
		System.out.println("----------");
		System.out.println(" Hierarchie de P1 : ");
		hierarchie(p1.getClass(), 1 );
		showFields(p1.getClass());
		showFieldSalaire(p1);
		showFieldSalaire(p1);
		System.out.println("----------");
		
		System.out.println(" Hierarchie de P2 : ");
		hierarchie(p2.getClass(), 1 );
		showFields(p2.getClass());
		showFieldSalaire(p2);
		showFieldSalaire(p2);
		
		
		System.out.println("----------");
		// Tenter d'utiliser instanceof sur p1 et p2 
		instance(p1);
		instance(p2);
		
		System.out.println("----------");
		callGetter("getNom", p1);
		callGetter("getNom", p2);
		System.out.println(" p1.getNom : " + TD2Utils.callGetter(p1, "getNom") ) ;
		System.out.println(" p2.getNom : " + TD2Utils.callGetter(p2, "getNom") ) ;
		System.out.println("----------");
		callGetter("getPrenom", p1);
		callGetter("getPrenom", p2);
		System.out.println("----------");
		callGetter("getSalaire", p1);
		callGetter("getSalaire", p2);
		
		
		
		System.out.println("----------");
		// Try to get the constructor and use it to create a new instance
		Object o1 = create( p1.getClass(), 456 );
		System.out.println( " o1 = " + o1 );
		
		Object o2 = create( p2.getClass(), "Toto", "Yoyo" ); 
		// IllegalAccessException : class visibility = package
		System.out.println( " o2 = " + o2 );
	}

}
