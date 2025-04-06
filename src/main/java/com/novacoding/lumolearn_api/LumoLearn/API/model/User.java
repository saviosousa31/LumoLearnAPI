package com.novacoding.lumolearn_api.LumoLearn.API.model;

import java.sql.Blob;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
}
