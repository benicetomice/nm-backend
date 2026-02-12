package de.nukingmoose.backend.contact;

import de.nukingmoose.backend.mail.MailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/mail")
public class MailTestController {
 private final MailService mailService;

 public MailTestController(MailService mailService) {
     this.mailService = mailService;
 }

 @GetMapping("/test")
    public ResponseEntity<String> sendTestMail() {
     mailService.sendMail(, "test", "test", "test");
     return ResponseEntity.ok("Test Mail gesendet");
 }

}
