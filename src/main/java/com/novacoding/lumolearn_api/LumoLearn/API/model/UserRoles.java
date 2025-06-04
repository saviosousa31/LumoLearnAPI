package com.novacoding.lumolearn_api.LumoLearn.API.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor	

@Entity
@Table(name = "tb_user_roles") 
public class UserRoles {

    @Id 
    @Column(insertable=false, updatable=false)
    private Long user_id;
    
    private String role_name; // Ex: "USERS", "ADMINS"

    @ManyToOne
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
		UserRoles other = (UserRoles) obj;
		return Objects.equals(role_name, other.role_name) && Objects.equals(user, other.user)
				&& Objects.equals(user_id, other.user_id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(role_name, user, user_id);
	}
    
    
}
