package org.jeecg.modules.srm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.srm.entity.BasWarehouse;
import org.jeecg.modules.srm.mapper.BasWarehouseMapper;
import org.jeecg.modules.srm.service.IBasWarehouseService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 仓库
 * @Author: jeecg-boot
 * @Date:   2022-06-16
 * @Version: V1.0
 */
@Service
public class BasWarehouseServiceImpl extends ServiceImpl<BasWarehouseMapper, BasWarehouse> implements IBasWarehouseService {
    /**
     * 仓库列表
     * @param page
     * @param basWarehouse
     * @return
     */
    @Override
    public IPage<BasWarehouse> queryPageList(Page<BasWarehouse> page, BasWarehouse basWarehouse) {
        return baseMapper.queryPageList(page,basWarehouse);
    }
}
