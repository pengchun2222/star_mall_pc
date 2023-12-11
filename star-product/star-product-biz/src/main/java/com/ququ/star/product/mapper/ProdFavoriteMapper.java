package com.ququ.star.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ququ.star.product.api.entity.ProdFavorite;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 商品收藏表 Mapper 接口
 * </p>
 *
 * @author 彭淳
 * @since 2023-11-29
 */
@Mapper
public interface ProdFavoriteMapper extends BaseMapper<ProdFavorite> {

}
