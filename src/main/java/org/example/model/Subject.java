package org.example.model;


import javax.xml.bind.annotation.*;

public class Subject {

    String subName;


    private Integer score;


    public Subject(){}
    public Subject(String subName, Integer score) {
        this.subName = subName;
        this.score = score;
    }

    public String getSubName() {
        return subName;
    }
    @XmlAttribute
    public void setSubName(String subName) {
        this.subName = subName;
    }

    public Integer getScore() {
        return score;
    }

    @XmlElement
    public int setScore(Integer score) {
        this.score = score;
        return score;
    }

    @Override
    public String toString() {
        return "Subjects{" +
                "subName='" + subName + '\'' +
                ", score=" + score +
                '}';
    }
}
