/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.demo.consumer;

import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.demo.DemoService;
import org.apache.dubbo.demo.ExceptionService;
import org.apache.dubbo.demo.GreetingService;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Application {
    /**
     * In order to make sure multicast registry works, need to specify '-Djava.net.preferIPv4Stack=true' before
     * launch the application
     */
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/dubbo-consumer.xml");
        context.start();
        DemoService demoService = context.getBean("demoService", DemoService.class);
        GreetingService greetingService = context.getBean("greetingService", GreetingService.class);
        ExceptionService exceptionService = context.getBean("exceptionService", ExceptionService.class);
        GenericService myGenericService = (GenericService) context.getBean("genericTestService");
        // 基本类型以及Date,List,Map等不需要转换，直接调用
        Object resultObj = myGenericService.$invoke("sayHello", new String[] {"java.lang.String"},
                new Object[] {"guoxi.li"});
        System.out.println("sayHello" + JSONObject.toJSONString(resultObj));


        new Thread(() -> {
            while (true) {
                exceptionService.hello();
                System.out.println(" exceptionService.");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                String greetings = greetingService.hello();
                System.out.println(greetings + " from separated thread.");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(100 * 1000L);

//        while (true) {
////            CompletableFuture<String> hello = demoService.sayHelloAsync("world");
////            System.out.println("result: " + hello.get());
////
////            String greetings = greetingService.hello();
////            System.out.println("result: " + greetings);
////
////            Thread.sleep(500);
////        }
    }
}
