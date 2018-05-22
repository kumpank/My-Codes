package utility;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileReader extends Readexcel {

	
		static Properties properties=new Properties();
		
		
         public Properties readProperties(){
			
			InputStream in = null;
			
			try {
				
				in = this.getClass().getResourceAsStream("/resources/common.properties");
				properties.load(in);
				System.out.println("Properties File Loaded successfully");
				}
			catch (Exception e) {
				System.out.println("%%%%%% Exc:"+e);
				e.printStackTrace();
			}
			return properties;
		}
		public static String getProperty(String key){
			PropertiesFileReader prop=new PropertiesFileReader();
			prop.readProperties();
			String value = null;
			if(null != key && !"".equalsIgnoreCase(key)){
				value = (String)properties.get(key);
			}
			return value;
		}
	
}
	
	
	

	
