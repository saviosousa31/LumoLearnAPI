package com.novacoding.lumolearn_api.LumoLearn.API.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="tb_user_settings")
public class UserSettings {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String language;
	private String theme;
	private short sound_effects;	

	@OneToOne
	@JoinColumn(name="user_id", referencedColumnName = "id", unique=true)
	@JsonBackReference
	private User user;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserSettings other = (UserSettings) obj;
		return Objects.equals(id, other.id) && Objects.equals(user, other.user);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, user);
	}
	
	
}
