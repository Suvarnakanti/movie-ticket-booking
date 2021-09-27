package com.capgemini.movieticketbooking.controller;


import com.capgemini.movieticketbooking.exceptions.TheatreNotFoundException;
import com.capgemini.movieticketbooking.model.Theatre;
import com.capgemini.movieticketbooking.service.TheatreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/theatre")
public class TheatreController {
	Logger logger = LoggerFactory.getLogger(TheatreController.class);
	@Autowired
	private TheatreService theatreservice;


	/**
	 * 
	 * @return listOfTheatres
	 * @throws TheatreNotFoundException
	 */
	@GetMapping("/getall")
	public ResponseEntity<List<Theatre>> getAlltheatres() throws TheatreNotFoundException {

		logger.info("-------Theatre List Fetched---------");
		return ResponseEntity.ok(theatreservice.getAllTheatres());
	}

	/**
	 * 
	 * @param t
	 * @return inserted theatre
	 * @throws TheatreNotFoundException
	 */
	@PostMapping("/add")
	public ResponseEntity<Theatre> addTheatre(@RequestBody Theatre t)
			throws TheatreNotFoundException {

		logger.info("-------Theatre Added Successfully---------");
		return new ResponseEntity<>(theatreservice.addTheatre(t), HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param t
	 * @return updated theatre
	 * @throws TheatreNotFoundException
	 */
	@PutMapping("/update")
	public List<Theatre> updateTheatre(@RequestBody Theatre t)
			throws  TheatreNotFoundException {

		logger.info("-------Theatre Updated Successfully---------");
		return theatreservice.updateTheatre(t);
	}

	/**
	 * 
	 * @param theatreId
	 * @return theatre by theatreId
	 * @throws TheatreNotFoundException
	 */
	@GetMapping("/find/{theatreId}")
	public ResponseEntity<Theatre> findTheatre(@PathVariable int theatreId)
			throws  TheatreNotFoundException {

		logger.info("-------Theatre Found with Theatre id" + theatreId + "---------");
		return ResponseEntity.ok(theatreservice.findTheatres(theatreId));
	}

	/**
	 * 
	 * @param theatreId
	 * @return deleted theatre
	 * @throws TheatreNotFoundException
	 */
	@DeleteMapping("/delete/{theatreId}")
	public List<Theatre> deleteMoviesById(@PathVariable int theatreId)
			throws TheatreNotFoundException {

		logger.info("-------Theatre Deleted with Theatre id" + theatreId + "---------");
		return theatreservice.deleteTheatreById(theatreId);
	}
	
	@GetMapping("/findbyMovie/{movieId}")
	public ResponseEntity<List<Theatre>> findTheatreByMovieId(@PathVariable int movieId)
			throws  TheatreNotFoundException {
		return ResponseEntity.ok(theatreservice.findTheatresByMovie(movieId));
	}
	
}
