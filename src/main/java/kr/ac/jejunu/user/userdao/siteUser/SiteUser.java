package kr.ac.jejunu.user.userdao.siteUser;

import jakarta.persistence.*;
import kr.ac.jejunu.user.userdao.transaction.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;
    @OneToMany(mappedBy = "siteUser", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();
}