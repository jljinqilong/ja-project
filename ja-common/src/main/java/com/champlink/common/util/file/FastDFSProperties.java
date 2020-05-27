package com.champlink.common.util.file;

import java.util.Properties;
import org.csource.fastdfs.ClientGlobal;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "fdfs")
public class FastDFSProperties {

    private String trackServer = "172.29.15.43:22122";
    private String connectTimeoutInSeconds = "10";
    private String networkTimeoutInSeconds = "30";
    private String charset = "UTF-8";
    private String httpTrackerHttpPort = "80";

    public Properties getProperty() {
        Properties props = new Properties();
        props.setProperty(ClientGlobal.PROP_KEY_TRACKER_SERVERS, trackServer);
        props.setProperty(ClientGlobal.PROP_KEY_CONNECT_TIMEOUT_IN_SECONDS, connectTimeoutInSeconds);
        props.setProperty(ClientGlobal.PROP_KEY_NETWORK_TIMEOUT_IN_SECONDS, networkTimeoutInSeconds);
        props.setProperty(ClientGlobal.PROP_KEY_CHARSET, charset);
        props.setProperty(ClientGlobal.PROP_KEY_HTTP_TRACKER_HTTP_PORT, httpTrackerHttpPort);
        return props;
    }

    public void setTrackServer(String trackServer) {
        this.trackServer = trackServer;
    }

    public void setConnectTimeoutInSeconds(String connectTimeoutInSeconds) {
        this.connectTimeoutInSeconds = connectTimeoutInSeconds;
    }

    public void setNetworkTimeoutInSeconds(String networkTimeoutInSeconds) {
        this.networkTimeoutInSeconds = networkTimeoutInSeconds;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public void setHttpTrackerHttpPort(String httpTrackerHttpPort) {
        this.httpTrackerHttpPort = httpTrackerHttpPort;
    }
}
