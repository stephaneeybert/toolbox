package com.thalasoft.toolbox.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class EnvPreProdCondition implements Condition {

	private static final String ENV = "preprod";

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		return context.getEnvironment().getProperty("env") != null
				&& context.getEnvironment().getProperty("env").equals(ENV);
	}

}