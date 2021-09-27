package com.capgemini.movieticketbooking.controller;

import com.capgemini.movieticketbooking.exceptions.ShowNotFoundException;
import com.capgemini.movieticketbooking.model.Show;
import com.capgemini.movieticketbooking.service.ShowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j //->log
@RestController
@RequestMapping("/shows")
public class ShowController {
    @Autowired
    private ShowService showService;

    /*------------ADD A NEW SHOW----------*/
    @PostMapping("/addshow")
    public ResponseEntity<Show> addShow(@RequestBody Show show)  {
        showService.addShow(show);
        log.info("-------Show Added Successfully--------");
        return new ResponseEntity<>(show, HttpStatus.CREATED);
    }

    /*--------UPDATE A SHOW--------*/    //uncomment showserviceimpl to get execute
    @PutMapping("/update")
    public ResponseEntity<Show> updateShow(@RequestBody Show show, @RequestParam(required = false) Integer theatreId,
                                           @RequestParam(required = false) Integer screenId)  {

        ResponseEntity<Show> response = null;
        if (show == null) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            showService.updateShow(show, theatreId, screenId);
            response = new ResponseEntity<>(show, HttpStatus.OK);
            log.info("-------Show Updated Successfully---------");
        }
        return response;
    }

    /*----------VIEW A SHOW BY ID-----------*/
    @GetMapping("/viewbyid/{showId}")
    public ResponseEntity<Show> viewShow(@PathVariable int showId)
            throws ShowNotFoundException {

        ResponseEntity<Show> response = null;
        try {
            Show show = showService.viewShow(showId);
            response = new ResponseEntity<>(show, HttpStatus.OK);
            log.info("-------Show with ShowId " + showId + " Found Successfully---------");
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            throw new ShowNotFoundException("Show with " + showId + " id dosen't exist");
        }
        return response;
    }

    /*------DELETE SHOW BY ID---------*/
    @DeleteMapping("/deletebyid/{showID}")
    public Show deleteShow(@PathVariable int showId) {
        log.info("---------Show with id: %d " + showId + " deleted-----------");
       return showService.removeShow(showId);
    }

    /*-------VIEW SHOW LIST BY DATE--------*/
    @GetMapping("/viewbydate/{showDate}")
    public List<Show> viewShowList(@PathVariable(name = "showDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("-------Date wise shows list----------");
        return showService.viewShowList(date);
    }

    /*-------VIEW SHOW LIST BY THEATRE ID--------*/
    @GetMapping("/viewbytheatreid/{theatreid}")
    public List<Show> viewShowList(@PathVariable int theatreid) {
        log.info("---------------Show by theatreid -----------------");
        return showService.viewShowList(theatreid);
    }
    /*------VIEW ALL SHOWS--------*/
    @GetMapping("/viewall")
    public List<Show> getAllShows() {
        log.info("--------Here are all shows---------");
        return showService.viewAllShows();
    }





}
