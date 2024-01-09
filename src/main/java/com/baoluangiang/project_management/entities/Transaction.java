package com.baoluangiang.project_management.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction")
@Entity(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "transactionType_id", referencedColumnName = "id")
    private TransactionType transactionType;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
