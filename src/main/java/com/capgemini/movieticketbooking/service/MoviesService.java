package com.capgemini.movieticketbooking.service;

import com.capgemini.movieticketbooking.exceptions.MovieNotFoundException;
import com.capgemini.movieticketbooking.model.Movie;

import java.time.LocalDate;
import java.util.List;

public interface MoviesService {

	public Movie addMovie(Movie movie) throws MovieNotFoundException;

	public Movie removeMovie(int movieid) throws MovieNotFoundException;
	
	public Movie updateMovie(Movie movie) throws MovieNotFoundException;
	
	public Movie addMovieToShow(Movie movie, Integer showId) throws MovieNotFoundException;

	public Movie viewMovie(int movieid) throws MovieNotFoundException;

	public List<Movie> viewMovieList() throws MovieNotFoundException;

	public List<Movie> viewMovieList(int theatreid);

	public List<Movie> viewMovieList(LocalDate date);
}