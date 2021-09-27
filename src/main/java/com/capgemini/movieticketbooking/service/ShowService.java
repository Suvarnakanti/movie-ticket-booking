package com.capgemini.movieticketbooking.service;

import com.capgemini.movieticketbooking.model.Show;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface ShowService {
    public Show addShow(Show show);

    public Show updateShow(Show show,Integer theatreId, Integer screenId);

    public Show removeShow(int showId);

    public Show viewShow(int showId);

    public List<Show> viewShowList(int theatreid); //not implemented yet

    public List<Show> viewShowList(LocalDate date);

    public List<Show> viewAllShows();
}
