package com.pc.mailerupt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import xyz.erupt.core.annotation.EruptScan;

@EnableTransactionManagement
@ComponentScan({"xyz.erupt","com.pc.mailerupt"}) // ↓ com.xxx要替换成实际需要扫描的代码包
@EntityScan({"xyz.erupt","com.pc.mailerupt"})    // ↓ 例如DemoApplication所在的包为 com.example.demo
@EruptScan({"xyz.erupt","com.pc.mailerupt"})     // → 则：com.xxx → com.example.demo
@SpringBootApplication
public class MailEruptBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailEruptBootApplication.class, args);
    }

}
