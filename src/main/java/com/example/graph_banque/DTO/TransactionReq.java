package com.example.graph_banque.DTO;

import com.example.graph_banque.entities.TypeTransaction;
import lombok.Data;

import java.util.Date;
@Data
public class TransactionReq {
    private Long compteId;
    private double montant;
    private Date date;
    private TypeTransaction type;
}
