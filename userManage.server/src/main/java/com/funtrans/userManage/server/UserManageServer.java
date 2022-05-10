package com.funtrans.userManage.server;

import com.hundsun.jrescloud.common.boot.CloudApplication;
import com.hundsun.jrescloud.common.boot.CloudBootstrap;
import com.hundsun.jrescloud.db.core.configuration.EnableCloudDataSource;

@CloudApplication
@EnableCloudDataSource
public class UserManageServer {
    public static void main(String[] args) {
        CloudBootstrap.run(UserManageServer.class,args);
    }
}
