package com.fundtrans.infoSearch.server.serviceImpl;

import com.fundtrans.infoSearch.server.mapper.RecordMapper;
import com.fundtrans.infoSearch.service.RecordService;
import com.fundtrans.pojo.Record;
import com.fundtrans.vo.RespBean;
import com.fundtrans.vo.RespBeanEnum;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

@CloudComponent
public class RecordServiceImpl implements RecordService {

    private static final Log logger = LogFactory.getLog(RecordServiceImpl.class);

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public RespBean findByUserIdAndProductId(String userId, String productId) {
        logger.info("查询用户所有基金");
        Record record = null;
        try {
            record = recordMapper.findByUserIdAndProductId(userId, productId);
        }catch (Exception e){
            logger.error("查询用户所有基金失败" + e.getMessage());
            return RespBean.error(RespBeanEnum.RECORD_FIND_ERROR);
        }
        if (record == null){
            return RespBean.error(RespBeanEnum.RECORD_NOT_EXIST);
        }
        logger.info("查询用户所有基金结束");
        return RespBean.success(record);
    }

    @Override
    public void OutAddRecord(Record record) {
        recordMapper.addRecord(record);
    }

    @Override
    public void OutDeleteRecord(String id) {
        recordMapper.deleteRecord(id);
    }
}
