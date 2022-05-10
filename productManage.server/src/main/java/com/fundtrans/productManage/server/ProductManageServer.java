package com.fundtrans.productManage.server;

import com.hundsun.jrescloud.common.boot.CloudApplication;
import com.hundsun.jrescloud.common.boot.CloudBootstrap;
import com.hundsun.jrescloud.db.core.configuration.EnableCloudDataSource;

@CloudApplication
@EnableCloudDataSource
public class ProductManageServer {
    public static void main(String[] args) {
        CloudBootstrap.run(ProductManageServer.class,args);
    }
}
