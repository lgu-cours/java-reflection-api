package reflect.bean;

public abstract class Animal {
	
	public String animalField = null ;
	
	String name = null ;

	
	public Animal(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
