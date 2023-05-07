package com.example.java.test.junior.developer.security;

import java.io.IOException;
import java.util.Properties;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class ApplicationPropertySourceFactory extends DefaultPropertySourceFactory {

  @Override
  public PropertySource<?> createPropertySource(String name, EncodedResource resource)
      throws IOException {
    Resource propertiesResource = resource.getResource();
    if (propertiesResource.exists()) {
      Properties properties = PropertiesLoaderUtils.loadProperties(propertiesResource);
      return new PropertiesPropertySource(propertiesResource.getFilename(), properties);
    } else {
      return super.createPropertySource(name, resource);
    }
  }
}
