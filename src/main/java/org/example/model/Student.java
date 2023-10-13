package org.example.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student {

    private String name;
    private String apellido;
    private Date birthdate;


    private  List<Subject> subjectList;

    public Student(String name, String apellido, Date birthdate) {
        this.name = name;
        this.apellido = apellido;
        this.birthdate = birthdate;
        this.subjectList = new ArrayList<>();
    }

    public Student(){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }


    public List<Subject> getSubjectList() {
        return subjectList;
    }


    @XmlElementWrapper(name = "subjectList")
    @XmlElement(name = "subject")
    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", Apellido='" + apellido + '\'' +
                ", birthdate=" + birthdate +
                ", subjects=" + subjectList +
                '}';
    }
}
