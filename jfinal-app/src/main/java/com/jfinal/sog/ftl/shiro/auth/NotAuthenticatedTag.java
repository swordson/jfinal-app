/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2014 sagyf Yang. The Four Group.
 */

package com.jfinal.sog.ftl.shiro.auth;

import java.io.IOException;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;

/**
 * <p>
 * .
 * </p>
 *
 * @author poplar.yfyang
 * @version 1.0 2012-10-27 10:40 AM
 * @since JDK 1.5
 */
public class NotAuthenticatedTag extends SecureTag {

	@Override
	public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
		if (getSubject() == null || !getSubject().isAuthenticated()) {
			_logger.debug("Subject does not exist or is not authenticated.  Tag body will be evaluated.");
			renderBody(env, body);
		} else {
			_logger.debug("Subject exists and is authenticated.  Tag body will not be evaluated.");
		}
	}
}