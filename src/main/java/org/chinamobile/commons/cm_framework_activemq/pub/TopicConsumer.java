package org.chinamobile.commons.cm_framework_activemq.pub;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.transport.TransportListener;
/*
 * 
 * */
public class TopicConsumer {

	/**
	 * 连接工厂
	 */
	private ConnectionFactory connectionFactory;
	private ActiveMQConnection connection;
	private Session session;
	private Topic topic;

	private MessageConsumer consumer;

	private void init() throws JMSException {
		connection = (ActiveMQConnection) connectionFactory.createConnection();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	}

	private void init(TransportListener transportListener) throws JMSException {
		connection = (ActiveMQConnection) connectionFactory.createConnection();
		if (null != transportListener) {
			connection.addTransportListener(transportListener);
		}
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	}
	
	public void receiveMessage(String topicName, MessageListener messageListener)
			throws JMSException {
		init();
		topic = session.createTopic(topicName);
		consumer = session.createConsumer(topic);
		consumer.setMessageListener(messageListener);
		connection.start();
	}	

}
