package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service.implementation;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.Person;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.Transaction;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class NotificationServiceImp  implements NotificationService {


    private final JavaMailSender javaMailSender;
    private final PersonServiceImp personServiceImp;
    private final TransactionServiceImp transactionServiceImp;

    private final String shoppingCategory = "shopping";

    @Autowired
    public NotificationServiceImp(JavaMailSender javaMailSender, PersonServiceImp personServiceImp, TransactionServiceImp transactionServiceImp) {
        this.javaMailSender = javaMailSender;
        this.personServiceImp = personServiceImp;
        this.transactionServiceImp = transactionServiceImp;
    }


    @Override
    //@Scheduled(cron = "0 0 12 * * MON")
    @Scheduled(cron = "0 11 16 * * MON")
    public void sendWeeklyNotification() {
        List<Person> personList =  this.personServiceImp.getAllPersons();

        for (Person person: personList) {
            String report = generateWeeklyReport(person);
            sendEmail(person.getEmail(), "Weekly Report", report);
        }
    }

    public String generateWeeklyReport(Person person) {
        List<Transaction> transactions = transactionServiceImp.getAllPersonTransactionsByCategoryLastWeek(person.getId(), shoppingCategory);

        // Build the report
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Weekly shopping report for user: ")
                .append(person.getFirstname())
                .append(" ")
                .append(person.getLastName())
                .append("\n\n");

        // Include transaction details
        BigDecimal totalAmountSpent = BigDecimal.ZERO;
        for (Transaction transaction : transactions) {
            reportBuilder.append("Transaction ID: ").append(transaction.getId()).append("\n")
                    .append("Amount: ").append(transaction.getAmount()).append("\n")
                    .append("Category: ").append(transaction.getCategory().getName()).append("\n")
                    .append("Date: ").append(transaction.getDate()).append("\n\n");

            // Accumulate the total amount spent
            totalAmountSpent = totalAmountSpent.add(transaction.getAmount());
        }

        // Include total amount spent
        reportBuilder.append("Total amount spent: $").append(totalAmountSpent).append("\n");

        return reportBuilder.toString();
    }

    @Async
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}
