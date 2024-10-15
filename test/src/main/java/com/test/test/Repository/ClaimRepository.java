package com.test.test.Repository;

import com.test.test.Entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    // Additional query methods can be defined here if needed
}
