package org.apache.dubbo.demo.provider;


/**
 * GenericTestServiceImpl 实现 GenericTestService
 */
public class GenericTestServiceImpl implements GenericTestService {

    @Override
    public String sayHello(String method) {
        System.out.println("MyGenericService sayHello....");
        return "welcome";
    }
}
