package org.apache.dubbo.demo.provider;

import org.apache.dubbo.rpc.service.GenericException;

/**
 * Generic service interface
 *
 * @export
 */
public interface GenericService {

    /**
     * Generic invocation
     * @return invocation return value
     * @throws GenericException potential exception thrown from the invocation
     */
    String sayHello(String method);
}
