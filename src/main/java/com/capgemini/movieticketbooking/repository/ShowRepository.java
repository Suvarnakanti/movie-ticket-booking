package com.capgemini.movieticketbooking.repository;

import com.capgemini.movieticketbooking.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Integer> {
//    @Query("select s from Show s where s.theatre.theatreId = :id")
    List<Show> getAllByTheatreId(@Param("theatreid") int theatreid);
}
