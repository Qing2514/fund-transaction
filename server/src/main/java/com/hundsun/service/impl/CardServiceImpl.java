package com.hundsun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hundsun.entity.Card;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import com.hundsun.mapper.CardMapper;
import com.hundsun.service.ICardService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Qing2514
 * @date 2022-05-02
 */
@CloudComponent
public class CardServiceImpl extends ServiceImpl<CardMapper, Card> implements ICardService {

    private static final Log logger = LogFactory.getLog(CardServiceImpl.class);

    @Autowired
    private CardMapper cardMapper;

}
