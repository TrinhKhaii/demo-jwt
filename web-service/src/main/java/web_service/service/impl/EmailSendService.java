package web_service.service.impl;
/*
    Created by Trinh Khai
    Date: 28/05/2022
    Time: 22:07
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSendService {
    @Autowired
    private JavaMailSender mailSender;

    public void setMail(String toEmail,
                        String subject,
                        String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("c1121g1.codegym@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);

        System.out.println("gởi mail thành công");

    }
}
