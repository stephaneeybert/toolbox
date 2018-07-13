package com.thalasoft.toolbox.spring;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ExamplePropertiesVerifier extends AbstractPropertiesVerifier {

  ExamplePropertiesVerifier() {
    super(PropertyNames.class);
  }

}
