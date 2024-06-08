package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Budget_History")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BudgetHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public BudgetHistory(Person person, BigDecimal totalBudget ,BigDecimal oldBudget, BigDecimal newBudget, LocalDateTime dateModified) {
        this.person = person;
        this.oldBudget = oldBudget;
        this.newBudget = newBudget;
        this.dateModified = dateModified;
        this.totalBudget = totalBudget;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    @JsonIgnore
    private Person person;

    private BigDecimal totalBudget;

    private BigDecimal oldBudget;

    private BigDecimal newBudget;


    private LocalDateTime dateModified;
}
