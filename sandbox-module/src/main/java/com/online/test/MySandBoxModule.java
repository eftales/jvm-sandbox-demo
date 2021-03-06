package com.online.test;

import com.alibaba.jvm.sandbox.api.Information;
import com.alibaba.jvm.sandbox.api.Module;
import com.alibaba.jvm.sandbox.api.ProcessController;
import com.alibaba.jvm.sandbox.api.annotation.Command;
import com.alibaba.jvm.sandbox.api.listener.ext.Advice;
import com.alibaba.jvm.sandbox.api.listener.ext.AdviceListener;
import com.alibaba.jvm.sandbox.api.listener.ext.EventWatchBuilder;
import com.alibaba.jvm.sandbox.api.resource.ModuleEventWatcher;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.MetaInfServices;

import javax.annotation.Resource;
import java.util.Random;

@MetaInfServices(Module.class)
@Information(id = "my-sandbox-module")// 模块名,在指定挂载进程后通过-d指定模块,配合@Command注解来唯一确定方法
@Slf4j
public class MySandBoxModule implements Module {

    @Resource
    private ModuleEventWatcher moduleEventWatcher;

    @Command("addLog")// 模块命令名
    public void addLog() {
        new EventWatchBuilder(moduleEventWatcher)
                .onClass("com.online.test.TestAdd")// 想要对 TestAdd 这个类进行切面
                .onBehavior("test")// 想要对上面类的 test 方法进行切面
                .onWatch(new AdviceListener() {
                    //对方法执行之前执行
                    @Override
                    protected void before(Advice advice) throws Throwable {
                        log.info("sandbox切入成功!!!!!!");
                        //获取方法的所有参数
                        Object[] parameterArray = advice.getParameterArray();
                        if (parameterArray != null) {
                            for (Object po : parameterArray) {
                                log.info("形参类型为:" + po.getClass().getName() + "!!!!!!!");
                                log.info("形参值为:" + po + "!!!!!!!");
                            }
                        }
                    }
                });
    }

    @Command("removeException")// 模块命令名
    public void removeException() {
        new EventWatchBuilder(moduleEventWatcher)
                .onClass("com.online.test.TestAdd")// 想要对 TestAdd 这个类进行切面
                .onBehavior("testException")// 想要对上面类的 testException 方法进行切面
                .onWatch(new AdviceListener() {
                    //对方法执行之前执行
                    @Override
                    protected void afterThrowing(Advice advice) throws Throwable {
                        log.info("没收异常!!!");

                        // 在此，你可以通过ProcessController来改变原有方法的执行流程
                        // 这里的代码意义是：改变原方法抛出异常的行为，变更为立即返回；void返回值用null表示
                        ProcessController.returnImmediately(null);
                    }
                });
    }

    @Command("changeReturn")// 模块命令名
    public void changeReturn() {
        new EventWatchBuilder(moduleEventWatcher)
                .onClass("com.online.test.TestAdd")// 想要对 TestAdd 这个类进行切面
                .onBehavior("testReturn")// 想要对上面类的 testReturn 方法进行切面
                .onWatch(new AdviceListener() {
                    @Override
                    protected void afterReturning(Advice advice) throws Throwable {
                        log.info("在返回之前!!!");
                        ProcessController.returnImmediately(new Random().nextInt());
//                        super.afterReturning(advice);
                    }
                });
    }
}