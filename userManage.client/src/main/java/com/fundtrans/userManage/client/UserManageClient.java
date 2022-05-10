package com.fundtrans.userManage.client;

import com.hundsun.jrescloud.common.boot.CloudApplication;
import com.hundsun.jrescloud.common.boot.CloudBootstrap;

@CloudApplication
public class UserManageClient {
    public static void main(String[] args) {
        CloudBootstrap.run(UserManageClient.class,args);
    }
}
