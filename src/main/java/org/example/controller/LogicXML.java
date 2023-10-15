package org.example.controller;

import org.example.model.Curs;
import org.example.model.Student;
import org.example.model.Subject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LogicXML {


    public static void writeFileJAXB(Curs curs) {

        try {
            JAXBContext context = JAXBContext.newInstance(Curs.class);
            Marshaller marshaller = context.createMarshaller();
            File xmlFile = new File(curs.getYearCurs() + "curs.xml"); // Define the XML file path
            marshaller.marshal(curs, xmlFile);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void readXMLJAXB(Curs curs1, Curs curs2) {
        Path path = Paths.get(curs1.getYearCurs() + "curs.xml");
        Path path2 = Paths.get(curs2.getYearCurs() + "curs.xml");

        if (Files.exists(path) && Files.exists(path2)) {

            try {
                JAXBContext context = JAXBContext.newInstance(Curs.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                Curs curs11 = (Curs) unmarshaller.unmarshal(path.toFile());
                Curs curs22 = (Curs) unmarshaller.unmarshal(path2.toFile());


                LogicCurs.putNotes(curs11);
                LogicCurs.putNotes(curs22);

  //              System.out.println(curs22);


                LogicCurs.checkSubjectScore(curs22);
                LogicCurs.changeStudentYear(curs11,curs22);

//
//                System.out.println(curs22.getStudentsList().size());
//                System.out.println(curs11.getStudentsList().size());

                LogicCurs.fillCFirstCurs(curs11);

              //  System.out.println(curs11.getStudentsList().size());


                writeFileJAXB(curs11);
                writeFileJAXB(curs22);


            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }

        } else {
            System.out.println("file not found");
        }
    }




    public static void saveXMLDOM(Curs curs) {
        List<Student> lis = new ArrayList<>();
        lis = curs.getStudentsList();


        try {
            // Crear una fábrica de documentos XML
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            // Crear un constructor de documentosW
            DocumentBuilder db = dbf.newDocumentBuilder();
            // Crear un nuevo documento XML vacío
            Document doc = db.newDocument();
            Element eRaiz = doc.createElement("curs");
            doc.appendChild(eRaiz);
            Element eCurs = doc.createElement("yearCurs");
            eRaiz.appendChild(eCurs);
            Element eStudentsList = doc.createElement("studentsList");
            eRaiz.appendChild(eStudentsList);

            for (int i = 0; i < lis.size(); i++) {


            }


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = null;
            try {
                transformer = transformerFactory.newTransformer();
            } catch (TransformerConfigurationException e) {
                throw new RuntimeException(e);
            }
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("ejercicio.xml"));
            // Transformar y guardar el documento XML en un archivo llamado "ejercicio.xml"
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }


    }






}
