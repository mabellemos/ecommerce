package com.compasso.ecommerce_app.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.compasso.ecommerce_app.app.dto.product.ProductDTO;
import com.compasso.ecommerce_app.app.dto.sale.SaleDTO;
import com.compasso.ecommerce_app.app.dto.sale.SaleProductDTO;
import com.compasso.ecommerce_app.core.exception.EmailException;
import com.compasso.ecommerce_app.core.model.Sale;
import com.compasso.ecommerce_app.core.repository.SaleRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    SaleRepository saleRepository;

    @Value("${spring.mail.username}")
    private String userName;

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.password}")
    private String senha;

    @Value("${spring.mail.email.remetente}")
    private String emailRemetente;

    public JavaMailSender javaMailSender() {

        JavaMailSenderImpl sendEmail = new JavaMailSenderImpl();

        Properties prop = new Properties();

        sendEmail.setHost(host);
        sendEmail.setPort(465);
        sendEmail.setUsername(userName);
        sendEmail.setPassword(senha);
        sendEmail.setProtocol("smtp");
        sendEmail.setDefaultEncoding("UTF-8");
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.ssl.enable", true);
        sendEmail.setJavaMailProperties(prop);

        return sendEmail;
    }

    public void sendMessage(String to, String subject, String text) throws MessagingException, EmailException{

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(emailRemetente);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        emailSender.send(message);
    }

    public void listEmail(SaleDTO saleDTO) throws EmailException, MessagingException{

        List<Sale> list = saleRepository.findAll();
        List<Sale> listInvoice = saleRepository.findByInvoice(saleDTO.getInvoice(), list);
        List<SaleProductDTO> listProduct = new ArrayList<>();

        for (Sale sale : listInvoice) {
            SaleProductDTO saleProductDTO = new  SaleProductDTO();

            saleProductDTO.setIdProduct(sale.getProduct().getId());
            saleProductDTO.setAmount(sale.getAmount());
            saleProductDTO.setValue(sale.getValue());

            listProduct.add(saleProductDTO);
        }

        saleDTO.setListProduct(listProduct);

    }

    public void sendEmail(SaleDTO saleDTO) throws EmailException, MessagingException{

        listEmail(saleDTO);

        String listPr = "";

        List<SaleProductDTO> listProduct = saleDTO.getListProduct();

        for (SaleProductDTO product : listProduct) {
            listPr += "ID do produto: " + product.getIdProduct().toString() + "<br/>" + "Quantidade: " + product.getAmount().toString() + "<br/>" + "Valor Unitário: " + product.getValue().toString() + "<br/>";
        }

        this.emailSender = javaMailSender();
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        try {
            helper.setFrom("mils2@aluno.ifal.edu.br");
            helper.setTo(emailRemetente);

            helper.setSubject("Produtos Comprados");

            StringBuilder sBuilder = new StringBuilder();
            sBuilder.append("<html>"
                    +"<body><img src='' "
                    +"width='290' border='0' style='padding:22px;'>"
                    +"<div style='font-family: fantasy;font-size:30px; color:rgb(6, 175, 79); font-weight:bold; border-style: groove; border-left: none; border-right: none; padding-left: 22px;'>"
                    +"16 BITS</div>"
                    +"<div style='font-family: monospace; font-size: 15px; color:rgb(0, 0, 0); padding-top: 20px;'>"
                    +"Detalhes da Compra: <br/>"
                    + listPr
                    +"<br/>"
                    +"<br/><br/></div>"
                    +"<div style='color:rgb(169, 166, 166);"
                    +"font:size 12px;border-top-style:double;border-color:rgb(169, 166, 166);padding-top:5px'>"
                    +"<i>Att, 16 Bits</i></div>"
                    +"</body></html>'");

            helper.setText(sBuilder.toString(),true);

            emailSender.send(message);
        }catch (Exception e) {
            throw new EmailException("Houve um erro"+ e.getMessage());
        }
    }

    public void emailProductStock(ProductDTO productDTO) throws EmailException, MessagingException {

        this.emailSender = javaMailSender();
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        try {
            helper.setFrom("mils2@aluno.ifal.edu.br");
            helper.setTo(emailRemetente);

            helper.setSubject("Estoque");

            StringBuilder sBuilder = new StringBuilder();
            sBuilder.append("<html>"
                    +"<body><img src='' "
                    +"width='290' border='0' style='padding:22px;'>"
                    +"<div style='font-family: fantasy;font-size:30px; color:rgb(6, 175, 79); font-weight:bold; border-style: groove; border-left: none; border-right: none; padding-left: 22px;'>"
                    +"16 BITS</div>"
                    +"<div style='font-family: monospace; font-size: 15px; color:rgb(0, 0, 0); padding-top: 20px;'>"
                    +"Produtos em falta: <br/>"
                    +productDTO.getName()
                    +"<br/>"
                    +"<br/><br/></div>"
                    +"<div style='color:rgb(169, 166, 166);"
                    +"font:size 12px;border-top-style:double;border-color:rgb(169, 166, 166);padding-top:5px'>"
                    +"<i>Att, 16 Bits</i></div>"
                    +"</body></html>'");
            helper.setText(sBuilder.toString(),true);

            emailSender.send(message);

        }catch (Exception e) {
            throw new EmailException("Houve um erro"+ e.getMessage());
        }
    }
}
