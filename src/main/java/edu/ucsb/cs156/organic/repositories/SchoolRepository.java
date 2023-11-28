package edu.ucsb.cs156.organic.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import edu.ucsb.cs156.organic.entities.School;

@Repository
public interface SchoolRepository extends CrudRepository<School, String> {
    
}
