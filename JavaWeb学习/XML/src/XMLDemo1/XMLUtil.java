package XMLDemo1;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;

/**
 *
 * xml操作的工具类
 */

public class XMLUtil {

    /**
     * 用于读取xml文件
     * @return Document
     */
    public static Document getDocument(){
        try {
            Document document = new SAXReader().read(new File("./contact.xml"));
            return document;
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 用于写出xml文件
     * @param  Document
     */

    public static void writeXml(Document document){
        try{
            FileOutputStream outputStream = new FileOutputStream("./contact.xml");
            OutputFormat outputFormat = OutputFormat.createPrettyPrint();
            outputFormat.setEncoding("utf-8");
            XMLWriter writer = new XMLWriter(outputStream,outputFormat);
            writer.write(document);
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
