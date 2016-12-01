package com.ymatou.alarmcenter.infrastructure.config;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import org.springframework.stereotype.Component;

@Component
@DisconfFile(
        fileName = "tomcat.properties"
)
public class TomcatConfig {
    private int port;
    private int connectionTimeout;
    private int maxConnections;
    private int maxThreads;
    private int acceptCount;
    private String uriEncoding;
    private String protocol;

    public TomcatConfig() {
    }

    @DisconfFileItem(
            name = "tomcat.port"
    )
    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @DisconfFileItem(
            name = "tomcat.connectionTimeout"
    )
    public int getConnectionTimeout() {
        return this.connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    @DisconfFileItem(
            name = "tomcat.maxConnections"
    )
    public int getMaxConnections() {
        return this.maxConnections;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    @DisconfFileItem(
            name = "tomcat.maxThreads"
    )
    public int getMaxThreads() {
        return this.maxThreads;
    }

    public void setMaxThreads(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    @DisconfFileItem(
            name = "tomcat.acceptCount"
    )
    public int getAcceptCount() {
        return this.acceptCount;
    }

    public void setAcceptCount(int acceptCount) {
        this.acceptCount = acceptCount;
    }

    @DisconfFileItem(
            name = "tomcat.uriEncoding"
    )
    public String getUriEncoding() {
        return this.uriEncoding;
    }

    public void setUriEncoding(String uriEncoding) {
        this.uriEncoding = uriEncoding;
    }

    @DisconfFileItem(
            name = "tomcat.protocol"
    )
    public String getProtocol() {
        return this.protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}