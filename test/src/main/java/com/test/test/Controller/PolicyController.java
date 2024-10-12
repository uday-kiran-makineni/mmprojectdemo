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


    @GetMapping
    @PreAuthorize("hasAnyRole('AGENT', 'USER')")
    public List<Policy> getAllPolicies() {
        return policyService.getAllPolicies();
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('AGENT', 'USER')")
    public ResponseEntity<Policy> getPolicyById(@PathVariable Long id) {
        return policyService.getPolicyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('AGENT', 'USER')")
    public ResponseEntity<Policy> updatePolicy(@PathVariable Long id, @RequestBody Policy policyDetails) {
        return policyService.getPolicyById(id)
                .map(existingPolicy -> {
                    // Update the existing policy with the new details
                    existingPolicy.setPolicynumber(policyDetails.getPolicynumber());
                    existingPolicy.setPolicytype(policyDetails.getPolicytype());
                    existingPolicy.setDescription(policyDetails.getDescription());
                    existingPolicy.setAgentId(policyDetails.getAgentId());
                    existingPolicy.setUserId(policyDetails.getUserId());
                    existingPolicy.setStartDate(policyDetails.getStartDate());
                    existingPolicy.setEndDate(policyDetails.getEndDate());
                    existingPolicy.setPremiumAmount(policyDetails.getPremiumAmount());
                    existingPolicy.setCoverageAmount(policyDetails.getCoverageAmount());
                    existingPolicy.setPaymentFrequency(policyDetails.getPaymentFrequency());
                    existingPolicy.setPolicyStatus(policyDetails.getPolicyStatus());
                    existingPolicy.setClaimLimit(policyDetails.getClaimLimit());
                    existingPolicy.setBeneficiaryDetails(policyDetails.getBeneficiaryDetails());
                    existingPolicy.setTermsAndConditions(policyDetails.getTermsAndConditions());
                    existingPolicy.setMobileNumber(policyDetails.getMobileNumber());
                    existingPolicy.setEmail(policyDetails.getEmail());

                    Policy updatedPolicy = policyService.updatePolicy(existingPolicy);
                    return ResponseEntity.ok(updatedPolicy);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
