package com.capgemini.movieticketbooking.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "theatre")
public class Theatre {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int theatreId;
	private String theatreName;
	private String theatreCity;
	private String managerName;
	private String managerContact;
//	@OneToMany(mappedBy = "theatre",cascade = CascadeType.ALL)
//	private List<Screen> screen;
	@JsonIgnore
	@OneToMany(mappedBy =   "theatre",cascade = CascadeType.ALL)
	private List<Show> show;
}
