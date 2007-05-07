package vgrabber.tools;

import java.io.*;
import javax.xml.*;
import javax.xml.validation.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.*;
import org.w3c.dom.*;
import org.xml.sax.*;




public class XPathQueryer {
    public static void main(String... s){
    try {            
    // parse an XML document into a DOM tree
    DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    Document document = parser.parse(new File("instance.xml"));

    // create a SchemaFactory capable of understanding WXS schemas
    SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

    // load a WXS schema, represented by a Schema instance
    Source schemaFile = new StreamSource(new File("mySchema.xsd"));
    Schema schema = factory.newSchema(schemaFile);

    // create a Validator instance, which can be used to validate an instance document
    Validator validator = schema.newValidator();

    // validate the DOM tree
    
    validator.validate(new DOMSource(document));
    } catch (IOException e1) {
        
    } catch (SAXException e2) {
        // instance document is invalid!
    } catch (ParserConfigurationException e3) {
        
    }
    
        
    }
}