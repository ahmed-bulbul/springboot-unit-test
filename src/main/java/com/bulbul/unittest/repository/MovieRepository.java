package com.bulbul.unittest.repository;


import com.bulbul.unittest.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	List<Movie> findByGenera(String genera);
}
