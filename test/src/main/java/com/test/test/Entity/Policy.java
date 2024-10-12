package com.test.test.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "policies")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String policynumber;
    private String policytype;
    private String description;
    private Long agentId;
    private Date startDate;
    private Date endDate;
    private Long userId;
    private Double premiumAmount;
    private Double coverageAmount;
    private String paymentFrequency;
    private String policyStatus;
    private Double claimLimit;
    private String beneficiaryDetails;
    private String termsAndConditions;
    private String mobileNumber;
    private String email;
}

