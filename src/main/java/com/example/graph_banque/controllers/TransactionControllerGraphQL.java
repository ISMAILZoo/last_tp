package com.example.graph_banque.controllers;

import com.example.graph_banque.DTO.TransactionReq;
import com.example.graph_banque.entities.Compte;
import com.example.graph_banque.entities.Transaction;
import com.example.graph_banque.entities.TypeTransaction;
import com.example.graph_banque.repo.CompteRepository;
import com.example.graph_banque.repo.TransactionRepo;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class TransactionControllerGraphQL {
    private TransactionRepo transactionRepo;
    private CompteRepository compteRepository;

    @MutationMapping
    public Transaction addTransaction(@Argument TransactionReq transactionReq) {

        Compte compte = compteRepository.findById(transactionReq.getCompteId())
                .orElseThrow(() -> new RuntimeException("Compte not found"));

        Transaction transaction = new Transaction();
        transaction.setMontant(transactionReq.getMontant());
        transaction.setDate(transactionReq.getDate());
        transaction.setType(transactionReq.getType());
        transaction.setCompte(compte);


        transactionRepo.save(transaction);

        return transaction;
    }


    @QueryMapping
    public List<Transaction> allTransactions() {
        return transactionRepo.findAll();
    }

    @QueryMapping
    public List<Transaction> compteTransactions(@Argument Long id) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte not found"));
        return transactionRepo.findByCompte(compte);
    }

    @QueryMapping
    public Map<String, Object> transactionStats() {
        long count = transactionRepo.count();
        double sumDepots = transactionRepo.sumByType(TypeTransaction.DEPOT);
        double sumRetraits = transactionRepo.sumByType(TypeTransaction.RETRAIT);
        return Map.of(
                "count", count,
                "sumDepots", sumDepots,
                "sumRetraits", sumRetraits
        );
    }

}
