package com.Sumi.taskmate.Repo;

import com.Sumi.taskmate.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepo extends JpaRepository<Task, Integer> {
}
