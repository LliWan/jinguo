package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠卷
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-08-22 14:44:10
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
