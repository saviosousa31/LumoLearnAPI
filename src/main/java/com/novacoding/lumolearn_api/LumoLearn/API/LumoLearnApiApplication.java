package com.novacoding.lumolearn_api.LumoLearn.API;

import java.sql.Connection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.novacoding.lumolearn_api.LumoLearn.API.service.DatabaseService;

@SpringBootApplication
public class LumoLearnApiApplication {

    public static void main(String[] args) {
        // Inicializa o contexto do Spring Boot
        ApplicationContext context = SpringApplication.run(LumoLearnApiApplication.class, args);
        
        // Obtém a instância de DatabaseService do contexto do Spring
        DatabaseService databaseService = context.getBean(DatabaseService.class);

        // Testa a conexão
        try (Connection conexao = databaseService.getConnection()) {
            System.out.println("✅ Conexão efetuada com sucesso!");
        } catch (Exception e) {
            System.err.println("❌ Erro ao conectar ao banco de dados!");
            e.printStackTrace();
        }
    }
}
