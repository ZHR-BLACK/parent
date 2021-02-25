package com.zhr.selfstudy.retry.guava.service;

import com.zhr.selfstudy.retry.guava.exception.NeedRetryException;
import com.zhr.selfstudy.retry.guava.model.Product;
import com.zhr.selfstudy.retry.guava.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 业务方法实现
 */
@Component
@Slf4j
public class ProductInformationHander implements Callable<Boolean> {

    @Autowired
    private ProductRepository pRepo;

    private static Map<Long, BigDecimal> prices = new HashMap<>();

    static {
        prices.put(1L, new BigDecimal(100));
        prices.put(2L, new BigDecimal(200));
        prices.put(3L, new BigDecimal(300));
        prices.put(4L, new BigDecimal(400));
        prices.put(8L, new BigDecimal(800));
        prices.put(9L, new BigDecimal(900));
    }

    @Override
    public Boolean call() throws Exception {
        log.info("sync price begin,prices size={}", prices.size());
        List<Product> all = pRepo.findAll();
        System.out.println("all = " + all);

        for (Long id : prices.keySet()) {
            Product product = pRepo.findById(id);
            if (null == product) {
                throw new NeedRetryException("can not find product by id=" + id);
            }
            if (null == product.getCount() || product.getCount() < 1) {
                throw new NeedRetryException("product count is less than 1, id=" + id);
            }
            Product updatedP = pRepo.updatePrice(id, prices.get(id));
            if (null == updatedP) {
                return false;
            }
            prices.remove(id);
        }
        log.info("sync price over,prices size={}", prices.size());
        return true;
    }

}
