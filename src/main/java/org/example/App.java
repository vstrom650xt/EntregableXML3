package org.example;

import org.example.controller.Logic;
import org.example.model.Curs;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Curs curs = new Curs(1);
        Curs c2 = new Curs(2);

        System.out.println(curs);
        System.out.println(c2);


        Logic.writeFileJAXB(curs);
        Logic.writeFileJAXB(c2);

    }
}
