package org.jeecg.modules.srm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.srm.entity.ProjectBomChild;
import org.jeecg.modules.srm.entity.ProjectCategory;
import org.jeecg.modules.srm.entity.PurchaseRequestDetail;
import org.jeecg.modules.srm.mapper.PurchaseRequestDetailMapper;
import org.jeecg.modules.srm.service.IPurchaseRequestDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: purchase_request_detail
 * @Author: jeecg-boot
 * @Date:   2022-06-17
 * @Version: V1.0
 */
@Service
public class PurchaseRequestDetailServiceImpl extends ServiceImpl<PurchaseRequestDetailMapper, PurchaseRequestDetail> implements IPurchaseRequestDetailService {
	
	@Autowired
	private PurchaseRequestDetailMapper purchaseRequestDetailMapper;
	
	@Override
	public List<PurchaseRequestDetail> selectByMainId(String mainId) {
		return purchaseRequestDetailMapper.selectByMainId(mainId);
	}

	/**
	 * 执行预算
	 * @param bomChild
	 * @return
	 */
	@Override
	public List<PurchaseRequestDetail> fetchAmountByModel(ProjectBomChild bomChild) {
		return baseMapper.fetchAmountByModel(bomChild);
	}

	/**
	 * 执行预算
	 * @param category
	 * @return
	 */
	@Override
	public List<PurchaseRequestDetail> fetchAmountByCategory(ProjectCategory category) {
		return baseMapper.fetchAmountByCategory(category);
	}

	/**
	 * 需求明细
	 * @param id
	 * @return
	 */
	@Override
	public List<PurchaseRequestDetail> queryPurchaseRequestDetail(String id) {
		return baseMapper.queryPurchaseRequestDetail(id);
	}

    @Override
    public PurchaseRequestDetail countInfo(String id) {
        return baseMapper.countInfo(id);
    }
}
