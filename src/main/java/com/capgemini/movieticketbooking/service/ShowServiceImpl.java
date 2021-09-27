package com.capgemini.movieticketbooking.service;

import com.capgemini.movieticketbooking.model.Show;
import com.capgemini.movieticketbooking.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowServiceImpl implements ShowService{
    @Autowired
    private ShowRepository showRepository;
    @Override
    public Show addShow(Show show) {
       return showRepository.save(show);
    }

    @Override
    public Show updateShow(Show show, Integer theatreId, Integer screenId) {
//        Theatre theatre = new Theatre();
//        Screen screen = new Screen();
//        if (theatreId != null) {
//            theatre = theatreRepository.getOne(theatreId);
//            show.setTheatre(theatre);
//        }
//        if (screenId != null) {
//            screen = screenRepository.getOne(screenId);
//            show.setScreen(screen);
//        }
//        showrepository.saveAndFlush(show);
//        return show;
        return null;
    }

    @Override
    public Show removeShow(int showId) {
        Show s = showRepository.findById(showId).get();
        showRepository.delete(s);
        return s;
    }

    @Override
    public Show viewShow(int showId) {
        return showRepository.findById(showId).get();
    }

    @Override
    public List<Show> viewShowList(int theatreid) {
        return showRepository.getAllByTheatreId(theatreid);

    }

    @Override
    public List<Show> viewShowList(LocalDate date) {
        List<Show> showList = new ArrayList<>();
        for(Show show:showRepository.findAll()) {
            if (show.getShowDate() != null && show.getShowDate().isEqual(date))
                showList.add(show);
        }

        return showList;
    }

    @Override
    public List<Show> viewAllShows() {
        return showRepository.findAll();
    }
}
