package reflect.bean;

public class Chat extends Felin 
{
	public final static int ZERO = 0 ;
	
	private static int count = 0;
	
	protected int age = 0 ;
	
	public    int publicValue = 123 ;
	
	public Chat() {
		super("???");
		count++;
	}
	
	public Chat(String name) {
		super(name);
		count++;
	}

	public static int getCount()
	{
		return count;
	}

	public void setAge(int v)
	{
		age = v ;
	}
	
	public int getAge()
	{
		return age;
	}

	public void miauler()
	{
		System.out.println("miaou");
	}

	private String getNameAndAge()
	{
		return "name=" + getName() + " age=" + getAge() ;
	}

	public String toString() {
		return "Chat : " + getNameAndAge() ;
	}
	
	
}
