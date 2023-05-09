package com.fund.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fund.entity.Prediction;
import com.fund.entity.Product;
import com.fund.mapper.PredictionMapper;
import com.fund.service.PredictionService;
import com.fund.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PredictionServiceImpl extends ServiceImpl<PredictionMapper, Prediction> implements PredictionService {

    @Autowired
    private PredictionMapper predictionMapper;

    @Autowired
    private ProductService productService;

    @Override
    public List<Prediction> findByProductId(String productId) {
        return predictionMapper.findByProductId(productId);
    }

    @Override
    public boolean addPrediction(Prediction prediction) {
        Product product = productService.findById(prediction.getProductId());
        if(product == null) {
            return false;
        }
        return predictionMapper.addPrediction(prediction);
    }

    @Override
    public boolean deletePrediction(String productId) {
        Product product = productService.findById(productId);
        if(product == null) {
            return false;
        }
        return predictionMapper.deletePrediction(productId);
    }

}
