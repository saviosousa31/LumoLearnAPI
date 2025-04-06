package com.novacoding.lumolearn_api.LumoLearn.API.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

	private Long id;
	private String description;
	private String explanation;
	private int subject_id;
	private int difficulty_level;
	private int author_id;	
}
