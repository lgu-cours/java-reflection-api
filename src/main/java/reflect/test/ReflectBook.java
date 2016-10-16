package reflect.test;

import java.lang.annotation.Annotation;

public class ReflectBook {

	/**
	 * @param args
	 */
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
		
		System.out.println("--- getAnnotation(Deprecated.class) :" );
		Annotation a = c.getAnnotation( Deprecated.class );
		if ( a != null )
		{
			System.out.println("Deprecated found : " + a.toString() );
		}
		System.out.println("--- getAnnotation(MyRuntimeAnnotation.class) :" );
		a = c.getAnnotation( MyRuntimeAnnotation.class );
		if ( a != null )
		{
			System.out.println("MyRuntimeAnnotation found : " + a.toString() );
		}
		System.out.println("--- getAnnotation(MySourceAnnotation.class) :" );
		a = c.getAnnotation( MySourceAnnotation.class );
		if ( a != null )
		{
			System.out.println("MySourceAnnotation found : " + a.toString() );
		}
	}

}
