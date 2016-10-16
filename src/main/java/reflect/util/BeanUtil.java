package reflect.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Laurent GUERIN
 *
 */
public class BeanUtil
{
    //----------------------------------------------------------------------------------------------
    // CONSTANTS
    //----------------------------------------------------------------------------------------------
    private final static String SHALLOW_COPY_ERR = "BeanUtil.shallowCopy error : " ;
    
    public final static int ALL_METHODS = 1 ;
    public final static int CLASS_DECLARED_METHODS = 2 ;
    
    //----------------------------------------------------------------------------------------------
    /**
     * Checks if the return type of the method is compatible with the given expected type
     * @param method
     * @param expectedType
     * @return
     */
    private final static boolean isReturnTypeCompatible ( Method method, Class expectedType ) // v 0.9.9
    {
//        System.out.println("isReturnTypeCompatible('" + method.getName()+"') ..." );
//        System.out.println("expectedType : " + expectedType );
        Class c = method.getReturnType(); 
//        System.out.println("getReturnType : " + c );
        if ( c != null )
        {
            if ( expectedType.isAssignableFrom( c ) ) 
            {
//                System.out.println("isReturnTypeCompatible('" + method.getName()+"') --> return TRUE" );
                return true ;
            }
        }
//        System.out.println("isReturnTypeCompatible('" + method.getName()+"') --> return FALSE" );
        return false ;
    }
    //----------------------------------------------------------------------------------------------
    /**
     * Returns the getter method "getXxxx" or "isXxxx" for the given attribute name,<br>
     * and the given expected return type.
     * @param methods
     * @param sAttribute
     * @param expectedType
     * @return
     */
    private final static Method getGetterMethod ( Method [] methods, String sAttribute, Class expectedType ) // v 0.9.9
    {
        for ( int i = 0 ; i < methods.length ; i++ )
        {
            Method m = methods[i] ;
            String s = m.getName();
//            System.out.println(" . " + s );
            if ( s.equals("get"+sAttribute) )
            {
                if ( isReturnTypeCompatible(m, expectedType) ) return m;
            }
            if ( s.equals("is"+sAttribute) )
            {
                if ( isReturnTypeCompatible(m, expectedType) ) return m;
            }
        }
        return null ;
    }
//    //----------------------------------------------------------------------------------------------
//    /**
//     * Returns the getter method "getXxxx" or "isXxxx" for the given attribute name
//     * @param methods : methods array where to search the getter method
//     * @param sAttribute : attribute name 
//     * @return
//     */
//    private final static Method getGetterMethod ( Method [] methods, String sAttribute )
//    {
//        for ( int i = 0 ; i < methods.length ; i++ )
//        {
//            Method m = methods[i] ;
//            String s = m.getName();
//            //System.out.println(" . " + s );
//            if ( s.equals("get"+sAttribute) )
//            {
//                return m ; // getMyAttribute
//            }
//            if ( s.equals("is"+sAttribute) )
//            {
//                return m ; // isMyBooleanAttribute
//            }
//        }
//        return null ;
//    }
    
    //----------------------------------------------------------------------------------------------
    /**
     * Shallow copy of a bean to another, attribute by attribute : <br>
     * call "setXxxx" with corresponding "getXxxx" or "isXxxx" <br>
     * NB: the 2 beans must be instances of the same class
     * 
     * @param orig : the original bean to copy
     * @param dest : the destination bean 
     */
    public final static void shallowCopy ( Object orig, Object dest )
    {
        shallowCopy  ( orig, dest, ALL_METHODS );
    }
    
