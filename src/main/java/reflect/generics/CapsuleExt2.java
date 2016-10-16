package reflect.generics;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

public class CapsuleExt2 extends Capsule<String>
{
	  public CapsuleExt2() 
	  {
		  super();
		  
		  // Pas de "Class cast exception" car type defini a la compilation
		  Class<?> classType =  (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		  
		  Class<?> c = getClass();
		  
		  // Returns the Type representing the direct superclass of the entity (class, interface, primitive type or void) represented by this Class
		  //Type type = c.getGenericSuperclass();
		  Type type = getClass();
		  System.out.println("Type / getClass : " + type );

		  type = getClass().getGenericSuperclass();
		  System.out.println("Type / getGenericSuperclass : " + type );
		  
		  // Type is the common superinterface for all types in the Java programming language. 
		  // These include raw types, parameterized types, array types, type variables and primitive types.
		  if ( type instanceof ParameterizedType ) {
			  ParameterizedType parameterizedType = (ParameterizedType) type ;
			  System.out.println("ParameterizedType ( raw type = " + parameterizedType.getRawType() 
					  + ", owner type = " + parameterizedType.getOwnerType() + " )");
			  Type[] args = parameterizedType.getActualTypeArguments();
			  for ( Type t : args ) {
				  System.out.println("actual arg " + t );
				  showType(t);
			  }
			  Type type0 = parameterizedType.getActualTypeArguments()[0] ;
//			  Class<T> c0 = (Class<T>) type0 ;
//			  System.out.println("Actual class : " + c0 );
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
		  
	  }
	  
	  private void showType(Type type) 
	  {
		  if ( type instanceof ParameterizedType ) {
			  System.out.println(" ParameterizedType ");
		  }
		  
		  else if ( type instanceof WildcardType ) {
			  System.out.println(" WildcardType");
			  WildcardType wildcardType = (WildcardType) type ;
		  }
		  
		  else if ( type instanceof GenericArrayType ) {
			  System.out.println(" GenericArrayType");
			  GenericArrayType genericArrayType = (GenericArrayType) type ;
			  Type componentType = genericArrayType.getGenericComponentType();
		  }
		  else if ( type instanceof TypeVariable<?> ) {
			  System.out.println(" TypeVariable");
			  TypeVariable<?> typeVariable = (TypeVariable<?>)type;
			  System.out.println(" TypeVariable : " + typeVariable.getName() );
			  Type[] bounds = typeVariable.getBounds();
			  for ( Type t : bounds ) {
				  System.out.println(" TypeVariable : bound : " + t );
			  }
		  } 
		  else {
			  System.out.println(" Other ");
		  }
	  }
}
