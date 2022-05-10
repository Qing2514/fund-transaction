package com.fundtrans.clearing.client;

import com.hundsun.jrescloud.common.boot.CloudApplication;
import com.hundsun.jrescloud.common.boot.CloudBootstrap;

@CloudApplication
public class ClearingClient {
    public static void main(String[] args) {
        CloudBootstrap.run(ClearingClient.class,args);
    }
}
