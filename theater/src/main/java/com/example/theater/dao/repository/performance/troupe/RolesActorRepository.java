package com.example.theater.dao.repository.performance.troupe;

import com.example.theater.dao.entity.employee.Actor;
import com.example.theater.dao.entity.performance.Performance;
import com.example.theater.dao.entity.performance.troupe.RolesActor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface RolesActorRepository extends CrudRepository<RolesActor, Integer> {
}