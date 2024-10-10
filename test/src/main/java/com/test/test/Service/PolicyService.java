package com.test.test.Service;


import com.test.test.Entity.Policy;
import com.test.test.Repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyService {

    @Autowired
    private PolicyRepository courseRepository;

    // Create a new course
    public Policy createCourse(Policy policy) {
        return courseRepository.save(policy);
    }

    // Get all courses
    public List<Policy> getAllCourses() {
        return courseRepository.findAll();
    }

    // Get a course by ID
    public Optional<Policy> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    // Delete a course
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}

