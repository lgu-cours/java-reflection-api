package reflect.generics;

public class TestCapsule {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		//-------------------------------------------------------------
	    // Create a Gen object for Strings.
	    Capsule<String> strOb = new Capsule<String>("Test");

	    // Show the type of data used by strOb.
	    strOb.showType();

	    // Get the value of strOb. Again, notice
	    // that no cast is needed.
	    String str = strOb.getob();
	    System.out.println("value: " + str);

		//-------------------------------------------------------------
	    Capsule<Integer> iOb; 

	    // Create a Gen<Integer> object and assign its
	    // reference to iOb.  Notice the use of autoboxing 
	    // to encapsulate the value 88 within an Integer object.
	    iOb = new Capsule<Integer>(88);

	    // Show the type of data used by iOb.
	    iOb.showType();

	    // Get the value of iOb. Notice that
	    // no cast is needed.
	    int v = iOb.getob();
	    System.out.println("value: " + v);

	    System.out.println();

	    //-------------------------------------------------------------

	}

}
