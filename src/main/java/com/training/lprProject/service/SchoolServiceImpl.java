package com.training.lprProject.service;

import com.training.lprProject.dao.SchoolRepository;
import com.training.lprProject.entity.School;
import com.training.lprProject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolServiceImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public School addSchool(School school) {
        return schoolRepository.save(school);
    }

    @Override
    public void addUsers(List<User> listOfUsers, School school) {
        Set<User> users = school.getUsers();
        users.addAll(listOfUsers);
        schoolRepository.updateSchoolUsers(school.getName(), users);
    }

    @Override
    public Set<User> getUsers(String schoolName) {
        return schoolRepository.getSchoolByName(schoolName).getUsers();
    }

    @Override
    public School getSchool(User user) {
        return schoolRepository.getSchoolByName(user.getSchool().getName());
    }
}
