//package com.eletronico.pontoapi.core.password;
//
//import com.eletronico.pontoapi.adapters.database.user.UserRepository;
//import com.eletronico.pontoapi.core.user.domain.User;
//import com.eletronico.pontoapi.core.password.dto.PasswordResetRequestDTO;
//import com.eletronico.pontoapi.core.user.services.UserService;
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.MailException;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import java.util.Calendar;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//public class PasswordResetTokenService {
//
//    private PasswordResetTokenRepository repository;
//    private UserService userService;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    private JavaMailSender emailSender;
//    private String passwordToken;
//
//
//    public PasswordResetTokenService(JavaMailSender emailSender, PasswordResetTokenRepository repository, UserService userService){
//        this.emailSender = emailSender;
//        this.repository = repository;
//        this.userService = userService;
//    }
//
//    public PasswordResetToken createToken(PasswordResetRequestDTO passwordResetRequestDTO) {
//        User emailUser = userService.findByEmail(passwordResetRequestDTO.email());
//        if (emailUser == null) {
//            throw new RuntimeException("Não encontrado Email");
//        }
//        this.passwordToken = UUID.randomUUID().toString();
//        PasswordResetToken passwordResetToken = new PasswordResetToken(passwordEncoder.encode(this.passwordToken), emailUser);
//        sendEmailLink();
//        return repository.save(passwordResetToken);
//    }
//    @Transactional
//    public void sendEmailLink() {
//        try {
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setFrom("samuelprogramacao3@gmail.com");
//            message.setTo("samuelprogramacao3@gmail.com");
//            message.setSubject("teste email");
//            message.setText("https://docs.spring.io/spring-framework/reference/integration/email.html");
//            emailSender.send(message);
//        } catch (MailException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public String validateToken(String token) {
//        PasswordResetToken resetToken = repository.findByToken(token);
//        if (resetToken == null) {
//            return "Invalid password reset token";
//        }
//        User user = resetToken.getUser();
//        Calendar calendar = Calendar.getInstance();
//        if ((resetToken.getExpirationTime().getTime() - calendar.getTime().getTime()) < 0) {
//            return "Link alredy expired, resend link";
//        }
//        return "valid";
//    }
//
//    public Optional<User> findUserByToken(String token) {
//        return Optional.ofNullable(repository.findByToken(token).getUser());
//    }
//
//    public PasswordResetToken findPasswordResetToken(String token) {
//        return repository.findByToken(token);
//    }
//
//}
