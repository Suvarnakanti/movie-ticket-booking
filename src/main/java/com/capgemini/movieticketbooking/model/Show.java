package com.capgemini.movieticketbooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "show")
public class Show {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int showId;
    private LocalDate showDate;
    private LocalDateTime showStartTime;
    private LocalDateTime showEndTime;
    private String showName;
    @JsonIgnore  //written lately
    @ManyToOne                             //1
    @JoinColumn(name = "movie_movie_id") //2 these two lines are written by intellij itself
    private Movie movie;
    private int screenId;
    private int theatreId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "theatre_theatre_id")
    private Theatre theatre;

}
