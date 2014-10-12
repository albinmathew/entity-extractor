package com.qburst.alchemy.entityextractor;

import com.alchemyapi.api.AlchemyAPI;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

/**
 * Created by albinmathew on 9/9/14.
 */
public class EntityExtractor {

    private static void getEntitiesFomText(String string) throws SAXException, ParserConfigurationException, XPathExpressionException, IOException {
        AlchemyAPI alchemyObj = AlchemyAPI.GetInstanceFromString(Constants.API_KEY);

        Document doc = alchemyObj.TextGetRankedNamedEntities(string);

        try {
            Element element = doc.getDocumentElement();
            NodeList nList = element.getElementsByTagName(Constants.ENTITY);

            for (int i = 0; i < nList.getLength(); i++) {

                Node nNode = nList.item(i);
                Element eElement = (Element) nNode;

                String type = eElement.getElementsByTagName(Constants.TYPE).item(0).getTextContent();
                String text = eElement.getElementsByTagName(Constants.TEXT).item(0).getTextContent();

                System.out.println(type + " : " + text);

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(Constants.ERROR);
        }

    }
}
