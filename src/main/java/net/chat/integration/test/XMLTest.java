/**
 * 
 */
package net.chat.integration.test;

import net.chat.integration.xml.bean.MessageBean;
import net.chat.integration.xml.driver.CDATADriver;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

/**
 * @author bo.chen
 *
 */
public class XMLTest {

	public static void main(String [] s){
		
		//没用cdata
		//XStream xs=new XStream();
		
		//添加cdata
		XStream xs=new XStream(new CDATADriver());
		
		MessageBean bean=new MessageBean();
		bean.setFromUserName("user111");
		bean.setToUserName("user222");
		xs.autodetectAnnotations(true);
		System.out.println(xs.toXML(bean));
		
		//for json
		xs=new XStream(new JsonHierarchicalStreamDriver());
		xs.autodetectAnnotations(true);
		System.out.println(xs.toXML(bean));
	}
}
