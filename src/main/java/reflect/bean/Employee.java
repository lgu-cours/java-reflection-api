package reflect.bean;

public class Employee extends Person {

	private String country ;
	
	private double wage ;
	
	public Employee(int id) {
		super(id);
		setSeniority(0);
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getWage() {
		return wage;
	}

	public void setWage(double wage) {
		this.wage = wage;
	}

	protected int seniority()
	{
		return 0 ;
	}

	private void setSeniority(int n)
	{
	}
}
