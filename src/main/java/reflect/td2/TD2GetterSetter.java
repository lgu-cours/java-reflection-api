package reflect.td2;

import reflect.lib.td2.Factory;
import reflect.lib.td2.Personne;


public class TD2GetterSetter {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Factory f = new Factory();
		Personne p1 = f.getPersonne(1);
		Personne p2 = f.getPersonne(2);
		
		System.out.println("----------");
		System.out.println(" p1.getNom : " + TD2Utils.callGetter(p1, "getNom") ) ;
		System.out.println(" p2.getNom : " + TD2Utils.callGetter(p2, "getNom") ) ;
		System.out.println("----------");
		System.out.println(" p1.getNom : " + TD2Utils.callGetter(p1, "getPrenom") ) ;
		System.out.println(" p2.getNom : " + TD2Utils.callGetter(p2, "getPrenom") ) ;
		System.out.println("----------");
		System.out.println(" p1.getNom : " + TD2Utils.callGetter(p1, "getSalaire") ) ;
		System.out.println(" p2.getNom : " + TD2Utils.callGetter(p2, "getSalaire") ) ;
		System.out.println("----------");
		
		System.out.println("----------");
		System.out.println(" p1.setNom ..." );
		TD2Utils.callSetter(p1, "setNom", "Toto") ;
		System.out.println(" p2.setNom ..." );
		TD2Utils.callSetter(p2, "setNom", "Yoyo") ;
		
		System.out.println(" p1.getNom : " + TD2Utils.callGetter(p1, "getNom") ) ;
		System.out.println(" p2.getNom : " + TD2Utils.callGetter(p2, "getNom") ) ;
		
		System.out.println("----------");
	}

}
