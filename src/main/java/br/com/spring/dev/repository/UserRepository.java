package br.com.spring.dev.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.spring.dev.model.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>{

	User findByUserName(String username);
}  
