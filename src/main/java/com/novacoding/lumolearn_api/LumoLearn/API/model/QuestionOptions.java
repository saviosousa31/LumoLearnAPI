package com.novacoding.lumolearn_api.LumoLearn.API.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name="tb_question_options")
public class QuestionOptions {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String description;
	@NotNull
	private short is_correct;
	@NotNull
    @Column(insertable=false, updatable=false)
	private Long question_id;	
	
	@ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id") 	
	@JsonBackReference
	private Question question;
	
	
}
