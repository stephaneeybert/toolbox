package com.thalasoft.toolbox.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class DbH2Condition implements Condition {

	private static final String DB = "h2";

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		return context.getEnvironment().getProperty("db") != null
				&& context.getEnvironment().getProperty("db").equals(DB);
	}

}
