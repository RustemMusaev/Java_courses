package ru.itis.theory;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.itis.practics.JdbcProperties;
import ru.itis.theory.beans.SimpleBean;

public class Program {
    public static void main(String[] args) {
        /**
        ApplicationContext context =
                new ClassPathXmlApplicationContext("ru.itis/context.xml");
        **/
        AbstractApplicationContext context =
                new ClassPathXmlApplicationContext("ru.itis/context.xml");
        SimpleBean simpleBean = (SimpleBean)context.getBean("simpleBean");

        System.out.println("In program: " + simpleBean.getIntValue());

        JdbcProperties properties = context.getBean(JdbcProperties.class);
        System.out.println(properties.getHost());
        context.registerShutdownHook();

    }
}
