package reflect.td5;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public @interface MyAnnotations {

	@Retention(RetentionPolicy.SOURCE)
	@interface MySourceAnnotation {}

	@Retention(RetentionPolicy.CLASS)
	@interface MyClassAnnotation {}

	@Retention(RetentionPolicy.RUNTIME)
	@interface MyRuntimeAnnotation {}

}
