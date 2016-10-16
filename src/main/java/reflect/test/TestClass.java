package reflect.test;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;

import reflect.bean.Animal;
import reflect.bean.Chat;
import reflect.bean.Chien;

public class TestClass {
	
	public static void test1() {
		Class<String[]> c = String[].class ;  	
	}
	
	public static void setField(Object obj, String fieldName, Object val ) {
		if ( obj == null ) return ;
		Class<?> c = obj.getClass() ;
		
		Field f = null ;
		try {
			f = c.getField(fieldName) ;
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if ( f != null )
		{
			try {
				f.set(obj, val) ;
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static Object get(Object obj, String propName ) {
		if ( obj == null ) return null ;
		Class<?> c = obj.getClass() ;
		
		String methodName = "get" + propName ;
		
		Method m = null ;
		Object ret = null ;
		try {
			m = c.getMethod(methodName, (Class<?>[]) null );
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
		if ( m != null )
		{
			try {
				ret = m.invoke(obj, (Object[]) null);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
		return ret ;
	}

	public static void set(Object obj, String propName, Object val ) {
		if ( obj == null ) return ;
		Class<?> c = obj.getClass() ;
		
		String methodName = "set" + propName ;
		
		Method m = null ;
		try {
			Class<?>[] args = new Class<?>[1];
			args[0] = val.getClass() ;
			m = c.getMethod(methodName, args );
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
		Object[] params = new Object[1];
		params[0] = val ;
		if ( m != null )
		{
			try {
				m.invoke(obj, params);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
	}

	public static void loadClass(String sClassName) {
		Class<?> c = null ;
		try {
			//c = Class.forName("java.lang.String");
			c = Class.forName(sClassName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Classe chargee : " + c.getName());
	}
	
	public static void testInstanceOf( Object obj) {

		 if ( obj instanceof String ) {
			 System.out.println("String");
			 String s = (String) obj ;
			 System.out.println("s = " +s );
		 }
		 else if ( obj instanceof Integer ) {
			 System.out.println("Integer");
			 Integer i = (Integer) obj ;
			 System.out.println("i = " + i );
		 }
		 else  {
			 System.out.println("???");
		 }
	}
	
	public static String modifiers ( int i ) {
		StringBuffer sb = new StringBuffer("") ;
		
		// Visibility 
		if ( Modifier.isPrivate(i) ) sb.append("private ");
		if ( Modifier.isProtected(i) ) sb.append("protected ");
		if ( Modifier.isPublic(i) ) sb.append("public ");
		
		if ( Modifier.isAbstract (i) ) sb.append("abstract ");
		if ( Modifier.isStatic(i) ) sb.append("static ");
		if ( Modifier.isFinal(i) ) sb.append("final ");
		
		if ( Modifier.isTransient(i) ) sb.append("transient ");
		if ( Modifier.isVolatile(i) ) sb.append("volatile ");
		if ( Modifier.isSynchronized(i) ) sb.append("synchronized ");
		if ( Modifier.isNative(i) ) sb.append("native ");
		
		return sb.toString();
	}
	
	public static void decrire ( Object obj) {
		if ( obj != null ) decrire ( obj.getClass() );
		Object name = get(obj, "Name");
		System.out.println("Name = " + name);
		
		set(obj, "Name", "Felix");
		System.out.println("Name = " + get(obj, "Name") );
		
		
		if ( obj instanceof Animal )
		{
			Animal animal = (Animal) obj ;
			//System.out.println("animalField = " + animal.animalField ) ;
			setField(obj, "animalField", "MyNewValue") ;
			//System.out.println("animalField = " + animal.animalField ) ;
		}
	}
	
	public static void decrire ( Class<?> c ) {

		System.out.println("==========");
		System.out.println("--- Class description :");
		System.out.println(" class name : " + c.getName() ); // package + class
		System.out.println(" class simple name : " + c.getSimpleName() ); // class uniquement
		System.out.println(" class canonical name : " + c.getCanonicalName() ); // class uniquement
		Package p = c.getPackage() ; 
		System.out.println( " package : " + p.getName());
		
		int cm = c.getModifiers();
		System.out.println(" class modifiers : " + Modifier.toString(cm) );

		System.out.println("--- Ancestors :");
		Class<?> sup = c.getSuperclass() ;
		while ( sup != null )
		{
			System.out.println( " super class : " + sup.getName() );
			sup = sup.getSuperclass() ;
		}
		
		Field[] fields = null ;
		System.out.println("--- Declared fields :");
		fields = c.getDeclaredFields();
		for ( Field f : fields )
		{
			int fm = f.getModifiers();
			System.out.println( " . " + modifiers(fm) + f.getType().getName() + " " + f.getName() );
		}
		
		System.out.println("--- Fields :");
		fields = c.getFields();
		//Field[] fields = c.getDeclaredFields(); // all fields (private, protected, .. )
		for ( Field f : fields ) 
		{
			int fm = f.getModifiers();
			//System.out.println(m);
			System.out.println(" . " + modifiers(fm) + f.getType().getName() + " " + f.getName() );
		}
		
		System.out.println("--- Declared Methods :");
		Method [] methods = c.getDeclaredMethods();
        for ( int i = 0 ; i < methods.length ; i++ )
        {
            Method m = methods[i] ;
            int modifiers = m.getModifiers();
			System.out.println(" . " + Modifier.toString(modifiers) + " " + m.getReturnType().getName() + " " + m.getName() );
			Class<?>[] types = m.getParameterTypes();
			for ( Class<?> type : types ) 
			{			
				System.out.println("   arg : " + type.getName() ) ;
			}
        }
        
		System.out.println("--- Methods :");
        methods = c.getMethods();
        for ( int i = 0 ; i < methods.length ; i++ )
        {
            Method m = methods[i] ;
            int modifiers = m.getModifiers();
			System.out.println(" . " + Modifier.toString(modifiers) + " " + m.getReturnType().getName() + " " + m.getName() );            
			Class<?>[] types = m.getParameterTypes();
			for ( Class<?> type : types ) 
			{			
				System.out.println("   arg : " + type.getName() ) ;
			}
        }
		System.out.println("==========");
		System.out.println("");
	}
	
	public static void printClass( Object obj) {
 
		System.out.println( "---" );
		
		Class<?> cl = obj.getClass() ;
		System.out.println( "class name = " + cl.getName() );
		
		Package p = cl.getPackage();		
		System.out.println( "package name = " + p.getName() );
		
		//Class<?> c = String.class ;
	}
	
	public static void describe( Class<?> c) {

		System.out.println( "---" );
		System.out.println(" . name = " + c.getName() );
		System.out.println(" . simple name = " + c.getSimpleName() );
		System.out.println(" . canonical name = " + c.getCanonicalName() );
		System.out.println(" . primitive ? : " + c.isPrimitive() );
		System.out.println(" . interface ? : " + c.isInterface() );
		System.out.println(" . array ? : " + c.isArray() );
	}
	
	public static Object create( String className ) {
		Object ret = null ;
		Class<?> c = null ;
		try {
			c = Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if ( c != null )
		{
			try {
				ret = c.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return ret ;
	}
	
	public static Object create( String className, String param ) {
		Class<?> c = null ;
		try {
			c = Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Constructor<?> constructor = null ;
		if ( c != null )
		{
			Class<?>[] parameterTypes = new Class<?>[1];
			parameterTypes[0] = param.getClass() ;
			try {
				constructor = c.getConstructor(parameterTypes);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		
		Object ret = null ;
		if ( constructor != null )
		{
			Object[] params = new Object[1];
			params[0] = param ;
			try {
				ret = constructor.newInstance(params);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
		return ret ;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "abc" ;
		Integer i = new Integer(123);
		Date d = new Date();
		
		testInstanceOf( s );
		testInstanceOf( i ); 
		testInstanceOf( d ); 
		printClass(s);
		printClass(i);
		
		loadClass("java.lang.String") ;
		
		describe(String.class) ;
		describe(Integer.class) ;
		describe(String[].class) ;
		describe(int.class) ;
		describe(int[].class) ;
		describe(Serializable.class) ;
		
		
		//decrire (  new Integer(123) ) ;
		decrire (  new Chat("Tom") ) ;
		
		System.out.println("isInstance() ? : " + String.class.isInstance("") ) ;
		System.out.println("isInstance() ? : " + String.class.isInstance(new Integer(12)) ) ;
		System.out.println("isInstance() ? : " + Animal.class.isInstance(new Integer(12)) ) ;
		System.out.println("isInstance() ? : " + Animal.class.isInstance(new Chat("Tom")) ) ;
		System.out.println("isInstance() ? : " + Animal.class.isInstance(new Chien("Pif")) ) ;

		System.out.println("isAssignableFrom() ? : " + Animal.class.isAssignableFrom( Chien.class ) ) ;
		System.out.println("isAssignableFrom() ? : " + Chien.class.isAssignableFrom( Animal.class ) ) ;
		
		System.out.println("isAssignableFrom() ? : " + Serializable.class.isAssignableFrom( Chien.class ) );
		
		
		Object obj = create ( "reflect.bean.Chat" ) ;
		System.out.println( " Object created : " + obj.getClass() );

		Object obj2 = create ( "reflect.bean.Chat", "Tom" ) ;
		if ( obj2 instanceof Chat )
		{
			Chat chat = (Chat) obj2 ;
			System.out.println(" obj2 : " + chat.getName() );
		}
	}

}
