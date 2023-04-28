package com.example.theater.dao.repositories.emploees.categories;

import com.example.theater.dao.entities.emploees.categories.ActorCategory;
import com.example.theater.dao.entities.emploees.categories.MusicianCategory;
import org.springframework.data.repository.CrudRepository;

public interface MusicianCategoryRepository extends CrudRepository<MusicianCategory, String> {
}