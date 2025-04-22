package com.novacoding.lumolearn_api.LumoLearn.API.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.novacoding.lumolearn_api.LumoLearn.API.model.UserSettings;

@Repository
public interface UserSettingsRepository extends JpaRepository<UserSettings, Long>{
	public Optional<UserSettings> findByUser_Id(Long user_id);
}
