package com.bestpractice.api.domain.repository;

import com.bestpractice.api.domain.entity.TaskEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<TaskEntity, String> {
    List<TaskEntity> findAll();
}