package org.example.controller;

import org.example.model.Curs;
import org.example.model.Student;
import org.example.model.Subject;
import org.w3c.dom.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
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


        //        LogicCurs.putNotes(curs11);
         //       LogicCurs.putNotes(curs22);

                //              System.out.println(curs22);


                //   LogicCurs.checkSubjectScore(curs22);
                //  LogicCurs.changeStudentYear(curs11,curs22);

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

    public static void generateXML(Curs curs) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element cursElement = doc.createElement("curs");
            doc.appendChild(cursElement);
            Attr attr = doc.createAttribute("subName");
            attr.setValue("1");
            cursElement.setAttributeNode(attr);


            for (Student student :  curs.getStudentsList()) {
                Element studentElement = doc.createElement("studentsList");
                cursElement.appendChild(studentElement);

                Element nameElement = doc.createElement("name");
                nameElement.appendChild(doc.createTextNode(student.getName()));
                studentElement.appendChild(nameElement);

                Element apellidoElement = doc.createElement("apellido");
                apellidoElement.appendChild(doc.createTextNode(student.getApellido()));
                studentElement.appendChild(apellidoElement);

                Element birthdateElement = doc.createElement("birthdate");
                birthdateElement.appendChild(doc.createTextNode(student.getBirthdate().toString()));
                studentElement.appendChild(birthdateElement);

                Element subjectListElement = doc.createElement("subjectList");
                studentElement.appendChild(subjectListElement);

                for ( Subject sub:  student.getSubjectList()){
                    Element subjectElement = doc.createElement("subject");
                    subjectElement.setAttribute("subName", sub.getSubName());
                    subjectListElement.appendChild(subjectElement);

                    Element scoreElement = doc.createElement("score");
                    scoreElement.appendChild(doc.createTextNode(sub.getScore() == null ? "" : sub.getScore().toString()));
                    subjectElement.appendChild(scoreElement);
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = null;
            try {
                transformer = transformerFactory.newTransformer();
            } catch (TransformerConfigurationException e) {
                throw new RuntimeException(e);
            }
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(curs.getYearCurs() + "ejercicioDOM.xml"));
            // Transformar y guardar el documento XML en un archivo llamado "ejercicio.xml"
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readDom(Curs curs1, Curs curs2) {
        Path path = Paths.get(curs1.getYearCurs() + "curs.xml");
        Path path2 = Paths.get(curs2.getYearCurs() + "curs.xml");

        try {
            File xmlFile = new File(path.toUri());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            Element rootElement = doc.getDocumentElement();

            int yearCurs = Integer.parseInt(rootElement.getAttribute("yearCurs"));

            System.out.println("yearCurs: " + yearCurs);

            NodeList studentList = doc.getElementsByTagName("studentsList");

            for (int i = 0; i < studentList.getLength(); i++) {
                Node studentNode = studentList.item(i);
                if (studentNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element studentElement = (Element) studentNode;

                    // Extrae los elementos dentro de cada estudiante
                    String name = studentElement.getElementsByTagName("name").item(0).getTextContent();
                    String apellido = studentElement.getElementsByTagName("apellido").item(0).getTextContent();
                    String birthdate = studentElement.getElementsByTagName("birthdate").item(0).getTextContent();

                    System.out.println("Nombre: " + name);
                    System.out.println("Apellido: " + apellido);
                    System.out.println("Fecha de nacimiento: " + birthdate);

                    // Obtén la lista de asignaturas para el estudiante
                    NodeList subjectList = studentElement.getElementsByTagName("subject");

                    for (int j = 0; j < subjectList.getLength(); j++) {
                        Node subjectNode = subjectList.item(j);
                        if (subjectNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element subjectElement = (Element) subjectNode;

                            String subName = subjectElement.getAttribute("subName");
                            String score = subjectElement.getElementsByTagName("score").item(0).getTextContent();

                            System.out.println("Nombre de la asignatura: " + subName);
                            System.out.println("Puntuación: " + score);
                        }
                    }
                    System.out.println("-----------------------------------------------------------------------------");

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readSAX(Curs curs) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            // Specify the XML file you want to parse
            File xmlFile = new File("1curs.xml");
            saxParser.parse(xmlFile, new MyHandler());
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    static class MyHandler extends DefaultHandler {
        private boolean inStudentsList = false;
        private boolean inSubject = false;
        private boolean inScore = false;
        private String currentSubjectName;
        private int currentScore;
        private StringBuilder currentElementValue;

        private String studentName;
        private String studentSurname;
        private String birthdate;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            currentElementValue = new StringBuilder();

            if (qName.equalsIgnoreCase("studentsList")) {
                inStudentsList = true;
            } else if (qName.equalsIgnoreCase("subject")) {
                inSubject = true;
                currentSubjectName = attributes.getValue("subName");
            } else if (qName.equalsIgnoreCase("score")) {
                inScore = true;
            } else if (qName.equalsIgnoreCase("name") || qName.equalsIgnoreCase("apellido") || qName.equalsIgnoreCase("birthdate")) {
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (inStudentsList) {
                if (qName.equalsIgnoreCase("studentsList")) {
                    inStudentsList = false;
                    System.out.println("Student Name: " + studentName);
                    System.out.println("Student Surname: " + studentSurname);
                    System.out.println("Birthdate: " + birthdate);
                } else if (qName.equalsIgnoreCase("subject")) {
                    inSubject = false;
                    System.out.println("Subject: " + currentSubjectName + ", Score: " + currentScore);
                } else if (qName.equalsIgnoreCase("score")) {
                    inScore = false;
                    currentScore = Integer.parseInt(currentElementValue.toString().trim());
                }
            }
            if (qName.equalsIgnoreCase("name")) {
                studentName = currentElementValue.toString().trim();
            } else if (qName.equalsIgnoreCase("apellido")) {
                studentSurname = currentElementValue.toString().trim();
            } else if (qName.equalsIgnoreCase("birthdate")) {
                birthdate = currentElementValue.toString().trim();
            }

        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            currentElementValue.append(new String(ch, start, length).trim());
        }

    }
}