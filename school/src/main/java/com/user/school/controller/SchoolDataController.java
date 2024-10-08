package com.user.school.controller;

import com.user.school.model.SchoolData;
import com.user.school.model.Student;
import com.user.school.model.WebHooksDetails;
import com.user.school.service.SchoolDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/demo")
public class SchoolDataController {
    @Autowired
    private SchoolDataRepository schoolDataRepository;


    @PostMapping(path="/addNewSchool") // Map ONLY POST Requests
    public @ResponseBody SchoolData addNewSchool (@RequestParam String schoolName) {
        SchoolData schoolData = new SchoolData();
        schoolData.setSchoolName(schoolName);
        SchoolData schoolDataSaved = schoolDataRepository.save(schoolData);
        return schoolDataSaved;
    }

    @PostMapping(path="/addWebhookEvent/{schoolId}") // Map ONLY POST Requests
    public @ResponseBody String addNewSchool (@PathVariable Integer schoolId, @RequestBody WebHooksDetails webHooksDetails) {
        Optional<SchoolData> schoolData = schoolDataRepository.findById(schoolId);
        List<WebHooksDetails> webHooks = new ArrayList<WebHooksDetails>();
        WebHooksDetails details = new WebHooksDetails();
        details.setEventName(webHooksDetails.getEventName());
        details.setEndPointUrl(webHooksDetails.getEndPointUrl());
        details.setSchoolData(schoolData.get());
        webHooks.add(details);

        schoolData.get().setWebHooksDetails(webHooks);
        schoolDataRepository.save(schoolData.get());

        //SchoolData schoolDataSaved = schoolDataRepository.save(schoolData);
        return "webhook added";
    }

    @PostMapping(path="/addStudent/{schoolId}") // Map ONLY POST Requests
    public @ResponseBody String addStudent (@PathVariable Integer schoolId, @RequestBody Student reqData) {
        Optional<SchoolData> schoolData = schoolDataRepository.findById(schoolId);
        SchoolData schoolData2 = schoolData.get();

        List<Student> students = new ArrayList<Student>();
        Student student = new Student();
        student.setAge(reqData.getAge());
        student.setName(reqData.getName());
        student.setSchoolData(schoolData2);
        students.add(student);

        schoolData2.setStudents(students);
        schoolDataRepository.save(schoolData2);

        WebHooksDetails webHooksDetails = schoolData2.getWebHooksDetails().stream().filter(eventData -> (eventData.getEventName().equals("add")))
                .findFirst().orElse(null);
        if(webHooksDetails != null && webHooksDetails.getEndPointUrl() != null)  {
            String url = webHooksDetails.getEndPointUrl();//localhost:9000
            url += "/" + reqData.getName();
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(url, String.class);
            System.out.println("result: " + result);
        }
        //send as webhook
        return "Student added";
    }
}
