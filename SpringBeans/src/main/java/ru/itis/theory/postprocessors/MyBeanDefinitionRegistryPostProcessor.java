package ru.itis.theory.postprocessors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import ru.itis.theory.beans.SimpleBean;

public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("In MyBeanDefinitionRegistryPostProcessor ");
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(SimpleBean.class);
        builder.addPropertyValue("intValue", 777);
        builder.addPropertyValue("stringValue", "Lucky");
        registry.registerBeanDefinition("simpleBean", builder.getBeanDefinition());
    }

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
