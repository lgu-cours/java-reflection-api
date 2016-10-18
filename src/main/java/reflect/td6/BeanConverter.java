package reflect.td6;

import java.io.File;
import java.util.Properties;

/**
 * The 'BeanConverter' TODO 
 *
 */
public class BeanConverter {
	
	/**
	 * Saves the given object in the given properties file
	 * @param obj
	 * @param file
	 */
	public void save( Object obj, File file ) {
		
		Properties properties = beanToProperties(obj);
		// Save in file
		PropertiesManager pm = new PropertiesManager(file);
		pm.save(properties);
	}

	private Properties beanToProperties(Object obj) {
		Properties properties = new Properties();
		// TODO
		// obj getters -> properties
		
		// Just for test 
		properties.setProperty("aa", "aaValue");
		properties.setProperty("bb", "bbValue");
		return properties ;
	}
	
	/**
	 * Loads an object from from the given properties file
	 * @param file
	 * @param clazz
	 * @return
	 */
	public <T> T load( File file, Class<T> clazz ) {
		// Load properties from file
		PropertiesManager pm = new PropertiesManager(file);
		Properties properties = pm.load();
		properties.list(System.out);
		
		return propertiesToBean(properties, clazz);
	}
	
	private <T> T propertiesToBean(Properties properties, Class<T> clazz) {
		// TODO
		// new instance of clazz
		// properties -> setters
		// return instance
		return null ;
	}

}
