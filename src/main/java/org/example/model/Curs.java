package org.example.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Curs {
    @XmlAttribute
    private int yearCurs;

    @XmlElement(name = "studentsList")

    private List<Student> studentsList  = new ArrayList<>();


    @XmlTransient//ME SALE UNA DE MAS AL FINAL SINO LO QUITO
    private List<Subject>subjectList = new ArrayList<>();

    public Curs(){}
    public Curs(int yearCurs) {
        this.yearCurs = yearCurs;
        this.studentsList = generateStudents(yearCurs);

    }


    public List<Student> generateStudents(int curs) {

        try {
            if (curs == 1) {

                List<Subject> subjectsC1 = subjectsC1();
                for (int i = 0; i < 23; i++) {
                    Student student = new Student("student" + i, "surname" + i,   new Date());
                    student.setSubjectList(subjectsC1);
                    studentsList.add(student);
                }
           //     System.out.println(studentsList.size());
            } else {
                List<Subject> subjectsC2 = subjectsC2();
                for (int i = 0; i < 5; i++) {
                    Student student = new Student("student" + i, "surname" + i, new Date());
                    student.setSubjectList(subjectsC2);
                    studentsList.add(student);
                }
           //     System.out.println(studentsList.size());

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return studentsList;
    }




    public List<Subject> subjectsC1(){
        subjectList.add(new Subject("programacion",null));
        subjectList.add(new Subject("entornos",null));
        subjectList.add(new Subject("fol",null));
        subjectList.add(new Subject("base de datos",null));
        subjectList.add(new Subject("sistemas informaticos",null));
        subjectList.add(new Subject("lenguaje de marcas",null));
        return subjectList;
    }
    public List<Subject> subjectsC2(){
        subjectList.add(new Subject("PSP",null));
        subjectList.add(new Subject("acceso a datos",null));
        subjectList.add(new Subject("EIE",null));
        subjectList.add(new Subject("Desarrollo de interf",null));
        subjectList.add(new Subject("Odoo",null));
        subjectList.add(new Subject("programacion de moviles",null));
        return subjectList;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public int getYearCurs() {
        return yearCurs;
    }

    public void setYearCurs(int yearCurs) {
        this.yearCurs = yearCurs;
    }

    public List<Student> getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(List<Student> studentsList) {
        this.studentsList = studentsList;
    }

    @Override
    public String toString() {
        return "Curs{" +
                "yearCurs=" + yearCurs +
                ", studentsList=" + studentsList +
                ", subjectList=" + subjectList +
                '}';
    }
}
