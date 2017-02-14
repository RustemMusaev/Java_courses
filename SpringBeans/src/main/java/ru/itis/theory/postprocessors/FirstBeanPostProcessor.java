package ru.itis.theory.postprocessors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import ru.itis.theory.beans.SimpleBean;

public class FirstBeanPostProcessor implements BeanPostProcessor, Ordered {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof SimpleBean) {
            System.out.println("In FirstBeanPostProcessor before init");
            SimpleBean simpleBean = (SimpleBean)bean;
            System.out.println(simpleBean.getIntValue() + " " + simpleBean.getStringValue());
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof SimpleBean) {
            System.out.println("In FirstBeanPostProcessor after init");
            SimpleBean simpleBean = (SimpleBean)bean;
            System.out.println(simpleBean.getIntValue() + " " + simpleBean.getStringValue());
        }
        return bean;
    }

    public int getOrder() {
        return 0;
    }
}
