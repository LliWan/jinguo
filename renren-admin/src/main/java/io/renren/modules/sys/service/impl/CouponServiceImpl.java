package io.renren.modules.sys.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.CouponDao;
import io.renren.modules.sys.entity.CouponEntity;
import io.renren.modules.sys.service.CouponService;


@Service("couponService")
public class CouponServiceImpl extends ServiceImpl<CouponDao, CouponEntity> implements CouponService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
    	String couponSn =(String)params.get("name");//优惠卷名称
    	IPage<CouponEntity> page = null;
    	if(couponSn!=null&&!"".equals(couponSn)) {
    		page = this.page(
                    new Query<CouponEntity>().getPage(params),
                    new QueryWrapper<CouponEntity>()
                    .eq(StringUtils.isNotBlank(couponSn),"name",couponSn)
            );
    	}else {
    		page = this.page(
                    new Query<CouponEntity>().getPage(params),
                    new QueryWrapper<CouponEntity>()
            );
    	}
        return new PageUtils(page);
    }

}
