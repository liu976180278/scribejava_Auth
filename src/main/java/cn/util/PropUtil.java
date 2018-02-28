/**
 * @Description: TODO
 * @date 2017年12月1日 下午2:31:13 	
 */
package cn.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author lzc
 *
 */
public class PropUtil {
	private static final String ProName="config.properties";
	public static String getProp(String key){
		InputStream in;
		Properties p = null;
		String value=null;
		try {
			in = PropUtil.class.getClassLoader().getResourceAsStream(ProName);
			
			p = new Properties();
			
			p.load(in);
			
			value=p.getProperty(key);
			
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return value;
	}
	
	public static void main(String[] args) {
		System.out.println(getProp("WeiBo.callbackUrl"));
	}
}
