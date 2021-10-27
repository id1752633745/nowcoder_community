package com.nowcoder.community;

import java.io.IOException;

public class WkTest {
    public static void main(String[] args) {
        String cmd = "D:\\develop_tools\\wkhtmltopdf\\bin\\wkhtmltoimage https://www.baidu.com/ D:\\work\\data\\wk\\wk_img\\1.png";
        try {
            Runtime.getRuntime().exec(cmd);
            System.out.println("OK!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
