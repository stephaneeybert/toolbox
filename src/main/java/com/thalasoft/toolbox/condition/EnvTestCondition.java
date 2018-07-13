package com.thalasoft.toolbox.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class EnvTestCondition implements Condition {

	private static final String ENV_TEST = "test";

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		return context.getEnvironment().getProperty("env") != null
				&& context.getEnvironment().getProperty("env").equals(ENV_TEST);
	}

}
