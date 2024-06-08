package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Users")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    private String role;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_person")
    private Person person;

    public User(User user) {

    }
}
