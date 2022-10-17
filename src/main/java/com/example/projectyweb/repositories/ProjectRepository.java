package com.example.projectyweb.repositories;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;

import com.example.projectyweb.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByUserId(Long postId);

    @Transactional
    void deleteByUserId(long userId);
}
