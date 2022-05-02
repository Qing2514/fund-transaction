package com.hundsun.controller;

import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import com.hundsun.service.IShareService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qing2514
 * @date 2022-05-02
 */
@RestController
@RequestMapping("/share")
public class ShareController {

    @CloudReference
    private IShareService shareService;

}
