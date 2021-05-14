package com.training.lprProject.service;

import com.training.lprProject.entity.School;
import com.training.lprProject.entity.User;

import java.util.List;
import java.util.Set;

public interface SchoolService {
    School addSchool(School school);

    void addUsers(List<User> listOfUsers, School school);

    Set<User> getUsers(String schoolName);

    School getSchool(User user);
}
