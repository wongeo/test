package com.feng.fastxml;

import android.util.Xml;

import com.feng.fastxml.annotation.XMLField;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by feng on 2017/6/29.
 */

public class XML {

    public static final <T> T parseObject2(String text, Class<T> clazz) throws Exception {
        InputStream is = new ByteArrayInputStream(text.getBytes());
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(is);
        Element root = doc.getDocumentElement();
        NodeList nodes = root.getChildNodes();
        Field[] fields = clazz.getDeclaredFields();

        T t = clazz.newInstance();
        for (Field field : fields) {
            XMLField xmlField = field.getAnnotation(XMLField.class);
            if (xmlField == null) {
                continue;
            }
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodes.item(i);
                    if (xmlField.name().equalsIgnoreCase(element.getNodeName())) {
                        field.set(t, element.getFirstChild().getNodeValue());
                    }
                }
            }
        }
        return t;
    }


    public static final <T> T parseObject(String text, Class<T> clazz) throws Exception {
        InputStream is = new ByteArrayInputStream(text.getBytes());
        T t = clazz.newInstance();
        XmlPullParser pullParser = Xml.newPullParser();
        pullParser.setInput(is, "UTF-8");
        int event = pullParser.getEventType();

        Field[] fields = clazz.getDeclaredFields();
        while (event != XmlPullParser.END_DOCUMENT) {
            switch (event) {
                case XmlPullParser.START_DOCUMENT:
                    println();
                    break;
                case XmlPullParser.START_TAG:
                    if ("name".equals(pullParser.getName())) {
                    }
                    println();
                    break;
                case XmlPullParser.END_TAG:
                    println();
                    break;
            }
            event = pullParser.next();
        }
        return t;
    }

    private static void println() {
        System.out.println();
    }
}
