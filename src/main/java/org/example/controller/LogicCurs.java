package org.example.controller;

import org.example.model.Curs;
import org.example.model.Student;
import org.example.model.Subject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class LogicCurs {


    public static void putNotes(Curs curs11) {
        List<Student> listaStud = curs11.getStudentsList();
        for (Student s : listaStud) {
            List<Subject> subjects = s.getSubjectList();
            for (Subject sub : subjects) {
                sub.setScore((int) (Math.random() * 7) + 4);

            }
        }
    }

    public static List<Student> changeStudentYear(Curs curs, Curs curs2) {
        Curs sencodcurs = new Curs(2);
        List<Student> listaStud = curs.getStudentsList();
        List<Student> pasan = new ArrayList<>();

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
                student.setSubjectList(sencodcurs.getSubjectList());
                pasan.add(student);
            }
        }

        curs2.getStudentsList().addAll(pasan);

        listaStud.removeAll(pasan);

        return listaStud;
    }


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

    public static List<Student> fillCFirstCurs(Curs curs) {
        int i = 0;
        Curs firstCurs = new Curs();

        List<Student> studentsList = new ArrayList<>();

        for (Student s:curs.getStudentsList() ) {
            studentsList.add(s);

        }

        System.out.println(studentsList.size());
        while (studentsList.size() < 24 && i < curs.getStudentsList().size()) {
            Student student = new Student();
            student.setName("student" + i);
            student.setApellido("surname" + i);
            student.setBirthdate(new Date());

            if (!curs.getStudentsList().get(i).getName().equals(student.getName())) {
                student.setSubjectList(firstCurs.subjectsC1());
                studentsList.add(student);
                i++;
            }
            i++;


        }
        System.out.println(studentsList.size());
        return studentsList;
    }


}
