package com.ymatou.alarmcenter.api.web.config;

import com.alibaba.dubbo.remoting.http.servlet.BootstrapListener;
import com.alibaba.dubbo.remoting.http.servlet.DispatcherServlet;
import com.ymatou.alarmcenter.infrastructure.config.TomcatConfig;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.AbstractProtocol;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.Resource;
import javax.servlet.Servlet;
import javax.servlet.ServletContextListener;
import java.nio.charset.Charset;

/**
 * Created by zhangxiaoming on 2016/11/29.
 */
@Configuration
public class WebConfigInitializer extends SpringBootServletInitializer {

    @Resource
    private TomcatConfig tomcatConfig;

    @Bean
    public ServletContextListener bootstrapListener() {

        return new BootstrapListener();
    }

    @Bean
    public Servlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    @Bean
    @DependsOn("disconfMgrBean2")
    public EmbeddedServletContainerFactory createEmbeddedServletContainerFactory() {
        TomcatEmbeddedServletContainerFactory tomcatFactory = new TomcatEmbeddedServletContainerFactory();
        tomcatFactory.setPort(tomcatConfig.getPort());
        tomcatFactory.setProtocol(tomcatConfig.getProtocol());
        tomcatFactory.setUriEncoding(Charset.forName(tomcatConfig.getUriEncoding()));
        tomcatFactory.addConnectorCustomizers(new MyTomcatConnectorCustomizer(tomcatConfig));
        return tomcatFactory;
    }
}


class MyTomcatConnectorCustomizer implements TomcatConnectorCustomizer {

    private TomcatConfig tomcatConfig;

    public MyTomcatConnectorCustomizer(TomcatConfig tomcatConfig) {
        this.tomcatConfig = tomcatConfig;
    }

    public void customize(Connector connector) {
        AbstractProtocol<?> protocol = (AbstractProtocol<?>) connector.getProtocolHandler();

        // 最大排队数
        connector.setProperty("acceptCount", String.valueOf(tomcatConfig.getAcceptCount()));

        // 最大连接数
        protocol.setMaxConnections(tomcatConfig.getMaxConnections());

        // 最大线程数
        protocol.setMaxThreads(tomcatConfig.getMaxThreads());

        // 连接超时
        protocol.setConnectionTimeout(tomcatConfig.getConnectionTimeout());
    }
}


