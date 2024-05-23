package com.example.securitytest.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@TableName("sys_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {

    @Serial
    private static final long serialVersionUID = 2L;

    @TableId
    private Long id;
    /**
     * 菜單名
     */
    @TableField("menuName")
    private String menuName;
    /**
     * 路由地址
     */
    @TableField("path")
    private String path;
    /**
     * 組件路徑
     */
    @TableField("component")
    private String component;
    /**
     * 菜單狀態（0显示 1隱藏）
     */
    @TableField("visible")
    private String visible;
    /**
     * 菜單狀態（0正常 1停用）
     */
    @TableField("status")
    private String status;
    /**
     * 權限標識
     */
    @TableField("perms")
    private String perms;
    /**
     * 菜單圖樣
     */
    @TableField("icon")
    private String icon;

    @TableField("create_by")
    private Long createBy;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_by")
    private Long updateBy;

    @TableField("update_time")
    private Date updateTime;
    /**
     * 是否刪除（0未刪除 1已刪除）
     */
    @TableField("del_flag")
    private Integer delFlag;
    /**
     * 備註
     */
    @TableField("remark")
    private String remark;
}

