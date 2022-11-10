package com.itcase.automall.utils.mail;

import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import java.util.Date;

@Component
public class MailService {

    /**
     * 注入邮件工具类
     */
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Value("${spring.mail.username}")
    private String sendMailer;




    /**
     * 检测邮件信息类
     * @param to
     * @param subject
     * @param text
     */
    private void checkMail(String to,String subject,String text){
        if(StringUtils.isEmpty(to)){
            throw new RuntimeException("邮件收信人不能为空");
        }
        if(StringUtils.isEmpty(subject)){
            throw new RuntimeException("邮件主题不能为空");
        }
        if(StringUtils.isEmpty(text)){
            throw new RuntimeException("邮件内容不能为空");
        }
    }

    /**
     * 发送纯文本邮件
     * @param to
     * @param subject
     * @param text
     */
    public void sendTextMailMessage(String to,String subject,String text){

        try {
            //true 代表支持复杂的类型
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(),true);
            //邮件发信人
            mimeMessageHelper.setFrom(sendMailer);
            //邮件收信人  1或多个
            mimeMessageHelper.setTo(to.split(","));
            //邮件主题
            mimeMessageHelper.setSubject(subject);
            //邮件内容
            mimeMessageHelper.setText(text);       //填入字符串模板
            //邮件发送时间
            mimeMessageHelper.setSentDate(new Date());

            //发送邮件
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            System.out.println("发送邮件成功："+sendMailer+"->"+to);

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("发送邮件失败："+e.getMessage());
        }
    }

    public void sendCode(String to,String subject,String code){
        String emailTemplate = "<div id=\"EmailBox\" style=\"background:#ecf1f3;padding:16px; min-width:820px;\">\n" +
                "            <div id=\"EmailCenterBox\" style=\"padding: 16px;;background: #fff;;width:820px;height:auto; margin:0px auto;\">\n" +
                "            <div style=\"width:801px;height:auto;margin:0px 11px;background:#fff;\">\n" +
                "                <div style=\"width:801px; margin: 0 auto; background:#fff;padding-top: 30px;\">\n" +
                "                    <div style=\"width:200px;height:100px;background:url(https://a.ideaopen.cn/JanYork/ETYtJ2Jf.png) center no-repeat; margin:auto;background-size: contain;\"></div>\n" +
                "                    <br>\n" +
                "                    <br>\n" +
                "                    <div style=\"FONT-SIZE: 11pt\"><a style=\"color: #000;text-decoration: none;\" href=\"mailto:+"+to+"\" rel=\"noopener\" target=\"_blank\">"+to+"</a>，您好！</div>\n" +
                "                    <br>\n" +
                "                    <br>\n" +
                "                    <div style=\"FONT-SIZE: 11pt\">以下是您用于验证身份的验证码，请在<span style=\"color:red\">5分钟内</span>输入并完成验证。如非本人操作，请忽略此邮件。</div>\n" +
                "                    <br>\n" +
                "                    <br>\n" +
                "                    <hr style=\"BORDER-BOTTOM: #C6E3F0 0px dashed; BORDER-LEFT: #C6E3F0 0px dashed; HEIGHT: 1px; BORDER-TOP: #C6E3F0 1px dashed; BORDER-RIGHT: #C6E3F0 0px dashed\">\n" +
                "                    <br>\n" +
                "                    <div style=\"width: 100%;height: 70px;\">\n" +
                "                        <div id=\"code\"  style=\"margin:0 auto;text-align: center;width: 220px;COLOR: #0094ff; FONT-SIZE: 40pt\">"+code+"</div>\n" +
                "                    </div>\n" +
                "                    <br>\n" +
                "                    <hr style=\"BORDER-BOTTOM: #C6E3F0 0px dashed; BORDER-LEFT: #C6E3F0 0px dashed; HEIGHT: 1px; BORDER-TOP: #C6E3F0 1px dashed; BORDER-RIGHT: #C6E3F0 0px dashed\">\n" +
                "                    <br>\n" +
                "                    <br>\n" +
                "                    <div style=\"position:relative;top:-15px;width:801px;height: 360px;background:url(https://a.ideaopen.cn/JanYork/JJheWBk3.png) 0px 0px no-repeat;\">\n" +
                "                        <div style=\"height:200px;color:#507383;font-size:14px;line-height: 1.4;padding: 20px 92px;\">\n" +
                "                            <div style=\"font-size: 22px;font-weight: bold;\">AutoMall</div>\n" +
                "                            <div style=\"margin:20px 0;color: #6a8895;min-height:4.2em;white-space: pre-wrap;\">此信为系统邮件，请不要直接回复。</div>\n" +
                "                        </div>\n" +
                "                        <div style=\"clear:both;\"></div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>";

        try {
            //true 代表支持复杂的类型
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(),true);
            //邮件发信人
            mimeMessageHelper.setFrom(sendMailer);
            //邮件收信人  1或多个
            mimeMessageHelper.setTo(to.split(","));
            //邮件主题
            mimeMessageHelper.setSubject(subject);
            //邮件内容
            mimeMessageHelper.setText(emailTemplate);       //填入字符串模板
            //邮件发送时间
            mimeMessageHelper.setSentDate(new Date());

            //发送邮件
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            System.out.println("发送邮件成功："+sendMailer+"->"+to);

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("发送邮件失败："+e.getMessage());
        }
    }
}
