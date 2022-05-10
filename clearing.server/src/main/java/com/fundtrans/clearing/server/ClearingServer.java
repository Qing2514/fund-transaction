package com.fundtrans.clearing.server;

import com.hundsun.jrescloud.common.boot.CloudApplication;
import com.hundsun.jrescloud.common.boot.CloudBootstrap;
import com.hundsun.jrescloud.db.core.configuration.EnableCloudDataSource;

@CloudApplication
@EnableCloudDataSource
public class ClearingServer {
    public static void main(String[] args) {
        CloudBootstrap.run(ClearingServer.class,args);
    }
}
