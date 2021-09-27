package com.capgemini.movieticketbooking.repository;

import com.capgemini.movieticketbooking.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MoviesRepository extends JpaRepository<Movie, Integer> {
    List<Movie> getAllBymovieDate(LocalDate date);
}
