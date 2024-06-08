package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PaymentTypes")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PaymentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
}
