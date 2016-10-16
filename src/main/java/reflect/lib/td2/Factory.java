package reflect.lib.td2;

public class Factory {

	
	public Personne getPersonne( int i )
	{
		if ( i % 2 == 0 )
		{
			Employe e = new Employe("Zola", "Emile");
			e.setSalaire(1234.50);
			return e ;
		}
		else
		{
			Client c = new Client(123);
			c.setNom("Flaubert");
			c.setPrenom("Gustave");
			return c ;
		}
	}
	
}
