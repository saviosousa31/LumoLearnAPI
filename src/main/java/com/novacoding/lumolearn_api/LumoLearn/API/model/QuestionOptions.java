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
@Table(name="tb_questions_options")
public class QuestionOptions {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String description;
	@NotNull
	private boolean is_correct;
	@NotNull
	private Long question_id;	
	
	@ManyToOne(fetch = FetchType.EAGER)           // EAGER para garantir que venha sempre
    @JoinColumn(
      name = "question_id",                        // FK na tabela tb_questions
      referencedColumnName = "id",                 // PK de tb_questions_options
      insertable = false,                         // para não duplicar coluna
      updatable = false
    ) 	
	private Question question;
}
