package org.example.controller;

import org.example.model.Curs;
import org.example.model.Student;
import org.example.model.Subject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LogicCurs {

//        public static List<Student> changeStudentYear(Curs curs) {
//            List<Student> listaStud = curs.getStudentsList();
//            List<Student> pasan= new ArrayList<>();
//
//            for (Student student : listaStud) {
//                List<Subject> subjects = student.getSubjectList();
//                Iterator<Subject> iterator = subjects.iterator();
//
//                while (iterator.hasNext()) {
//                    Subject sub = iterator.next();
//                    if (sub.getScore() >= 5) {
//                        iterator.remove();
//                    }
//                }
//
//                if (student.getSubjectList().isEmpty()) {
//                    pasan.add(student);
//                }
//            }
//
//            curs2.getStudentsList().addAll(pasan);
//            listaStud.removeAll(pasan);
//
//            return listaStud;
//        }
//


    public static List<Student> checkSubjectScore(Curs curs) {
        List<Student> listaStud = curs.getStudentsList();
        List<Student> studentsRemover = new ArrayList<>();

        for (Student student : listaStud) {
            List<Subject> subjects = student.getSubjectList();
            Iterator<Subject> iterator = subjects.iterator();

            while (iterator.hasNext()) {
                Subject sub = iterator.next();
                if (sub.getScore() >= 5) {
                    iterator.remove();
                }
            }

            if (student.getSubjectList().isEmpty()) {
                studentsRemover.add(student);
            }
        }

        listaStud.removeAll(studentsRemover);

        return listaStud;
    }





}
