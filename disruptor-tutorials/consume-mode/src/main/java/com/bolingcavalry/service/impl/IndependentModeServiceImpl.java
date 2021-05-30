package com.bolingcavalry.service.impl;

import com.bolingcavalry.service.*;
import com.lmax.disruptor.dsl.Disruptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

/**
 * @author will (zq2599@gmail.com)
 * @version 1.0
 * @description: 方法实现
 * @date 2021/5/23 11:05
 */
@Service("independentModeService")
@Slf4j
public class IndependentModeServiceImpl extends ConsumeModeService {

    @Override
    protected void disruptorOperate() {
        // 调用handleEventsWith，表示创建的多个消费者，每个都是独立消费的
        // 这里创建两个消费者，一个是短信的，一个是邮件的
        disruptor.handleEventsWith(new SmsEventHandler(eventCountPrinter), new MailEventHandler(eventCountPrinter));
    }
}
