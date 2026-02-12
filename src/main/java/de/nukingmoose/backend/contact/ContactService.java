package de.nukingmoose.backend.contact;

import de.nukingmoose.backend.mail.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final MailService mailService;

    @Value("${contact.mail.to}")
    private String targetMail;


    public ContactService(MailService mailService) {
        this.mailService = mailService;
    }

    public void handleContact(ContactRequest request) {
        mailService.sendMail(
                targetMail,
                request.getName(),
                request.getEmail(),
                request.getMessage()
        );
    }
}
