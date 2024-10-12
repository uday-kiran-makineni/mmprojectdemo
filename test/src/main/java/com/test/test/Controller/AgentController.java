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
    @GetMapping("/policies")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Policy> getPolicies() {
        return policyService.getAllPolicies();
    }

    // Get policies by agent ID
    @GetMapping("/agent/{agentId}")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<List<Policy>> getPoliciesByAgentId(@PathVariable Long agentId) {
        List<Policy> policies = policyService.getPoliciesByAgentId(agentId);
        if (policies.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(policies);
    }
}