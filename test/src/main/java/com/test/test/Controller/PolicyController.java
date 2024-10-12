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
@RequestMapping("/api/policies")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    // Admin can create a new course
    @PostMapping
    @PreAuthorize("hasAnyRole('AGENT', 'USER')")
    public ResponseEntity<Policy> createPolicy(@RequestBody Policy policy) {
        try {
            Policy createdPolicy = policyService.createPolicy(policy);
            return ResponseEntity.ok(createdPolicy);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<Void> deletePolicy(@PathVariable Long id) {
        if (policyService.getPolicyById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        policyService.deletePolicy(id);
        return ResponseEntity.noContent().build();
    }


    // Admin or Learners can view all courses
    @GetMapping
    @PreAuthorize("hasAnyRole('AGENT', 'USER')")
    public List<Policy> getAllPolicies() {
        return policyService.getAllPolicies();
    }

    // Admin or Learners can view a specific course by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('AGENT', 'USER')")
    public ResponseEntity<Policy> getPolicyById(@PathVariable Long id) {
        return policyService.getPolicyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
