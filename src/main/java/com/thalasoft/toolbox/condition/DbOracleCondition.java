package com.thalasoft.toolbox.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class DbOracleCondition implements Condition {

	private static final String DB = "oracle";

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		return context.getEnvironment().getProperty("db") != null && context.getEnvironment().getProperty("db").equals(DB);
	}

}