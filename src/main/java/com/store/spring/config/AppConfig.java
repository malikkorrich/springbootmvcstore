package com.store.spring.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.store.spring.upload.StorageProperties;
/**
 * 
 * @author Malik Korrich
 * 
 *
 */

@Configuration
@EnableJpaAuditing
@EnableConfigurationProperties(StorageProperties.class)
public class AppConfig {

}
