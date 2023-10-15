package org.example;

import org.example.controller.LogicXML;
import org.example.model.Curs;

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
        LogicXML.readXMLJAXB(curs);
        LogicXML.readXMLJAXB(c2);


        System.out.println(curs);
        System.out.println(c2);

        System.out.println(curs.getStudentsList().size());
        System.out.println(c2.getStudentsList().size());

        //       System.out.println(curs);

    }
}
