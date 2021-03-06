package com.online.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.LongAdder;


@Slf4j
@Component
public class TestAdd implements ApplicationRunner {

    private final LongAdder longAdder = new LongAdder();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        while (true) {
            longAdder.add(1);
            try {
                Thread.sleep(2000);
                testException();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            log.info("返回值为： "+testReturn());
            test(longAdder);
        }
    }

    private void test(LongAdder longAdder) {
        log.info("Test------->" + longAdder);
    }
    private void testException() throws Exception{
        throw new Exception("报错啦");
    }

    private int testReturn() {
        return 0;
    }

}
