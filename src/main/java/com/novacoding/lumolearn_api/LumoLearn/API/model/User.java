package com.novacoding.lumolearn_api.LumoLearn.API.model;

import java.sql.Blob;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="tb_users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	
	@NotBlank(message="Nome de usuário não informado!")
	private String username;
	@NotBlank(message="Senha não informada!")
	private String password;
	@Email(message="E-mail inválido!")
	private String email;
	@NotBlank(message="Número de celular não informado!")
	private String phone_number;
	private String nickname;
	private Blob photo;
	private int play_streak;
	private int total_xp;
	private int total_coins;
	private boolean email_verified;
	private boolean phone_number_verified;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private UserSettings user_settings;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<UserRoles> roles = new HashSet<>();

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id) && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, password, username);
	}
	
	
}
