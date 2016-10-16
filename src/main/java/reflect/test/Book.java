package reflect.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.SOURCE)
@interface MySourceAnnotation {}

@Retention(RetentionPolicy.CLASS)
@interface MyClassAnnotation {}

@Retention(RetentionPolicy.RUNTIME)
@interface MyRuntimeAnnotation {}

@MySourceAnnotation
@MyClassAnnotation
@MyRuntimeAnnotation
@Deprecated
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
	public String toString() {
		return title + " price = " + price ;
	}
	
	
}
