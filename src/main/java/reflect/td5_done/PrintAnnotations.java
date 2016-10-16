package reflect.td5_done;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import reflect.td5.Book;
import reflect.td5.MyAnnotations.MyClassAnnotation;
import reflect.td5.MyAnnotations.MyRuntimeAnnotation;
import reflect.td5.MyAnnotations.MySourceAnnotation;


public class PrintAnnotations {
	
	private static void getAnnotation ( Class<?> clObj, Class<? extends Annotation> clAnnot ) {
		System.out.println("--- getAnnotation(" + clAnnot.getName() + ") :" );
		Annotation a = clObj.getAnnotation( clAnnot );
		if ( a != null )
		{
			System.out.println(" found : " + a.toString() );
		}
		else
		{
			System.out.println(" not found !" );
		}
	}
	
	private static void getAnnotations ( Method m ) {
		System.out.println("--- getAnnotations(" + m.getName() + ") :" );
		System.out.println("--- m.getAnnotations() :" );
		Annotation[] annotations1 = m.getAnnotations();
		for ( Annotation a : annotations1 )
		{
			System.out.println(" . " + a );
		}

		System.out.println("--- m.getDeclaredAnnotations() :" );
		Annotation[] annotations2 = m.getDeclaredAnnotations();
		for ( Annotation a : annotations2 )
		{
			System.out.println(" . " + a );
		}
	}
	
	private static void getAnnotation ( Method m, Class<? extends Annotation> clAnnot ) {
		System.out.println("--- getAnnotation(" + clAnnot.getName() + ") :" );
		Annotation a = m.getAnnotation( clAnnot );
		if ( a != null )
		{
			System.out.println(" found : " + a.toString() );
		}
		else
		{
			System.out.println(" not found !" );
		}
	}
	
	public static void main(String[] args) {
		
		Class<Book> c = Book.class ;
		
		System.out.println("--- getAnnotations() :" );
		Annotation[] annotations1 = c.getAnnotations();
		for ( Annotation a : annotations1 )
		{
			System.out.println(" . " + a );
		}

		System.out.println("--- getDeclaredAnnotations() :" );
		Annotation[] annotations2 = c.getDeclaredAnnotations();
		for ( Annotation a : annotations2 )
		{
			System.out.println(" . " + a );
		}
		
//		System.out.println("--- getAnnotation(Deprecated.class) :" );
//		Annotation a = c.getAnnotation( Deprecated.class );
//		if ( a != null )
//		{
//			System.out.println("Deprecated found : " + a.toString() );
//		}
//		System.out.println("--- getAnnotation(MyRuntimeAnnotation.class) :" );
//		a = c.getAnnotation( MyRuntimeAnnotation.class );
//		if ( a != null )
//		{
//			System.out.println("MyRuntimeAnnotation found : " + a.toString() );
//		}
//		System.out.println("--- getAnnotation(MySourceAnnotation.class) :" );
//		a = c.getAnnotation( MySourceAnnotation.class );
//		if ( a != null )
//		{
//			System.out.println("MySourceAnnotation found : " + a.toString() );
//		}
		
		System.out.println("" );
		System.out.println(" === get Annotations by type :" );
		getAnnotation(c, Deprecated.class );
		getAnnotation(c, MyRuntimeAnnotation.class );
		getAnnotation(c, MySourceAnnotation.class );
		getAnnotation(c, MyClassAnnotation.class );
		
		System.out.println("" );
		System.out.println(" === get Method annotations " );
		try {
			Method m = c.getMethod("toString", (Class[]) null);
			getAnnotations(m);
			getAnnotation(m, Deprecated.class );
			getAnnotation(m, Override.class );
			getAnnotation(m, SuppressWarnings.class );
			getAnnotation(m, MyRuntimeAnnotation.class );
			getAnnotation(m, MySourceAnnotation.class );
			getAnnotation(m, MyClassAnnotation.class );
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
