package com.fund.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fund.entity.Prediction;

import java.util.List;

public interface PredictionService extends IService<Prediction> {

    List<Prediction> findByProductId(String productId);

    boolean addPrediction(Prediction prediction);

    boolean deletePrediction(String productId);

}
