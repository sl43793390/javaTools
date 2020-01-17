package com.rquest.test.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rquest.test.entity.User;

public class MyBatisBasicTest { 
  private static final Logger log = LoggerFactory.getLogger(MyBatisBasicTest.class); 
  private static SqlSessionFactory sqlSessionFactory; 
    
  private static Reader reader; 
    
  @BeforeClass
  public static void initial() { 
    try { 
      reader = Resources.getResourceAsReader("Configuration.xml"); 
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader); 
    } catch (IOException e) { 
      log.error("Error thrown while reading the configuration: {}", e); 
    } finally { 
      if (reader != null) { 
        try { 
          reader.close(); 
        } catch (IOException e) { 
          log.error("Error thrown while closing the reader: {}", e); 
        } 
      } 
    } 
  } 
    
  @Test
  public void queryTest() { 
    SqlSession session = sqlSessionFactory.openSession(); 
    User user = (User)session.selectOne("com.john.hbatis.model.UserMapper.getUserById", 1); 
    log.info("{}: {}", user.getUserId(), user.getAge()); 
  } 
} 