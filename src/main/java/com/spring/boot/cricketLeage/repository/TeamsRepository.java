package com.spring.boot.cricketLeage.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.cricketLeage.model.Teams;
@Repository
public interface TeamsRepository extends CrudRepository<Teams, Long>{

}
