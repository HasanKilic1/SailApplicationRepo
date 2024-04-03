package com.sail.SailApplication.AdvisorEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvisorRepository extends JpaRepository<Advisor, Long> {
    public Advisor findAdvisorById(Long id);
}
