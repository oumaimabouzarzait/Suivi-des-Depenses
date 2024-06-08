package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "Persons")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstname;

    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;

    @Column(
            nullable = false,
            columnDefinition = "TEXT",
            unique = true
    )
    private String email;

    private BigDecimal totalBudget = BigDecimal.ZERO;

    private BigDecimal totalBudgetSpend = BigDecimal.ZERO;

    private BigDecimal currenttotalBudget = BigDecimal.ZERO;

}
