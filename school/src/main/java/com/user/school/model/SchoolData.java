package com.user.school.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class SchoolData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String schoolName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schoolData", cascade = CascadeType.ALL)
    private List<WebHooksDetails> webHooksDetails;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schoolData", cascade = CascadeType.ALL)
    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<WebHooksDetails> getWebHooksDetails() {
        return webHooksDetails;
    }

    public void setWebHooksDetails(List<WebHooksDetails> webHooksDetails) {
        this.webHooksDetails = webHooksDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
