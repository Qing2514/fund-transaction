package com.fundtrans.productManage.client;

import com.hundsun.jrescloud.common.boot.CloudApplication;
import com.hundsun.jrescloud.common.boot.CloudBootstrap;

@CloudApplication
public class ProductManageClient {
    public static void main(String[] args) {
        CloudBootstrap.run(ProductManageClient.class,args);
    }
}
