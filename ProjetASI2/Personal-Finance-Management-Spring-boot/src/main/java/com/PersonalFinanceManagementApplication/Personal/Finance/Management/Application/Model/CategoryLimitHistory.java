package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Category_Limit_History")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CategoryLimitHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonIgnore
    private Person person;


    private BigDecimal monthlyLimit;

    private BigDecimal totalSpent;

    private LocalDateTime dateModified;

    public CategoryLimitHistory(Category category, Person person, BigDecimal monthlyLimit, BigDecimal totalSpent, LocalDateTime dateModified) {
        this.category = category;
        this.person = person;
        this.monthlyLimit = monthlyLimit;
        this.totalSpent = totalSpent;
        this.dateModified = dateModified;
    }
}
