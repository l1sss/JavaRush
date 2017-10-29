package com.javarush.task.task33.task3309;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;

/*
Комментарий внутри xml
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) {
        StringWriter writer = new StringWriter();
        objToWriter(obj, writer);

        Document document = parseXmlToDoc(writer);
        addComment(document, tagName, comment);
        searchTextNode(document, document);

        return transformToString(document);
    }

    private static String transformToString(Document document) {
        StringWriter writer = new StringWriter();

        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "no");

            StreamResult streamResult = new StreamResult(writer);

            DOMSource domSource = new DOMSource(document);
            transformer.transform(domSource, streamResult);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return writer.toString();
    }

    private static void searchTextNode(Document document, Node start) {
        for (Node child = start.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getNodeType() == Node.TEXT_NODE) checkOnCdata(document, child);
            searchTextNode(document, child);
        }
    }

    private static void checkOnCdata(Document document, Node node) {
        String text = node.getTextContent();

        if (text.matches(".*[<>&'\"].*")) {
            Node parent = node.getParentNode();
            parent.replaceChild(document.createCDATASection(text), node);
        }
    }

    private static void addComment(Document document, String tagName, String comment) {
        NodeList nodeList = document.getElementsByTagName(tagName);

        for (int i = 0; i < nodeList.getLength(); i++) {
            Comment commentXml = document.createComment(comment);

            Node current = nodeList.item(i);
            Node parent = current.getParentNode();

            parent.insertBefore(commentXml, current);
        }
    }

    private static Document parseXmlToDoc(StringWriter writer) {
        Document document = null;

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            document = db.parse(new ByteArrayInputStream(writer.toString().getBytes("UTF-8")));
            document.normalize();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return document;
    }

    private static void objToWriter(Object obj, StringWriter writer) {
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(obj, writer);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String result = Solution.toXmlWithComment(new AnExample(), "needCDATA", "it's a comment - <needCDATA>");
        System.out.println(result);
    }

    @XmlType(name = "anExample")
    @XmlRootElement
    public static class AnExample {
        public String[] needCDATA = new String[]{"need CDATA because of < and >", ""};
    }
}