package com.novacoding.lumolearn_api.LumoLearn.API.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="tb_courses")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String name;
	@NotBlank
	private String description;
	@NotNull
	private Long user_id;
	@NotNull
	private Long author_id; 
	private Date creation_date;
	
	@ManyToOne(fetch = FetchType.EAGER)           // EAGER para garantir que venha sempre
    @JoinColumn(
      name = "author_id",                         // FK na tabela tb_courses
      referencedColumnName = "id",                // PK de tb_users
      insertable = false,                         // para não duplicar coluna
      updatable = false
    ) 
	@JsonIgnoreProperties({ 
	      "password", "email", "phone_number", "play_streak", 
	      "total_xp", "total_coins", "email_verified", 
	      "phone_number_verified", "user_settings", "roles" 
	    })
	private User author;
}
