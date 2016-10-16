package reflect.bean;

public class Manager extends Employee {

	private int departmentId ;
	
	public Manager(int id) {
		super(id);
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	
}
