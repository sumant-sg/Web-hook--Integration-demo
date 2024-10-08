package com.user.school.model;

import jakarta.persistence.*;

@Entity
public class WebHooksDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    private String eventName;

    private String endPointUrl;

    @ManyToOne(cascade = CascadeType.ALL)//many events(add, delete) to one school, so may to one relationship
    @JoinColumn(name = "school_id")
    private SchoolData schoolData;

    public WebHooksDetails() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEndPointUrl() {
        return endPointUrl;
    }

    public void setEndPointUrl(String endPointUrl) {
        this.endPointUrl = endPointUrl;
    }

    public SchoolData getSchoolData() {
        return schoolData;
    }

    public void setSchoolData(SchoolData schoolData) {
        this.schoolData = schoolData;
    }
}
