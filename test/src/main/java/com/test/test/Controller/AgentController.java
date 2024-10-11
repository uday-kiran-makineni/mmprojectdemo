package com.test.test.Controller;

import com.test.test.Entity.Policy;
import com.test.test.Service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agent")
public class AgentController {

    @Autowired
    private PolicyService policyService;

    // Admin can create a new course
    @GetMapping("/policies/{id}")
    @PreAuthorize("hasRole('AGENT')")
    public List<Policy> getPolicies() {
        return policyService.getAllCourses();
    }
}