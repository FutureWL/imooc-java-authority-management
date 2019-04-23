package io.github.futurewl.imooc.java.authority.management.util;

import io.github.futurewl.imooc.java.authority.management.beans.Mail;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class MailUtil {

    public static boolean send(Mail mail) {

        // TODO
        String from = "624263934@qq.com";
        int port = 25;
        String host = "smtp.qq.com";
        String pass = "weilai09290013";
        String nickname = "慕课网，邮件发送中心";

        HtmlEmail email = new HtmlEmail();
        try {
            email.setHostName(host);
            email.setCharset("UTF-8");
            for (String str : mail.getReceivers()) {
                email.addTo(str);
            }
            email.setFrom(from, nickname);
            email.setSmtpPort(port);
            email.setAuthentication(from, pass);
            email.setSubject(mail.getSubject());
            email.setMsg(mail.getMessage());
            email.send();
            log.info("{} 发送邮件到 {}", from, StringUtils.join(mail.getReceivers(), ","));
            return true;
        } catch (EmailException e) {
            log.error(from + "发送邮件到" + StringUtils.join(mail.getReceivers(), ",") + "失败", e);
            return false;
        }
    }

    public static void main(String[] args) {
        Mail mail = new Mail();
        mail.setMessage("Hello World");
        Set<String> re = new HashSet<>();
        re.add("624263934@qq.com");
        re.add("futurewl0929@qq.com");
        re.add("future4ever@foxmail.com");
        mail.setReceivers(re);
        mail.setSubject("Hello");
        send(mail);
    }

}

