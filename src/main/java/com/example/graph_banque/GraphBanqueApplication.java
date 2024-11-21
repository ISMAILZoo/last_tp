package com.example.graph_banque;

import com.example.graph_banque.entities.Compte;
import com.example.graph_banque.entities.Transaction;
import com.example.graph_banque.entities.TypeCompte;
import com.example.graph_banque.entities.TypeTransaction;
import com.example.graph_banque.repo.CompteRepository;
import com.example.graph_banque.repo.TransactionRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class GraphBanqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphBanqueApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CompteRepository compteRepository , TransactionRepo transactionRepo) {
		return args -> {

			Compte compte1 =compteRepository.save(new Compte(null, Math.random() * 9000, new Date(), TypeCompte.COURANT));
			Compte compte2 =compteRepository.save(new Compte(null, Math.random() * 9000, new Date(), TypeCompte.COURANT));
			Compte compte3 =compteRepository.save(new Compte(null, Math.random() * 9000, new Date(), TypeCompte.EPARGNE));

			compteRepository.findAll().forEach(c -> {
				System.out.println(c.toString());
			});

			// Création des transactions pour compte1
			transactionRepo.save(new Transaction(null, 1500.0, new Date(), TypeTransaction.DEPOT, compte1));
			transactionRepo.save(new Transaction(null, 500.0, new Date(), TypeTransaction.RETRAIT, compte1));

			// Création des transactions pour compte2
			transactionRepo.save(new Transaction(null, 2000.0, new Date(), TypeTransaction.DEPOT, compte2));
			transactionRepo.save(new Transaction(null, 1000.0, new Date(), TypeTransaction.RETRAIT, compte2));

			// Création des transactions pour compte3
			transactionRepo.save(new Transaction(null, 3000.0, new Date(), TypeTransaction.DEPOT, compte3));
			transactionRepo.save(new Transaction(null, 700.0, new Date(), TypeTransaction.RETRAIT, compte3));

			// Afficher toutes les transactions
			transactionRepo.findAll().forEach(t -> {
				System.out.println(t.toString());
			});

		};
	}
}
