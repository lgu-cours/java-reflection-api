package reflect.td6;

import java.io.File;

import reflect.bean.Chat;
import reflect.bean.Chien;

public class BeanConverterTest {
	
	private final static String FELIX_PROPERTIES = "D:/TMP/felix.properties";
	private final static String MILOU_PROPERTIES = "D:/TMP/milou.properties";
	
	public static void main(String[] args) {
		
		saveBeans();
		
		loadBeans();
	}
	
	public static void saveBeans() {
		BeanConverter bc = new BeanConverter();

		Chat chat = new Chat("Felix");
		bc.save(chat, new File(FELIX_PROPERTIES));
		
		Chien chien = new Chien("Milou");
		bc.save(chien, new File(MILOU_PROPERTIES));
	}

	public static void loadBeans() {
		BeanConverter bc = new BeanConverter();

		Chat chat = bc.load(new File(FELIX_PROPERTIES), Chat.class);
		System.out.println("Chat : " + chat );

		Chien chien = bc.load(new File(MILOU_PROPERTIES), Chien.class);
		System.out.println("Chien : " + chien );
	}
}
