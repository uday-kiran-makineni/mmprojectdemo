package com.test.test.Controller;

import com.test.test.Entity.Policy;
import com.test.test.Service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    // Admin can create a new course
    @PostMapping
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<Policy> createCourse(@RequestBody Policy policy) {
        return ResponseEntity.ok(policyService.createCourse(policy));
    }

    // Admin can delete a course by ID
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        policyService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    // Admin or Learners can view all courses
    @GetMapping
    @PreAuthorize("hasAnyRole('AGENT', 'USER')")
    public List<Policy> getAllCourses() {
        return policyService.getAllCourses();
    }

    // Admin or Learners can view a specific course by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('AGENT', 'USER')")
    public ResponseEntity<Policy> getCourseById(@PathVariable Long id) {
        return policyService.getCourseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
