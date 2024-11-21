package com.example.graph_banque.repo;

import com.example.graph_banque.entities.Compte;
import com.example.graph_banque.entities.Transaction;
import com.example.graph_banque.entities.TypeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
    @Query("SELECT t FROM Transaction t JOIN FETCH t.compte WHERE t.compte = :compte")
    List<Transaction> findByCompte(Compte compte);
    @Query("SELECT SUM(t.montant) FROM Transaction t WHERE t.type = :type")
    double sumByType(TypeTransaction type);
}
