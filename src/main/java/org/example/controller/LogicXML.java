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

    public static boolean fileExist(Path path) {
        return Files.exists(path);

    }

    public static void writeFileJAXB(Curs curs) {

        try {
            JAXBContext context = JAXBContext.newInstance(Curs.class);
            Marshaller marshaller = context.createMarshaller();
            File xmlFile = new File( curs.getYearCurs() +"curs.xml"); // Define the XML file path
            marshaller.marshal(curs, xmlFile);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void readXMLJAXB(Curs curs) {
        Path path = Paths.get(curs.getYearCurs() + "curs.xml");

        if (Files.exists(path)) {

            try {
                JAXBContext context = JAXBContext.newInstance(Curs.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                Curs curs1 = (Curs) unmarshaller.unmarshal(path.toFile());
                curs1.getStudentsList();

                putNotes(curs1);

                    LogicCurs.checkSubjectScore(curs1);
                    writeFileJAXB(curs1);




            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }

        } else {
            System.out.println("file not found");
        }
    }

    private static void putNotes(Curs curs11) {
        List<Student> listaStud = curs11.getStudentsList();
        for (Student s:listaStud ) {
            List<Subject> subjects = s.getSubjectList();
            for (Subject sub: subjects ) {
                sub.setScore((int)(Math.random()*(10 +5)+0));

            }
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
