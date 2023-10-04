/* (C)2022 */
package org.wakanda.framework.mailer;

import java.io.File;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * Utility to send html emails
 *
 * @author vipulmeehnia
 * @date 2021-09-11
 * @since JDK1.8
 */
@Component
public class EmailSender {

  @Autowired private JavaMailSender javaMailSender;

  @Value("${rara.email.from.name}")
  private String fromName;

  @Value("${rara.email.from.email}")
  private String fromEmail;

  public void sendHtmlMail(String to, String subject, String message) {

    MimeMessage msg = javaMailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(msg, false);
      helper.setTo(to);
      helper.setFrom(fromName + " <" + fromEmail + ">");
      helper.setSubject(subject);
      helper.setText(message, true);

      javaMailSender.send(msg);

    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }

  public void sendMailWithAttachment(
      String to, String subject, String message, MailAttachment attachment)
      throws MessagingException {

    MimeMessage msg = javaMailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(msg, true);
      helper.setTo(to);
      helper.setFrom(fromName + " <" + fromEmail + ">");
      helper.setSubject(subject);
      helper.setText(message, true);
      helper.addAttachment(
          attachment.getName(), new FileSystemResource(new File(attachment.getPath())));

      javaMailSender.send(msg);
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }
}
