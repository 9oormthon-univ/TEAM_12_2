package com.letsRoll.letsRoll.Project.repository;


import com.letsRoll.letsRoll.Project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findProjectByTitle(String Title);

    Optional<Project> findProjectById(Long Id);
}
