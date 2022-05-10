package com.fundtrans.infoSearch.server;

import com.hundsun.jrescloud.common.boot.CloudApplication;
import com.hundsun.jrescloud.common.boot.CloudBootstrap;
import com.hundsun.jrescloud.db.core.configuration.EnableCloudDataSource;

@CloudApplication
@EnableCloudDataSource
public class InfoSearchServer {
    public static void main(String[] args) {
        CloudBootstrap.run(InfoSearchServer.class,args);
    }
}
