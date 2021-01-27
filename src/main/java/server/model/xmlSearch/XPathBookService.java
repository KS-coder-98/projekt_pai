package server.model.xmlSearch;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XPathBookService {
    private DocumentBuilderFactory factory;
    DocumentBuilder builder;
    Document doc;

    public XPathBookService(String pathToFile) {
        factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        builder = null;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(pathToFile);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getBooksByYear(int year) {
        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();
        XPathExpression expr = null;
        System.err.println(year);
        try {
            expr = xpath.compile("//book[@year='" + year + "']/title/text()");
            return getNodeList(expr);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getBooksByAuthor(String author) {
        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();
        try {
            XPathExpression expr = xpath.compile("//book[author='" + author + "']/title/text()");
            return getNodeList(expr);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<String> getNodeList(XPathExpression expr) throws XPathExpressionException {
        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodeList = (NodeList) result;
        List<String> booksList = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            booksList.add(nodeList.item(i).getNodeValue());
            System.out.println(nodeList.item(i).getNodeValue());
        }
        return booksList;
    }
}
