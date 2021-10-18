package com.nowcoder.community;

import com.nowcoder.community.service.AlphaService;
import com.nowcoder.community.util.SensitiveFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class TransactionTest {
    @Autowired
    private AlphaService alphaService;

    @Test
    public void testTransaction1() {
        Object o = alphaService.save1();
        System.out.println(o);
    }

    @Test
    public void testTransaction2() {
        Object o = alphaService.save2();
        System.out.println(o);
    }
}
