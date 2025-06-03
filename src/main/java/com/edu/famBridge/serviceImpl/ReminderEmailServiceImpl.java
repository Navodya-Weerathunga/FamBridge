package com.edu.famBridge.serviceImpl;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.MimeMessageHelper;
import jakarta.mail.internet.MimeMessage;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Logger;

import org.springframework.jdbc.core.JdbcTemplate;

@Service
public class ReminderEmailServiceImpl {
    private static final Logger logger = Logger.getLogger(ReminderEmailServiceImpl.class.getName());

        @Autowired
        private JavaMailSender javaMailSender;


        @Autowired
    private JdbcTemplate jdbcTemplate;

    public void sendReminderEmailService(LocalDate visitingDate, LocalTime startTime,LocalTime endTime, String area, String users){
            try{
                sendReminderEmail(visitingDate,startTime,endTime,area,users);
            }
            catch (MessagingException e){
                throw new RuntimeException("Unable to send  details");
            }
        }

    private void sendReminderEmail(LocalDate visitingDate, LocalTime startTime, LocalTime endTime, String area, String users) throws MessagingException {
        List<String> emailList;

        
        // Fetch emails if the condition is met
        if ("married".equalsIgnoreCase(users)) {
            String query = "SELECT email FROM married_couple";
            emailList = jdbcTemplate.queryForList(query, String.class);
        } else {
            String query = "SELECT email FROM pregnant_women";
            emailList = jdbcTemplate.queryForList(query, String.class);
        }

        System.out.println(emailList);
        for (String email : emailList) {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject("Field Visiting Details");
            mimeMessageHelper.setText(
                    "For Area: " + area + "\n" +
                            "Visiting Date: " + visitingDate + "\n" +
                            "Start Time: " + startTime + "\n" +
                            "End Time: " + endTime + "\n"
            );
            logger.info("Email sent to: " + email);
            javaMailSender.send(mimeMessage);
        }
    }


    public void sendReminderEmailDeleteService(LocalDate visitingDate, LocalTime startTime,LocalTime endTime, String area, String users){
        try{
            sendReminderEmailDelete(visitingDate,startTime,endTime,area,users);
        }
        catch (MessagingException e){
            throw new RuntimeException("Unable to send  details");
        }
    }

    private void sendReminderEmailDelete(LocalDate visitingDate, LocalTime startTime, LocalTime endTime, String area, String users) throws MessagingException {
        List<String> emailList;


        // Fetch emails if the condition is met
        if ("married".equalsIgnoreCase(users)) {
            String query = "SELECT email FROM married_couple";
            emailList = jdbcTemplate.queryForList(query, String.class);
        } else {
            String query = "SELECT email FROM pregnant_women";
            emailList = jdbcTemplate.queryForList(query, String.class);
        }

        System.out.println(emailList);
        for (String email : emailList) {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject("Field Visiting Cancel Details");
            mimeMessageHelper.setText(
                    "For Area: " + area + "\n" +
                            "Visiting Date: " + visitingDate + "\n" +
                            "Start Time: " + startTime + "\n" +
                            "End Time: " + endTime + "\n"+
                            "Canceled due to unavoidale reason please be kind enough with consideration"
            );
            logger.info("Email sent to: " + email);
            javaMailSender.send(mimeMessage);
        }
    }
}
