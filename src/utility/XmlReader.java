package utility;

/**
 * Created by QQQ on 15/8/29.
 */
import java.io.*;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlReader {
    public static void main(String file_name, HashMap<String, Object> content_all) {
        File xmlFile = new File(file_name);
        DocumentBuilder builder = null;
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();


        try {
            builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            Element root = document.getDocumentElement();
            NodeList childNode = root.getChildNodes();

            //System.out.println("root = " + root.getNodeName());
            //System.out.println("root childnode length = " + childNode.getLength());
            HashMap<String, Object> hash_for_diease_all = new HashMap<>();
            HashMap<String, String> hash_for_title = new HashMap<>();
            int content_id = 0;

            for(int i=0; i<childNode.getLength(); i++ ) {
                //System.out.println("root childnode "+i);
                Node node = childNode.item(i);
                if ( node.getNodeName().equals("#text")) {continue;}
                else if ( node.getNodeName().equals("title") ) {
                    NodeList nodeDetail = node.getChildNodes();
                    for (int j=0; j<nodeDetail.getLength(); j++) {
                        Node detail = nodeDetail.item(j);
                        if ( detail.getNodeName().equals("#text")) {continue;}
                        hash_for_title.put(detail.getNodeName(), detail.getTextContent());
                    }
                    content_all.put("title", hash_for_title);
                } else if (node.getNodeName().equals("content")){
                    HashMap<String, String> hash_for_diease = new HashMap<>();
                    NodeList nodeDetail = node.getChildNodes();
                    String id="none";
                    for (int j=0; j<nodeDetail.getLength(); j++) {
                        Node detail = nodeDetail.item(j);
                        if ( detail.getNodeName().equals("#text")) {continue;}
                        hash_for_diease.put(detail.getNodeName(), detail.getTextContent());
                    }
                    hash_for_diease_all.put(String.valueOf(content_id), hash_for_diease);
                    content_id ++;
                }
            }
            content_all.put("content", hash_for_diease_all);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
