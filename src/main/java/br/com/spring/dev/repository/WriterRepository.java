package br.com.spring.dev.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.spring.dev.model.Writer; 

@Repository
public interface WriterRepository extends PagingAndSortingRepository<Writer, Long>{

	List<Writer> findAll();
	
}
