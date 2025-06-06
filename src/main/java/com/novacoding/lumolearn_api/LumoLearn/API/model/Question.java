package com.novacoding.lumolearn_api.LumoLearn.API.model;

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
@Table(name="tb_questions")
public class Question {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String description;
	@NotBlank
	private String explanation;
	@NotNull
	private Long subject_id;
	@NotNull
	private int difficulty_level;
	@NotNull
	private Long author_id;	
	
	@ManyToOne(fetch = FetchType.EAGER)           // EAGER para garantir que venha sempre
    @JoinColumn(
      name = "subject_id",                        // FK na tabela tb_subjects
      referencedColumnName = "id",                // PK de tb_questions
      insertable = false,                         // para não duplicar coluna
      updatable = false
    ) 	
	private Subject subject;
	
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
