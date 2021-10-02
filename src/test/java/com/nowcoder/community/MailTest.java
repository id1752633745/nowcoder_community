package com.nowcoder.community;

import com.nowcoder.community.util.MailCilent;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MailTest {
    @Autowired
    private MailCilent mailCilent;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testSendMail() {
        mailCilent.sendMail("17393191951@163.com", "test", "welcome");
    }

    @Test
    public void testHtmlMail() {
        Context context = new Context();
        context.setVariable("username", "study");

        String process = templateEngine.process("/mail/demo", context);

        System.out.println(process);

        mailCilent.sendMail("17393191951@163.com", "TestHtmlMail", process);
    }
}
