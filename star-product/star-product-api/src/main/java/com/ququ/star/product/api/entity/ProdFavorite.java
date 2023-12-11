package com.ququ.star.product.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 商品收藏表
 * </p>
 *
 * @author 彭淳
 * @since 2023-11-29
 */
@Data
public class ProdFavorite implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品ID
     */
    @TableField("prod_id")
    private Long prodId;

    /**
     * 收藏时间
     */
    @TableField("rec_time")
    private LocalDateTime recTime;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private String userId;

    @TableLogic
    private Integer isDelete;


    @Override
    public String toString() {
        return "TzProdFavorite{" +
        "id = " + id +
        ", prodId = " + prodId +
        ", recTime = " + recTime +
        ", userId = " + userId +
        "}";
    }
}
