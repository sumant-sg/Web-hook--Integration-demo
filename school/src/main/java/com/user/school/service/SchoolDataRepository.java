package com.user.school.service;

import com.user.school.model.SchoolData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolDataRepository extends JpaRepository<SchoolData, Integer> {

}
