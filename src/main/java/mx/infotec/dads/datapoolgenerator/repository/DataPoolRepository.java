package mx.infotec.dads.datapoolgenerator.repository;

import mx.infotec.dads.datapoolgenerator.domain.DataPool;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the DataPool entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DataPoolRepository extends MongoRepository<DataPool,String> {
    
	public DataPool findById(String id);
	
}
