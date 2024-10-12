package com.test.test.Controller;

import com.test.test.Entity.Policy;
import com.test.test.Service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private PolicyService policyService;

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Policy>> getPoliciesByUserId(@PathVariable Long userId) {
        List<Policy> policies = policyService.getPoliciesByUserId(userId);
        if (policies.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(policies);
    }
}
