/*
 * @(#)WebServiceConfig.java $version 2016年5月18日
 *
 * Copyright 2016 LINE Corporation. All rights Reserved. 
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ufnet.ws.configuration;

import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.soap.SoapMessageFactory;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;

/**
 * Web Service Configuration.
 * @author cuizuoli
 * @date 2016年5月18日
 */
@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/*");
	}

	// 标准版接口配置
	// @Bean(name = "CardCharge")
	public DefaultWsdl11Definition defaultWsdl11Definition() {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("CardCharge");
		wsdl11Definition.setLocationUri("/services/CardCharge");
		wsdl11Definition.setSchema(new SimpleXsdSchema(new ClassPathResource("/xsd/CardCharge.xsd")));
		return wsdl11Definition;
	}

	@Bean(name = "CardCharge")
	public SimpleWsdl11Definition SimpleWsdl11Definition() {
		return new SimpleWsdl11Definition(new ClassPathResource("/wsdl/CardCharge.wsdl"));
	}

	@Bean
	public SaajSoapMessageFactory messageFactory() {
		return new SaajSoapMessageFactory();
	}

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan("com.ufnet.ws.server.model");
		return marshaller;
	}

	@Bean
	public WebServiceTemplate webServiceTemplate(SoapMessageFactory messageFactory, Jaxb2Marshaller marshaller) {
		WebServiceTemplate webServiceTemplate = new WebServiceTemplate(messageFactory);
		webServiceTemplate.setDefaultUri("http://124.67.20.216:8080/services/CardCharge");
		webServiceTemplate.setMarshaller(marshaller);
		webServiceTemplate.setUnmarshaller(marshaller);
		return webServiceTemplate;
	}

}
