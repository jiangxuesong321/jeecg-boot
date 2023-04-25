package org.jeecg.modules.srm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.srm.entity.BasWarehouse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 仓库
 * @Author: jeecg-boot
 * @Date:   2022-06-16
 * @Version: V1.0
 */
public interface IBasWarehouseService extends IService<BasWarehouse> {
    /**
     * 仓库列表
     * @param page
     * @param basWarehouse
     * @return
     */
    IPage<BasWarehouse> queryPageList(Page<BasWarehouse> page, BasWarehouse basWarehouse);
}
