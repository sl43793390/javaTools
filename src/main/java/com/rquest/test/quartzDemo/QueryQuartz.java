package com.rquest.test.quartzDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.rquest.test.util.Util;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Configurable
@EnableScheduling
public class QueryQuartz {

    private static final Logger logger = LoggerFactory.getLogger(QueryQuartz.class);

    /**
     * 每日刷新用户查询次数
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void printCurrentTimes(){
    	logger.info("now is flush the user query times map");
        Util.resetUserMap();
    }
}
