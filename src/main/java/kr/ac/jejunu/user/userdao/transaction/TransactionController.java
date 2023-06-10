package kr.ac.jejunu.user.userdao.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transactions")
    public String getAllTransactions(Model model) {
        List<Transaction> transactions = transactionService.getAllTransactions();
        model.addAttribute("transactions", transactions);
        return "transactionList";
    }
    @GetMapping("/transactions/add")
    public String showAddTransactionForm(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "addTransactionForm";
    }

    @PostMapping("/transactions/add")
    public String addTransaction(@ModelAttribute Transaction transaction) {
        transactionService.addTransaction(transaction);
        return "redirect:/transactions";
    }

    @GetMapping("/transactions/{id}/edit")
    public String editTransactionForm(@PathVariable Long id, Model model) {
        Transaction transaction = transactionService.getTransactionById(id);
        model.addAttribute("transaction", transaction);
        return "editTransactionForm";
    }

    @PostMapping("/transactions/{id}/edit")
    public String editTransaction(@PathVariable Long id, @ModelAttribute Transaction transaction) {
        transaction.setId(id);
        transactionService.updateTransaction(transaction);
        return "redirect:/transactions";
    }

    @GetMapping("/transactions/{id}/delete")
    public String deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return "redirect:/transactions";
    }

    @GetMapping("/statistics")
    public String showStatistics(@RequestParam(value = "type",  required = false) String type,
                                 @RequestParam(value = "startDate", required = false) String startDate,
                                 @RequestParam(value = "endDate", required = false) String endDate,
                                 Model model) {
        if (type != null && startDate != null && endDate != null) {
            // startDate와 endDate를 사용하여 특정 기간 내의 내역을 가져옵니다.
            List<Transaction> transactions = transactionService.getTransactionsByTypeAndDateRange(type, startDate, endDate);

            // 통계 계산 로직 수행
            Map<String, Double> statistics = calculateStatistics(transactions);

            model.addAttribute("type", type);
            model.addAttribute("startDate", startDate);
            model.addAttribute("endDate", endDate);
            model.addAttribute("statistics", statistics);
        }

            return "statistics";
    }

    public Map<String, Double> calculateStatistics(List<Transaction> transactions) {

        Map<String, Double> statistics = new HashMap<>();

        // 각 Category별로 금액을 누적하여 계산합니다.
        for (Transaction transaction : transactions) {
            String category = transaction.getCategory();
            double price = Integer.parseInt(transaction.getPrice().replace(",",""));

            if (statistics.containsKey(category)) {
                // 이미 해당 Category의 합계가 존재하는 경우, 누적값에 금액을 더합니다.
                double currentAmount = statistics.get(category);
                statistics.put(category, currentAmount + price);
            } else {
                // 해당 Category의 합계가 처음 계산되는 경우, 새로운 항목을 추가합니다.
                statistics.put(category, price);
            }
        }

        return statistics;
    }

}
