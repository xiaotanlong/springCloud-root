package com.weixin;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * @ProjectName springcloudroot
 * @PackageName com.weixin
 * @Author tanjianglong
 * @CreatedTime 2017/10/31.
 * @Description :Plase give some message
 * 修改记录：1...;2....
 */
@Component
@Order(value = 1)
public class service implements CommandLineRunner {

    public void run(String... arg0) throws Exception {
        //
        System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作<<<<<<<<<<<<<");
        File modelFile;
        if (System.getProperty("os.name").equals("Linux")) {
            File file = new File("/opt/finance_zh.xls");
            if (!file.exists()) {
                InputStream is = service.class.getResourceAsStream("/templates/finance_zh.xls");
                OutputStream os = new FileOutputStream(file);
                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                os.close();
                is.close();
                file.createNewFile();
            }
            modelFile = file;
        } else if (System.getProperty("os.name").equals("Windows XP")) {
            URL excelUrl = this.getClass().getResource("/templates/finance_zh.xls");
            modelFile = new File(excelUrl.toURI());
        }



        //File modelFile = new File(excelUrl.toURI());
        //modelFile.createNewFile();
        //System.out.println(modelFile.getName());

        System.out.println(">>>>>>>>>>>>>>>222222222222222222222222222222<<<<<<<<<<<<<");
    }
}
