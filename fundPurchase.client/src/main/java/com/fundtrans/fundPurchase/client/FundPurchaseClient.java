package com.fundtrans.fundPurchase.client;

import com.hundsun.jrescloud.common.boot.CloudApplication;
import com.hundsun.jrescloud.common.boot.CloudBootstrap;

@CloudApplication
public class FundPurchaseClient {
    public static void main(String[] args) {
        CloudBootstrap.run(FundPurchaseClient.class,args);
    }
}
