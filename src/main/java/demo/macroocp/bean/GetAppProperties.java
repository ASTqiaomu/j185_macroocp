package demo.macroocp.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetAppProperties {
    @Bean
    public String port(@Value("${server.port}") String port){
        return port;
    }
    @Bean
    public String path(@Value("${server.servlet.context-path}") String path){
        return path;
    }
}
