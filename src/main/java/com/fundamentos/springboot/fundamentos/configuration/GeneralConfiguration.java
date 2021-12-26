package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.Pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithPropertiesImplement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@EnableConfigurationProperties(UserPojo.class)
@PropertySource("classpath:connection.properties")
@Configuration
public class GeneralConfiguration {
    @Value("${value.name}")
    private String name;
    @Value("${value.apellido}")
    private String apellido;
    @Value("${value.random}")
    private String random;

    @Bean
    public MyBeanWithProperties function(){
        return new MyBeanWithPropertiesImplement(name,apellido);
    }

    @Value("${jdbc.url}")
    private String url;
    @Value("${driver}")
    private String driver;
    @Value("${password}")
    private String password;
    @Value("${username}")
    private String username;

    @Bean
    public DataSource dataSource(){
        DataSourceBuilder dataSourceBuilder=DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driver);
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }
}
