package com.fundtrans.fundRedemption.client;

import com.hundsun.jrescloud.common.boot.CloudApplication;
import com.hundsun.jrescloud.common.boot.CloudBootstrap;

@CloudApplication
public class FundRedemptionClient {
    public static void main(String[] args) {
        CloudBootstrap.run(FundRedemptionClient.class,args);
    }
}
