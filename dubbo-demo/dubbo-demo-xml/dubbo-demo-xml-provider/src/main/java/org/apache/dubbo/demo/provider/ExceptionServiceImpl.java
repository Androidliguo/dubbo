package org.apache.dubbo.demo.provider;

import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.demo.ExceptionService;
import org.apache.dubbo.demo.exception.SelfException;
import org.apache.dubbo.rpc.RpcContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExceptionServiceImpl implements ExceptionService {

    @Override
    public String hello() {
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date())
                + "] Hello "
                + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        try {
            JSONObject.parse("aaa");
        }catch (Exception e){
            System.out.println("SelfException.....");
            throw new SelfException(e);
        }
        return "Hello "  + ", response from provider: " + RpcContext.getContext().getLocalAddress();
    }
}