    //----------------------------------------------------------------------------------------------
    /**
     * Shallow copy of a bean to another, attribute by attribute : <br>
     * call "setXxxx" with corresponding "getXxxx" or "isXxxx" <br>
     * NB: the 2 beans must be instances of the same class
     * 
     * @param orig : the original bean to copy
     * @param dest : the destination bean 
     * @param iFlag : indicates the introspection level : <br>
     *  ALL_METHODS : all the public methods including those inherited <br> 
     *  CLASS_DECLARED_METHODS : only the methods declared by the class (without inherited methods)
     */
    public final static void shallowCopy ( Object orig, Object dest, int iFlag )
    {
        if (orig == null) {
            throw new RuntimeException(SHALLOW_COPY_ERR+"origin bean is null");
        }

        if (dest == null) {
            throw new RuntimeException(SHALLOW_COPY_ERR+"destination bean is null");
        }
        
        Class clOrig = orig.getClass();
        Class clDest = dest.getClass();
        
        if ( clOrig.getName().equals( clDest.getName() ) != true )
        {
            throw new RuntimeException(SHALLOW_COPY_ERR+"different classes");
        }
        
        Method [] methods = null ;
        if ( iFlag == CLASS_DECLARED_METHODS )
        {
            // get the methods declared by the class or interface (without inherited methods)
            methods = clOrig.getDeclaredMethods(); 
        }
        else
        {
            // get all the public member methods of the class or interface, 
            // including those inherited from superclasses and superinterfaces
            methods = clOrig.getMethods(); 
        }
        
            Method methodInvoked = null ;
	        Object args [] = { "" }; 
	        for ( int i = 0 ; i < methods.length ; i++ )
	        {
	            // changes in v 0.9.9
//	            Method mSetter = methods[i] ;
//	            String s = mSetter.getName();
	            Method currentMethod = methods[i] ;
	            String sCurrentMethodName = currentMethod.getName();
	            if ( sCurrentMethodName.startsWith("set") )
	            {
	                String sAttributeName = sCurrentMethodName.substring(3);
		            Method mSetter = currentMethod ;
//System.out.println(" --> SETTER = " + mSetter.getName() );
		            Class[] params = mSetter.getParameterTypes();
		            if ( params.length == 1 ) // setXxxx with just 1 parameter
		            {
		                //Class paramType = params[0].getClass() ;
		                Class paramType = params[0] ;
		                Method mGetter = getGetterMethod ( methods, sAttributeName, paramType );
//System.out.println(" --> GETTER = " + mGetter );
		                if ( mGetter != null && mSetter != null )
		                {
		                    //--- Try to call getter and setter ( NB : a bad bean can throw any kind of Exception )
		                    try
		                    {
		                        //--- Invoke getXxxxxx or isXxxxxx
		                        methodInvoked = mGetter ;
		                        Object oValue = mGetter.invoke( orig , null); // getXxxxx
		                        
		                        //--- Invoke setXxxxxx 
		                        methodInvoked = mSetter ;
		                        args[0] = oValue ;
		                        mSetter.invoke( dest, args); // setXxxxx
	//	                    } catch (IllegalArgumentException e)
	//	                    {
	//	                        throw new TelosysRuntimeException(SHALLOW_COPY_ERR + getInvokeErrorMsg(method,e), e);
	//	                    } catch (IllegalAccessException e)
	//	                    {
	//	                        throw new TelosysRuntimeException(SHALLOW_COPY_ERR + getInvokeErrorMsg(method,e), e);
	//	                    } catch (InvocationTargetException e)
	//	                    {
	//	                        throw new TelosysRuntimeException(SHALLOW_COPY_ERR + getInvokeErrorMsg(method,e), e);
		                    } catch (Throwable e)
			                {
			                    throw new RuntimeException(SHALLOW_COPY_ERR + getInvokeErrorMsg(methodInvoked,e), e);
			                }
		                }
		            }
	            }
	        }
    }
    
    //----------------------------------------------------------------------------------------------
    private static Method findMethod(Class beanClass, String sMethodName)
    {
        Method meth = null;
        try
        {
            meth = beanClass.getMethod(sMethodName, null);
        } catch (SecurityException e)
        {
            throw new RuntimeException("findMethod : security violation ", e);
            //return null;
        } catch (NoSuchMethodException e)
        {
            return null; // Not found
        }
        return meth;
    }

