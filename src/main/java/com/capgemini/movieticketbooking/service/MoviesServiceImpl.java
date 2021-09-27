package com.capgemini.movieticketbooking.service;

import com.capgemini.movieticketbooking.exceptions.MovieNotFoundException;
import com.capgemini.movieticketbooking.model.Movie;
import com.capgemini.movieticketbooking.model.Show;
import com.capgemini.movieticketbooking.repository.MoviesRepository;
import com.capgemini.movieticketbooking.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MoviesServiceImpl implements MoviesService {

	@Autowired
	private MoviesRepository moviesrepository;
//	@Autowired
//	TheatreRepository theatreRepository;
	@Autowired
	ShowRepository showrepository;
//	@Autowired
//	QueryClass query;

	@Override
	public Movie addMovie(Movie movie) throws MovieNotFoundException {
		if (movie != null) {
			if (moviesrepository.existsById(movie.getMovieId())) {
				throw new MovieNotFoundException("Movie with this id already exists");
			} else {
				/*
				String fileName = StringUtils.cleanPath(file.getOriginalFilename());
				if(fileName.contains(".."))
				{
					System.out.println("not a a valid file");
				}
				try {
					movie.setMovieImage(Base64.getEncoder().encodeToString(file.getBytes()));
				} catch (IOException e) {
					e.printStackTrace();
				}*/
				moviesrepository.saveAndFlush(movie);
			}
		}
		return movie;
	}

	@Override
	public Movie removeMovie(int movieid) {
		Movie m = moviesrepository.findById(movieid).get();
		List<Show> shows = showrepository.findAll();
		for (Show show : shows) {
			if (show.getMovie()!=null && show.getMovie().getMovieId() == movieid) {
				show.setMovie(null);
			}
		}
		moviesrepository.delete(m);
		return m;
	}
	
	@Override
	public Movie updateMovie(Movie movie) {
		moviesrepository.saveAndFlush(movie);
		return moviesrepository.getById(movie.getMovieId());
	}
	
	@Override
	public Movie addMovieToShow(Movie movie,Integer showId) {
		Show show=new Show();
		if (showId != null) {
			show = showrepository.getById(showId);
			movie.setShow(show);
		}
		moviesrepository.saveAndFlush(movie);
		return moviesrepository.getById(movie.getMovieId());
	}

	@Override
	public Movie viewMovie(int movieid) {
		return moviesrepository.findById(movieid).get();
	}

	@Override
	public List<Movie> viewMovieList() throws MovieNotFoundException {
		List<Movie> ml = moviesrepository.findAll();
		//if (ml.size() == 0) throw new MovieNotFoundException("Movies dosen't exist");
		return ml;
	}

	@Override
	public List<Movie> viewMovieList(int theatreid) {
		/*List<Movie> movies = new ArrayList<>();
		List<Show> shows = showrepository.findAll();
		Set<Integer> showIds = new HashSet<>();
		for (Show s : shows) {
			if (s.getTheatre().getTheatreId() == theatreid) {
				showIds.add(s.getShowId());
			}
		}
		for (Integer id : showIds) {
			movies.add(showrepository.getOne(id).getMovie());
		}
		return movies;*/
		return null;
	}

	@Override
	public List<Movie> viewMovieList(LocalDate date) {
		List<Movie> mvList = new ArrayList<>();
		for (Movie movie : moviesrepository.findAll()) {
			if (movie.getMovieDate() != null && movie.getMovieDate().isEqual(date)) {
				mvList.add(movie);
			}
		}
		return mvList;
	}


}