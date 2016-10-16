package reflect.td4;

import java.util.Hashtable;

public class Container {

	private Hashtable<String,Object> beans = new Hashtable<String,Object>();
		
	public void addBean(String name, Object bean)
	{
		beans.put(name, bean);
	}
	
	public Object getValue(String name )
	{
		// TODO 
		
		// 1) isoler le nom du bean et le nom de l'attribut 
		// ex "chien.name" --> "chien" & "name" 
		String beanName = "" ;
		
		// 2) rechercher le bean dans le container 
		Object bean = beans.get(beanName);
		if ( bean == null )
		{
			throw new RuntimeException("Bean '" + beanName + "' not found in the container");
		}

		// 3) si bean OK : rechercher le "getter" correspondant au nom de l'attribut
		// ex "name" --> getName()
		
		// 4) call "getter" + return value
		return null ;
	}
	
	
}
