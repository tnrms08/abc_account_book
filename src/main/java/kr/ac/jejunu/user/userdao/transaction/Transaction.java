package kr.ac.jejunu.user.userdao.transaction;

import jakarta.persistence.*;
import kr.ac.jejunu.user.userdao.siteUser.SiteUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;
    private String price;
    private String category;
    private String memo;
    private String type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private SiteUser siteUser;
}
