package com.thalasoft.toolbox.spring;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContextException;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.env.Environment;

public abstract class AbstractPropertiesVerifier implements BeanFactoryPostProcessor, PriorityOrdered {

    private static Logger logger = LoggerFactory.getLogger(AbstractPropertiesVerifier.class);

    Class configurationClass;

    protected AbstractPropertiesVerifier(Class configurationClass) {
        this.configurationClass = configurationClass;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Environment environment = beanFactory.getBean(Environment.class);
        Field[] fields = configurationClass.getDeclaredFields();
        for (Field field : fields) {
            try {
                String propertyName = (String) field.get(null);
                if (environment.getProperty(propertyName) == null) {
                    throw new ApplicationContextException(
                            "Missing property on application context bootstrap: " + propertyName);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }

}