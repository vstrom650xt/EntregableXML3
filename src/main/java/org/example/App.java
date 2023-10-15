package org.example;

import org.example.controller.LogicXML;
import org.example.controller.MySaxHandler;
import org.example.model.Curs;
import org.example.model.Student;
import org.example.model.Subject;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.List;

/**
 *
 */
public class App {
    public static void main(String[] args) {

        Curs curs = new Curs(1);
        Curs c2 = new Curs(2);

//        System.out.println(curs);
//        System.out.println(c2);//

        //se inicia el curso sin notas
        LogicXML.writeFileJAXB(curs);
        LogicXML.writeFileJAXB(c2);

//se ponen notas a los alumnos
        LogicXML.readXMLJAXB(curs,c2);
   //     LogicXML.readXMLJAXB(c2);

//
//        System.out.println(curs);
//        System.out.println(c2);
////
//        System.out.println(curs.getStudentsList().size());
//        System.out.println(c2.getStudentsList().size());

        //       System.out.println(curs);


        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            MySaxHandler handler = new MySaxHandler();
            saxParser.parse("1curs.xml", handler);

            List<Student> studentsList = handler.getStudentsList();
            for (Student student : studentsList) {
                System.out.println("Student Name: " + student.getName());
                System.out.println("Student Apellido: " + student.getApellido());
                System.out.println("Student Birthdate: " + student.getBirthdate());
                System.out.println("Subjects: ");
                for (Subject subject : student.getSubjectList()) {
                    System.out.println("  Subject Name: " + subject.getSubName());
                    System.out.println("  Subject Score: " + subject.getScore());
                }
                System.out.println("------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    }

