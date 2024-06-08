package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Transactions")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Person person;

    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_category")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "budget_history_id")
    private BudgetHistory budgetHistory;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_Limit_History_id")
    private CategoryLimitHistory categoryLimitHistory;

    private Date date;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "id_typePayment")
    private PaymentType paymentType;

    public Transaction(Person person, BigDecimal amount, Category category, BudgetHistory budgetHistory, Date date, PaymentType paymentType) {
        this.person = person;
        this.amount = amount;
        this.category = category;
        this.budgetHistory = budgetHistory;
        this.date = date;
        this.paymentType = paymentType;
    }
    public Transaction(Person person, BigDecimal amount, Category category, BudgetHistory budgetHistory, Date date, PaymentType paymentType
    , CategoryLimitHistory categoryLimitHistory) {
        this.person = person;
        this.amount = amount;
        this.category = category;
        this.budgetHistory = budgetHistory;
        this.date = date;
        this.paymentType = paymentType;
        this.categoryLimitHistory = categoryLimitHistory;
    }
}
