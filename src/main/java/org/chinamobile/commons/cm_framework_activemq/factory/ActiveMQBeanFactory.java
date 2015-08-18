package org.chinamobile.commons.cm_framework_activemq.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class ActiveMQBeanFactory {

	/**
	 * This is a instance of ApplicationContext, be used to get beans.
	 */
	private ApplicationContext context = null;

	/**
	 * This is a instance of BeanFactory.
	 */
	private static ActiveMQBeanFactory instance = null;

	/**
	 * Don't let anyone instantiate this class. And initialize the spring
	 * ApplicationContext.
	 */
	private ActiveMQBeanFactory() {
		context = new ClassPathXmlApplicationContext(
				"classpath:applicationContext-ACTIVEMQ-JMS.xml");
	}

	/**
	 * @return the instance of BeanFactory.
	 */
	public static synchronized ActiveMQBeanFactory getInstance() {
		if (instance == null) {
			instance = new ActiveMQBeanFactory();
		}
		return instance;
	}

	/**
	 * @param beanName
	 *            is the name of bean.
	 * @return the Object of the bean name.
	 */
	public Object getBean(final String beanName) {
		return context.getBean(beanName);
	}
}
