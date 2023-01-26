package com.server.server.utils.XML;

import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class XMLReader {

    private ArrayList<String> xmlNodeContent = new ArrayList<String>();

    /**
     * Get the message code
     * 
     * @param doc
     * @return "code"
     */
    public String getMessageCode(Document doc) {
        return doc.getDocumentElement().getAttribute("code");
    }

    /**
     * Access to XML Data with XPath expression
     * 
     * @param doc
     * @param expression
     * @throws XPathExpressionException
     */
    public String getNodeXPath(Document doc, String expression) throws XPathExpressionException {

        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

        return nodeList.item(0).getTextContent();
    }

    /**
     * Parse the xml file Save content in the xmlNodeContent Array
     * 
     * @param startingNode
     */
    public void parseXML(Node startingNode) {
        NodeList childNodes = startingNode.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);

            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                parseXML(childNode);
            } else {

                // <trim> is used to ignore new lines and spaces elements.
                if (!childNode.getTextContent().trim().isEmpty()) {
                    xmlNodeContent.add(childNode.getTextContent());
                }
            }
        }
    }

    /**
     * Start parsing the commument from a specific node
     * 
     * @param doc
     * @param nodeName
     */
    public void parseXMLFromNode(Document doc, String nodeName) {
        Node startingNode = doc.getElementsByTagName(nodeName).item(0);
        parseXML(startingNode);
    }

    /**
     * Get the xmlNodeContent Array
     * 
     * @return"xmlNodeNodeContent"
     */

    public ArrayList<String> getXmlNodeContent() {
        return this.xmlNodeContent;
    }

    /**
     * Set the xmlNodeContent Array
     * 
     * @param xmlNodeContent
     */
    public void setXmlNodeContent(ArrayList<String> xmlNodeContent) {
        this.xmlNodeContent = xmlNodeContent;
    }

    public String convertXMLtoString(Document xmlDocument) throws TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        transformer = tf.newTransformer();

        // A character stream that collects its output in a string buffer,
        // which can then be used to construct a string.
        StringWriter writer = new StringWriter();

        // transform document to string
        transformer.transform(new DOMSource(xmlDocument), new StreamResult(writer));

        String xmlString = writer.getBuffer().toString();

        return xmlString;
    }

}