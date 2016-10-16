package reflect.generics;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

public class Capsule<T> {
	
	  private T obj; // un objet de type T
	  
	  public Capsule() 
	  {
		  Class<?> c = getClass();
		  
		  // Returns the Type representing the direct superclass of the entity (class, interface, primitive type or void) represented by this Class
		  //Type type = c.getGenericSuperclass();
//		  Type type = getClass();
//		  System.out.println("Type : " + type );
		  
//		  ParameterizedType parameterizedType = (ParameterizedType) type ;
//		  System.out.println("ParameterizedType : " + parameterizedType );
		  
		  /**
		  // Type is the common superinterface for all types in the Java programming language. 
		  // These include raw types, parameterized types, array types, type variables and primitive types.
		  if ( type instanceof ParameterizedType ) {
			  System.out.println("ParameterizedType");
			  ParameterizedType parameterizedType = (ParameterizedType) type ;
			  parameterizedType.getActualTypeArguments();
		  }
		  
		  if ( type instanceof WildcardType ) {
			  // WildcardType represents a wildcard type expression, such as ?, ? extends Number, or ? super Integer.
			  System.out.println("WildcardType");
			  WildcardType wildcardType = (WildcardType) type ;
		  }
		  
		  if ( type instanceof GenericArrayType ) {
			  // GenericArrayType represents an array type whose component type is either a parameterized type or a type variable.
			  System.out.println("GenericArrayType");
			  GenericArrayType genericArrayType = (GenericArrayType) type ;
			  Type componentType = genericArrayType.getGenericComponentType();
		  }
		  
		  if ( type instanceof ParameterizedType ) {
		  }
		  **/
	  }
	  
	  // Constructeur avec en parametre un objet de type T
	  public Capsule(T o) {
	    obj = o;
	  }

	  // Return ob.
	  public T getob() {
	    return obj;
	  }

	  // Show type of T.
	  public void showType() {
	    System.out.println("Type of T is " +
	                       obj.getClass().getName());
	  }

	@Override
	public String toString() {
		return obj.toString();
	}
	  
}
