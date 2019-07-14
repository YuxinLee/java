package session_xml.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;

/**
 * xml的工具类
 *
 */

public class XMLUtil {
    /**
     * 读取xml文档方法
     * @return
     */
    public static Document getDocument(){
        try {
            Document doc = new SAXReader().read(new File("./contact.xml"));
            return doc;
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 写出到xml文档中
     */
    public static void write2xml(Document doc){
        try {
            FileOutputStream out = new FileOutputStream("./contact.xml");
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("utf-8");
            XMLWriter writer = new XMLWriter(out,format);
            writer.write(doc);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
