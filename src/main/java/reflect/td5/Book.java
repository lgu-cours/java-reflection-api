package reflect.td5;

import reflect.td5.MyAnnotations.MyClassAnnotation;
import reflect.td5.MyAnnotations.MyRuntimeAnnotation;
import reflect.td5.MyAnnotations.MySourceAnnotation;

@MySourceAnnotation
@MyClassAnnotation
@MyRuntimeAnnotation
public class Book {

	private String title ;
	
	public  double price ;

	
	public Book(String title, double price) {
		super();
		this.title = title;
		this.price = price;
	}

	
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	@Override
	@SuppressWarnings("unchecked")
	@MyRuntimeAnnotation
	public String toString() {
		return title + " price = " + price ;
	}
	
	
}