    //----------------------------------------------------------------------------------------------
    private static Object callGetterMethod(Method method, Object oBean)
    {
        try
        {
            return method.invoke(oBean, null);
        } catch (IllegalArgumentException e)
        {
            throw new RuntimeException("callGetterMethod : IllegalArgumentException ", e);
            //return null;
        } catch (IllegalAccessException e)
        {
            throw new RuntimeException("callGetterMethod : IllegalAccessException ", e);
            //return null;
        } catch (InvocationTargetException e)
        {
            throw new RuntimeException("callGetterMethod : InvocationTargetException ", e);
            //return null;
        }
    }
    //----------------------------------------------------------------------------------------------
    private static String getInvokeErrorMsg(Method method, Throwable t)
    {
        String s1 = "?" ;
        String s2 = "?" ;
           
        if ( method != null )
        {
            String s3 = "" ;
            Class c = method.getDeclaringClass() ;
            if ( c != null )
            {
                s3 = c.getName() + "." ;
            }
            s1 = s3 + method.getName() ;
        }
        if ( t != null )
        {
            s2 = t.getClass().getName() ;
        }
        return "method " + s1 + " invocation : " + s2 ; 
    }
    //----------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------
    /**
     * Returns the value of the given bean field using reflection
     * @param oBean : the bean instance 
     * @param sFieldName : the field name 
     * @return : the value found 
     * @throws TelosysException if the field name is unknown or if arguments are invalid
     */
    public static Object getFieldValue(Object oBean, String sFieldName) 
    {
        if (oBean == null)
        {
            throw new RuntimeException("getFieldValue : bean instance is null ");
        }
        if (sFieldName == null)
        {
            throw new RuntimeException("getFieldValue : field name is null ");
        }

        //--- Build the "method field name" (1rst char Upper Case)
        String sFieldName2 = sFieldName.trim();
        char cFirstChar = sFieldName2.charAt(0);
        if (Character.isLowerCase(cFirstChar))
        {
            String s1 = sFieldName2.substring(0, 1);
            String s2 = sFieldName2.substring(1);
            sFieldName2 = s1.toUpperCase() + s2;
        }

        Class beanClass = oBean.getClass();
        Method getter = null;
        //--- Try to find a "getXxxx" getter method
        getter = findMethod(beanClass, "get" + sFieldName2);
        if (getter != null)
        {
            return callGetterMethod(getter, oBean);
        }
        else
        {
            //--- Try to find a "isXxxx" getter method
            getter = findMethod(beanClass, "is" + sFieldName2);
            if (getter != null)
            {
//                System.out.println("get" + sFieldName2 + " : found ");
                return callGetterMethod(getter, oBean);
            }
            else
            {
                //--- Unknown field
                throw new RuntimeException("getFieldValue : unknown field ");
            }
        }
    }

    private static String trimNotVoidOrNull(String s) 
    {
        if ( s != null )
        {
            String s2 = s.trim();
            if ( s2.length() > 0)
            {
                return s2;
            }
        }        
        return null ;
    }
    
    //----------------------------------------------------------------------------------------------
    /**
     * Returns the "bean name" from the variable name ( in standard "dot notation" ) 
     * @param sName : the variable name to parse ( 'bean.attribute' )
     * @return : the "bean name" part : 'aaa' for 'aaa.bbb', 'aaa' for 'aaa' or null for '.aaa'
     */
    public static String getBeanName(String sName) 
    {
        if ( sName == null )
        {
            return null ;
        }
        if ( sName.trim().length() == 0 )
        {
            return null ;
        }
        int iDotIndex = sName.indexOf('.');
        if (iDotIndex > 0)
        {
            //--- There's at least a "." in the name ( and not at the first position )
            String[] tokens = sName.split("\\.");
            if (tokens.length == 2) // Exactly 2 parts : "bean.attribute"
            {
                //--- Return the first part : "bean.attribute" => return "bean"
                return trimNotVoidOrNull( tokens[0] );
            }
            else // More than 2 parts : "aa.bb.cc" or more
            {
                return null ;
            }
        }
        else
        {
            if (iDotIndex < 0) // No '.' in the name => return all the name
            {
                return trimNotVoidOrNull(sName);
            }
        }
        // Other cases : "aaa.bbb.ccc.xxx", "aa", ".aa" => return null 
        return null ;
    }
    
    //----------------------------------------------------------------------------------------------
    /**
     * @param sName : the variable name to parse ( 'bean.attribute', 'bean', '.attribute' )
     * @return : the "attribute name" part ( 'attr' for 'bean.attr' or '.attr' )<br>
     *  or null for 'bean', 'aa.bb.cc', ''
     */
    public static String getAttributeName(String sName) 
    {
        if ( sName == null )
        {
            return null ;
        }
        if ( sName.trim().length() == 0 )
        {
            return null ;
        }
        String sFieldName = null;
        int iDotIndex = sName.indexOf('.');
        if (iDotIndex > 0)
        {
            //--- There's at least a "." in the name ( not at the begining : "bean.attribute" )
            String[] tokens = sName.split("\\.");
            if (tokens.length == 2) // Exactly 2 parts : "bean.attribute"
            {
                //--- Return the 2nd part : "bean.attribute" => return "attribute"
                sFieldName = tokens[1];
            }
            else // More than 2 parts : "aa.bb.cc" or more
            {
                return null ;
            }
        }
        else
        {
            // No '.' or '.' is at the first position => no bean name
            if (iDotIndex == 0) // One '.' at the beginning : ".attribute"
            {
                //--- Remove the '.' at the first position
                sFieldName = sName.substring(1);
            }
            else // No '.'
            {
                //--- Take it "as is"
                //sFieldName = sName;
                return null ;
            }
        }    
        return trimNotVoidOrNull(sFieldName);
    }
}
