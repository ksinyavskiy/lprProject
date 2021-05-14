package com.training.lprProject.dao;

import com.training.lprProject.entity.School;
import com.training.lprProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface SchoolRepository extends JpaRepository<School, Long> {
    School getSchoolByName(String schoolName);

    @Modifying
    @Query("UPDATE School s SET s.users = :users WHERE s.name = :schoolName")
    void updateSchoolUsers(String schoolName, Set<User> users);
}
