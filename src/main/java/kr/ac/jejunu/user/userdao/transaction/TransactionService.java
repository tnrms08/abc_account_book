package kr.ac.jejunu.user.userdao.transaction;

import kr.ac.jejunu.user.userdao.siteUser.SiteUser;
import kr.ac.jejunu.user.userdao.siteUser.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserService userService;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public void addTransaction(Transaction transaction, String username) {
        SiteUser siteUser = userService.getByUsername(username);
        transactionRepository.save(transaction);
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public void updateTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
    public List<Transaction> getTransactionsByTypeAndDateRange(String type, String startDate, String endDate) {
        return transactionRepository.findByTypeAndDateBetween(type, startDate, endDate);
    }

    public List<Transaction> getBySiteUser(String username) {
        SiteUser siteUser = userService.getByUsername(username);
        return transactionRepository.findAllBySiteUser(siteUser);
    }

    public List<Transaction> getTransactionsByTypeAndDateRangeAndSiteUser(String type, String startDate, String endDate, String username) {
        SiteUser siteUser = userService.getByUsername(username);
        return transactionRepository.findByTypeAndDateBetweenAndSiteUser(type,startDate,endDate,siteUser);
    }
}
