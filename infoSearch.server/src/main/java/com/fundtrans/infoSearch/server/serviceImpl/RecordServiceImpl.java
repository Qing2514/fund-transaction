package com.fundtrans.infoSearch.server.serviceImpl;

import com.fundtrans.infoSearch.server.mapper.RecordMapper;
import com.fundtrans.infoSearch.service.RecordService;
import com.fundtrans.pojo.Record;
import com.fundtrans.vo.RespBean;
import com.fundtrans.vo.TransSelectVo;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@CloudComponent
public class RecordServiceImpl implements RecordService {

    private static final Log logger = LogFactory.getLog(RecordServiceImpl.class);

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public void OutAddRecord(Record record) {
        recordMapper.addRecord(record);
    }

    @Override
    public void OutDeleteRecord(String id) {
        recordMapper.deleteRecord(id);
    }

    @Override
    public RespBean getSum() {
        return RespBean.success(recordMapper.getSum());
    }

    @Override
    public RespBean findByUserId(String user_id) {
        return RespBean.success(recordMapper.findRecordByUserId(user_id));
    }

    @Override
    public RespBean findByDate(Date date1, Date date2) {
        return RespBean.success(recordMapper.findRecordByDate(date1,date2));
    }

    @Override
    public RespBean findByDateAndUserId(Date date1, Date date2, String id) {
        return RespBean.success(recordMapper.findRecordByDateAndUserId(date1,date2,id));
    }

    @Override
    public RespBean findByAll(TransSelectVo transSelectVo) {
        return RespBean.success(recordMapper.findByAll(transSelectVo));
    }


}
