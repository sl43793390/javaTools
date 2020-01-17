package com.rquest.readproperties;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class PropertyPlaceholder extends PropertyPlaceholderConfigurer {

    public static Map<String,String> dataSourceMap;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        dataSourceMap = new HashMap<String, String>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            dataSourceMap.put(keyStr, value);
        }
    }
    /**
     * 然后在配置文件中加以下配置，就可从map中获取key了；
     * 
     * 
     * 	  <bean id="propertyConfigurer" class="com.rquest.riskmaster.config.PropertyPlaceholder">
        <property name="location">
            <value>classpath:institutionMapping.properties</value>
        </property>
    </bean>
     */
    
    //static method for accessing context properties
/*    public static Object getProperty(String name) {
        return propertyMap.get(name);
    }*/
}