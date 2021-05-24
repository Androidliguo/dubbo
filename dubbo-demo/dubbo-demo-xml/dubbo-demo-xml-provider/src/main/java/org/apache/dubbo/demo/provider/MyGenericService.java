package org.apache.dubbo.demo.provider;



public class MyGenericService implements GenericService {


    @Override
    public String sayHello(String method) {
        return "welcome";
    }
}
