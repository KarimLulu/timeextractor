package com.codeminders.labs.timeextractor.service;

import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.codeminders.labs.timeextractor.entities.HtmlElement;

public class GetHtmlText {

    public ArrayList<HtmlElement> getElements(String html) {

        ArrayList<HtmlElement> elements = new ArrayList<HtmlElement>();
        Document document = Jsoup.parse(html);
        document.outputSettings(new Document.OutputSettings().prettyPrint(false));
        Elements htmlElements = document.body().select("*");
        for (Element element : htmlElements) {
            if (element.childNodes().size() > 3 && element.ownText().isEmpty()) {
                continue;
            }
            if (element.ownText().isEmpty() || element.ownText().length() < 2 || element.toString().length() > 10000) {
                continue;
            }
            String text = element.text();

            try {
                String elementString = element.toString();
                if (!html.contains(elementString)) {
                    System.out.println("Element :" + element);
                    System.out.println("Text :" + text);

                }
                if (html.contains(elementString)) {
                    HtmlElement htmlEl = new HtmlElement();
                    htmlEl.setTag(element.tagName());
                    int beginning = html.indexOf(element.toString());
                    int end = beginning + element.toString().length();
                    htmlEl.setTextFrom(beginning);
                    htmlEl.setTextTo(end);
                    htmlEl.setExtractedText(text);
                    htmlEl.setFullTag(element.toString());
                    htmlEl.setTag(element.tagName());
                    elements.add(htmlEl);

                }

            } catch (PatternSyntaxException ex) {
                System.out.println("Exception: " + ex);
            }
        }

        return elements;
    }

}