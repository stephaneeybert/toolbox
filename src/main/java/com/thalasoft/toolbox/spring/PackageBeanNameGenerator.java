package com.thalasoft.toolbox.spring;

import java.beans.Introspector;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;

public class PackageBeanNameGenerator implements BeanNameGenerator {

  public PackageBeanNameGenerator() {  
  }
  
  public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
    return Introspector.decapitalize(definition.getBeanClassName());
  }

}