package org.example.controller;
import org.example.model.Student;
import org.example.model.Subject;
import org.xml.sax.Attributes;
        import org.xml.sax.SAXException;
        import org.xml.sax.helpers.DefaultHandler;

        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.List;

public class MySaxHandler extends DefaultHandler {
    private List<Student> studentsList;
    private Student student;
    private Subject currentSubject;
    private StringBuilder data;

    public List<Student> getStudentsList() {
        return studentsList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        data = new StringBuilder();

        if (qName.equals("studentsList")) {
            student = new Student();
            student.setSubjectList(new ArrayList<>());
        } else if (qName.equals("subject")) {
            currentSubject = new Subject();
            currentSubject.setSubName(attributes.getValue("subName"));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("studentsList")) {
            studentsList.add(student);
            student = null;
        } else if (qName.equals("name")) {
            student.setName(data.toString());
        } else if (qName.equals("apellido")) {
            student.setApellido(data.toString());
        } else if (qName.equals("birthdate")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            try {
                Date birthdate = dateFormat.parse(data.toString());
                student.setBirthdate(birthdate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (qName.equals("subject")) {
            student.getSubjectList().add(currentSubject);
            currentSubject = null;
        } else if (qName.equals("score")) {
            if (currentSubject != null) {
                currentSubject.setScore(Integer.parseInt(data.toString()));
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        data.append(new String(ch, start, length).trim());
    }
}
