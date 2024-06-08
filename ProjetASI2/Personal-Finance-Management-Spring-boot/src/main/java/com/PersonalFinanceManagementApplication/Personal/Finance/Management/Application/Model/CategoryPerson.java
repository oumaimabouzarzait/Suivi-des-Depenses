package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Category_Person")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CategoryPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "id_person")
    @JsonIgnore
    private Person person;

    private BigDecimal monthlyLimit;

    private BigDecimal totalSpent ;

    public CategoryPerson(Category category, Person person, BigDecimal monthlyLimit) {
        this.category = category;
        this.person = person;
        this.monthlyLimit = monthlyLimit;
        this.totalSpent = BigDecimal.ZERO;
    }
}
