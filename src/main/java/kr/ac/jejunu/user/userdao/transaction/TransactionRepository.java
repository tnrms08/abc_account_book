package kr.ac.jejunu.user.userdao.transaction;

import kr.ac.jejunu.user.userdao.siteUser.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByTypeAndDateBetween(String type, String startDate, String endDate);

    List<Transaction> findAllBySiteUser(SiteUser siteUser);

    List<Transaction> findByTypeAndDateBetweenAndSiteUser(String type, String startDate, String endDate, SiteUser siteUser);
}
