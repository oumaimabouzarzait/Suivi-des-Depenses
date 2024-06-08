package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.Person;

public interface NotificationService {
    public void sendWeeklyNotification();

    public String generateWeeklyReport(Person person);

    public void sendEmail(String to, String subject, String text);
}
