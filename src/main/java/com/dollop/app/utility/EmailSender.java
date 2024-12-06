package com.dollop.app.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Controller
public class EmailSender {
	@Autowired
	private final JavaMailSender javaMailSender;

	public EmailSender(JavaMailSender javaMailSender) {
		super();
		this.javaMailSender = javaMailSender;
	}
	
	
	public void sendEmail(String to,String subject,String body) {
		MimeMessage mimeMessage=javaMailSender.createMimeMessage();
		try {
			mimeMessage.setSubject(subject);
			mimeMessage.setRecipients(Message.RecipientType.TO, to);
			mimeMessage.setContent(body, "text/html");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		javaMailSender.send(mimeMessage);
		
	}
	public void sendOtp(String email, String otp) {
		System.out.println("email is reay ot send passeord");
		String mess = "<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "  <head>\r\n"
				+ "    <meta charset=\"UTF-8\" />\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\" />\r\n"
				+ "    <title>Static Template</title>\r\n" + "\r\n" + "    <link\r\n"
				+ "      href=\"https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap\"\r\n"
				+ "      rel=\"stylesheet\"\r\n" + "    />\r\n" + "  </head>\r\n" + "  <body\r\n" + "    style=\"\r\n"
				+ "      margin: 0;\r\n" + "      font-family: 'Poppins', sans-serif;\r\n"
				+ "      background: #ffffff;\r\n" + "      font-size: 14px;\r\n" + "    \"\r\n" + "  >\r\n"
				+ "    <div\r\n" + "      style=\"\r\n" + "        max-width: 680px;\r\n"
				+ "        margin: 0 auto;\r\n" + "        padding: 45px 30px 60px;\r\n"
				+ "        background: #f4f7ff;\r\n"
				+ "        background-image: url(https://archisketch-resources.s3.ap-northeast-2.amazonaws.com/vrstyler/1661497957196_595865/email-template-background-banner);\r\n"
				+ "        background-repeat: no-repeat;\r\n" + "        background-size: 800px 452px;\r\n"
				+ "        background-position: top center;\r\n" + "        font-size: 14px;\r\n"
				+ "        color: #434343;\r\n" + "      \"\r\n" + "    >\r\n" + "      <header>\r\n"
				+ "        <table style=\"width: 100%;\">\r\n" + "          <tbody>\r\n"
				+ "            <tr style=\"height: 0;\">\r\n" + "              <td>\r\n" + "                <img\r\n"
				+ "                  alt=\"\"\r\n"
				+ "                  src=\"https://archisketch-resources.s3.ap-northeast-2.amazonaws.com/vrstyler/1663574980688_114990/archisketch-logo\"\r\n"
				+ "                  height=\"30px\"\r\n" + "                />\r\n" + "              </td>\r\n"
				+ "              <td style=\"text-align: right;\">\r\n" + "                <span\r\n"
				+ "                  style=\"font-size: 16px; line-height: 30px; color: #ffffff;\"\r\n"
				+ "                  >02 Dec, 2024</span\r\n" + "                >\r\n" + "              </td>\r\n"
				+ "            </tr>\r\n" + "          </tbody>\r\n" + "        </table>\r\n" + "      </header>\r\n"
				+ "\r\n" + "      <main>\r\n" + "        <div\r\n" + "          style=\"\r\n"
				+ "            margin: 0;\r\n" + "            margin-top: 70px;\r\n"
				+ "            padding: 92px 30px 115px;\r\n" + "            background: #ffffff;\r\n"
				+ "            border-radius: 30px;\r\n" + "            text-align: center;\r\n" + "          \"\r\n"
				+ "        >\r\n" + "          <div style=\"width: 100%; max-width: 489px; margin: 0 auto;\">\r\n"
				+ "            <h1\r\n" + "              style=\"\r\n" + "                margin: 0;\r\n"
				+ "                font-size: 24px;\r\n" + "                font-weight: 500;\r\n"
				+ "                color: #1f1f1f;\r\n" + "              \"\r\n" + "            >\r\n"
				+ "              Your OTP\r\n" + "            </h1>\r\n" + "            <p\r\n"
				+ "              style=\"\r\n" + "                margin: 0;\r\n"
				+ "                margin-top: 17px;\r\n" + "                font-size: 16px;\r\n"
				+ "                font-weight: 500;\r\n" + "              \"\r\n" + "            >\r\n"
				+ "              Hey Tomy,\r\n" + "            </p>\r\n" + "            <p\r\n"
				+ "              style=\"\r\n" + "                margin: 0;\r\n"
				+ "                margin-top: 17px;\r\n" + "                font-weight: 500;\r\n"
				+ "                letter-spacing: 0.56px;\r\n" + "              \"\r\n" + "            >\r\n"
				+ "              Thank you for choosing Us. Use the following OTP\r\n"
				+ "              to complete the procedure to change your email address. OTP is\r\n"
				+ "              valid for\r\n"
				+ "              <span style=\"font-weight: 600; color: #1f1f1f;\">5 minutes</span>.\r\n"
				+ "              Do not share this code with others, including Archisketch\r\n"
				+ "              employees.\r\n" + "            </p>\r\n" + "            <p\r\n"
				+ "              style=\"\r\n" + "                margin: 0;\r\n"
				+ "                margin-top: 60px;\r\n" + "                font-size: 40px;\r\n"
				+ "                font-weight: 600;\r\n" + "                letter-spacing: 25px;\r\n"
				+ "                color: #ba3d4f;\r\n" + "              \"\r\n" + "            >\r\n"
				+ "              " + otp + "\r\n" + "            </p>\r\n" + "          </div>\r\n"
				+ "        </div>\r\n" + "\r\n" + "        <p\r\n" + "          style=\"\r\n"
				+ "            max-width: 400px;\r\n" + "            margin: 0 auto;\r\n"
				+ "            margin-top: 90px;\r\n" + "            text-align: center;\r\n"
				+ "            font-weight: 500;\r\n" + "            color: #8c8c8c;\r\n" + "          \"\r\n"
				+ "        >\r\n" + "          Need help? Ask at\r\n" + "          <a\r\n"
				+ "            href=\"mailto:archisketch@gmail.com\"\r\n"
				+ "            style=\"color: #499fb6; text-decoration: none;\"\r\n"
				+ "            >archisketch@gmail.com</a\r\n" + "          >\r\n" + "          or visit our\r\n"
				+ "          <a\r\n" + "            href=\"\"\r\n" + "            target=\"_blank\"\r\n"
				+ "            style=\"color: #499fb6; text-decoration: none;\"\r\n" + "            >Help Center</a\r\n"
				+ "          >\r\n" + "        </p>\r\n" + "      </main>\r\n" + "\r\n" + "      <footer\r\n"
				+ "        style=\"\r\n" + "          width: 100%;\r\n" + "          max-width: 490px;\r\n"
				+ "          margin: 20px auto 0;\r\n" + "          text-align: center;\r\n"
				+ "          border-top: 1px solid #e6ebf1;\r\n" + "        \"\r\n" + "      >\r\n" + "        <p\r\n"
				+ "          style=\"\r\n" + "            margin: 0;\r\n" + "            margin-top: 40px;\r\n"
				+ "            font-size: 16px;\r\n" + "            font-weight: 600;\r\n"
				+ "            color: #434343;\r\n" + "          \"\r\n" + "        >\r\n"
				+ "          Archisketch Company\r\n" + "        </p>\r\n"
				+ "        <p style=\"margin: 0; margin-top: 8px; color: #434343;\">\r\n"
				+ "          Address 540, City, State.\r\n" + "        </p>\r\n"
				+ "        <div style=\"margin: 0; margin-top: 16px;\">\r\n"
				+ "          <a href=\"\" target=\"_blank\" style=\"display: inline-block;\">\r\n"
				+ "            <img\r\n" + "              width=\"36px\"\r\n" + "              alt=\"Facebook\"\r\n"
				+ "              src=\"https://archisketch-resources.s3.ap-northeast-2.amazonaws.com/vrstyler/1661502815169_682499/email-template-icon-facebook\"\r\n"
				+ "            />\r\n" + "          </a>\r\n" + "          <a\r\n" + "            href=\"\"\r\n"
				+ "            target=\"_blank\"\r\n"
				+ "            style=\"display: inline-block; margin-left: 8px;\"\r\n" + "          >\r\n"
				+ "            <img\r\n" + "              width=\"36px\"\r\n" + "              alt=\"Instagram\"\r\n"
				+ "              src=\"https://archisketch-resources.s3.ap-northeast-2.amazonaws.com/vrstyler/1661504218208_684135/email-template-icon-instagram\"\r\n"
				+ "          /></a>\r\n" + "          <a\r\n" + "            href=\"\"\r\n"
				+ "            target=\"_blank\"\r\n"
				+ "            style=\"display: inline-block; margin-left: 8px;\"\r\n" + "          >\r\n"
				+ "            <img\r\n" + "              width=\"36px\"\r\n" + "              alt=\"Twitter\"\r\n"
				+ "              src=\"https://archisketch-resources.s3.ap-northeast-2.amazonaws.com/vrstyler/1661503043040_372004/email-template-icon-twitter\"\r\n"
				+ "            />\r\n" + "          </a>\r\n" + "          <a\r\n" + "            href=\"\"\r\n"
				+ "            target=\"_blank\"\r\n"
				+ "            style=\"display: inline-block; margin-left: 8px;\"\r\n" + "          >\r\n"
				+ "            <img\r\n" + "              width=\"36px\"\r\n" + "              alt=\"Youtube\"\r\n"
				+ "              src=\"https://archisketch-resources.s3.ap-northeast-2.amazonaws.com/vrstyler/1661503195931_210869/email-template-icon-youtube\"\r\n"
				+ "          /></a>\r\n" + "        </div>\r\n"
				+ "        <p style=\"margin: 0; margin-top: 16px; color: #434343;\">\r\n"
				+ "          Copyright © 2022 Company. All rights reserved.\r\n" + "        </p>\r\n"
				+ "      </footer>\r\n" + "    </div>\r\n" + "  </body>\r\n" + "</html>\r\n" + "";
		sendEmail(email, "OTP", mess);

	}
	

}
