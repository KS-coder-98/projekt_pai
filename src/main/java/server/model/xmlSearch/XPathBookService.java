package server.model.xmlSearch;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;

public class XPathBookService {
    private DocumentBuilderFactory factory;
    DocumentBuilder builder;
    Document doc;

    public XPathBookService(String pathToFile) {
        factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true); // never forget this!
        builder = null;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(pathToFile);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public NodeList getBooksByYear(int year) throws XPathExpressionException {
        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();
        XPathExpression expr = xpath.compile("//book[@year='" + year + "']/title/text()");
        return getNodeList(expr);
    }

    public NodeList getBooksByAuthor(String author) throws XPathExpressionException {
        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();
        XPathExpression expr = xpath.compile("//book[author='" + author + "']/title/text()");
        return getNodeList(expr);
    }

    private NodeList getNodeList(XPathExpression expr) throws XPathExpressionException {
        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        return (NodeList) result;
    }
}
