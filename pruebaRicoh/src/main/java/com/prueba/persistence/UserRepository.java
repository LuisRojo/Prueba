package com.prueba.persistence;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.prueba.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	    Optional<User> findByUserName(String userName);
	    Boolean existsByUserName(String userName);
	}

