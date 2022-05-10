package com.fundtrans.infoSearch.client;

import com.hundsun.jrescloud.common.boot.CloudApplication;
import com.hundsun.jrescloud.common.boot.CloudBootstrap;

@CloudApplication
public class InfoSearchClient {
    public static void main(String[] args) {
        CloudBootstrap.run(InfoSearchClient.class,args);
    }
}
