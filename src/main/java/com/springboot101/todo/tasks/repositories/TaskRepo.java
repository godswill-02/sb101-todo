package com.springboot101.todo.tasks.repositories;

import com.springboot101.todo.tasks.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Mr Uche.
 *
 */
public interface TaskRepo extends JpaRepository<Task, Long> {
}
