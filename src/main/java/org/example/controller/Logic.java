package org.example.controller;

import org.example.model.Curs;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class Logic {

    public static  boolean fileExist(Path path){
        return Files.exists(path);

    }
    public static void readFile(){

    }
    public static void writeFileJAXB(Curs curs){

        try {
            JAXBContext context = JAXBContext.newInstance(Curs.class);
            Marshaller marshaller = context.createMarshaller();
            File xmlFile = new File(curs.getYearCurs()+"curs.xml"); // Define the XML file path
            marshaller.marshal(curs, xmlFile);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void saveXMLJAXB(){


    }


    public static void saveXMLDOM(){
        try {
            // Crear una fábrica de documentos XML
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            // Crear un constructor de documentos
            DocumentBuilder db = dbf.newDocumentBuilder();
            // Crear un nuevo documento XML vacío
            Document doc = db.newDocument();

            Element eRaiz = doc.createElement("curs");
            doc.appendChild(eRaiz);
            Element eCurs = doc.createElement("yearCurs");
            eRaiz.appendChild(eCurs);

            Element eStudentsList = doc.createElement("studentsList");
            eRaiz.appendChild(eStudentsList);


        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }


    }
}
