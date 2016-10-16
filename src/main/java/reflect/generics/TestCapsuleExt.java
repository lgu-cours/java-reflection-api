package reflect.generics;

public class TestCapsuleExt {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		//-------------------------------------------------------------
	    // Create a Gen object for Strings.
	    Capsule<?> capsule = null ;
	    
	    capsule = new CapsuleExt<String>();

	    capsule =  new CapsuleExt<Integer>();

	    capsule =  new CapsuleExt<int[]>();
	    //-------------------------------------------------------------

	}

}
