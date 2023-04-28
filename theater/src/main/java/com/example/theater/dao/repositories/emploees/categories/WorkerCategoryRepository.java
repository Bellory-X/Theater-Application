package com.example.theater.dao.repositories.emploees.categories;

import com.example.theater.dao.entities.emploees.categories.ActorCategory;
import com.example.theater.dao.entities.emploees.categories.WorkerCategory;
import org.springframework.data.repository.CrudRepository;

public interface WorkerCategoryRepository extends CrudRepository<WorkerCategory, String> {
}