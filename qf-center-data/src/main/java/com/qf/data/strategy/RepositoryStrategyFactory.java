package com.qf.data.strategy;



import com.qf.common.constant.CommonConstant;
import com.qf.data.service.RepositoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 设备数据存储策略工厂
 *
 * @author 千锋健哥
 */
public class RepositoryStrategyFactory {
    private static final Map<String, RepositoryService> savingStrategyServiceMap = new ConcurrentHashMap<>();

    public static List<RepositoryService> getAll() {
        return new ArrayList<>(savingStrategyServiceMap.values());
    }


    public static RepositoryService get(String name) {
        return savingStrategyServiceMap.get(CommonConstant.RepositoryStrategy.REPOSITORY_STRATEGY + name);
    }

    public static void put(String name, RepositoryService service) {
        savingStrategyServiceMap.put(CommonConstant.RepositoryStrategy.REPOSITORY_STRATEGY + name, service);
    }
}
