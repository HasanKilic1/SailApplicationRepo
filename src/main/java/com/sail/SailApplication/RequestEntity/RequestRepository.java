package com.sail.SailApplication.RequestEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request , Long> {
}
