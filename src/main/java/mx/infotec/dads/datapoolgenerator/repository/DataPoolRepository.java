package mx.infotec.dads.datapoolgenerator.repository;

import mx.infotec.dads.datapoolgenerator.domain.DataColumn;
import mx.infotec.dads.datapoolgenerator.domain.DataPool;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Spring Data MongoDB repository for the DataPool entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DataPoolRepository extends MongoRepository<DataPool,String> {
    
	public DataPool findById(String id);
	
	@Query("{ 'id' : ?0 }")
	public DataPool findByIdGroupBy(String id, String groupParam);
		
}				
