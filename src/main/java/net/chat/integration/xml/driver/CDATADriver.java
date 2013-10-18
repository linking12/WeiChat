/**
 * 
 */
package net.chat.integration.xml.driver;

import java.io.Writer;

import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * @author bo.chen
 *
 */
public class CDATADriver extends XppDriver {
	public HierarchicalStreamWriter createWriter(Writer out) {  
        return new PrettyPrintWriter(out) {  
            // 对所有xml节点的转换都增加CDATA标记  
            boolean cdata = true;  
       
            public void startNode(String name, Class clazz) {  
                super.startNode(name, clazz);  
            }  
       
            protected void writeText(QuickWriter writer, String text) {  
                if (cdata) {  
                    writer.write("<![CDATA[");  
                    writer.write(text);  
                    writer.write("]]>");  
                } else {  
                    writer.write(text);  
                }  
            }  
        };  
    }  
}
