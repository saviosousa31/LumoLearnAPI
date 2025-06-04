package com.novacoding.lumolearn_api.LumoLearn.API.model;

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
@Table(name="tb_subjects")
public class Subject {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String name;
	@NotBlank
	private String description;
	@NotNull
	private Long course_id;
	
	@ManyToOne(fetch = FetchType.EAGER)           // EAGER para garantir que venha sempre
    @JoinColumn(
      name = "course_id",                         // FK na tabela tb_subjects
      referencedColumnName = "id",                // PK de tb_courses
      insertable = false,                         // para não duplicar coluna
      updatable = false
    ) 
//	@JsonIgnoreProperties({ 
//	      "password", "email", "phone_number", "play_streak", 
//	      "total_xp", "total_coins", "email_verified", 
//	      "phone_number_verified", "user_settings" 
//	    })
	private Course course;
}
