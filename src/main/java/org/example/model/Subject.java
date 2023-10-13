package org.example.model;


import javax.xml.bind.annotation.*;

public class Subject {
    @XmlAttribute
    String subName;

    @XmlElement
    private Integer score;


    public Subject(){}
    public Subject(String subName, Integer score) {
        this.subName = subName;
        this.score = score;
    }


    @Override
    public String toString() {
        return "Subjects{" +
                "subName='" + subName + '\'' +
                ", score=" + score +
                '}';
    }
}
