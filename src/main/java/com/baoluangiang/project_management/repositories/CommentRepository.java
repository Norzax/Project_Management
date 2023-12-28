package com.baoluangiang.project_management.repositories;

import com.baoluangiang.project_management.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
