package com.ruoyi.guagua.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户对象 user
 * 
 * @author lm
 * @date 2025-06-07
 */

public class User extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long id;

    /** 用户名 */
    @Excel(name = "用户名")
    private String username;

    /** 加密后的密码 */
    private String password;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickname;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 头像URL */
    @Excel(name = "头像URL")
    private String avatar;

    /** 性别（0-未知，1-男，2-女） */
    @Excel(name = "性别", readConverterExp = "0=-未知，1-男，2-女")
    private Integer gender;

    /** 状态（1-正常，0-禁用） */
    @Excel(name = "状态", readConverterExp = "1=-正常，0-禁用")
    private Integer status;

    /** 会员类型（0-普通会员，1-银牌会员，2-金牌会员，3-钻石会员） */
    @Excel(name = "会员类型", readConverterExp = "0=-普通会员，1-银牌会员，2-金牌会员，3-钻石会员")
    private Integer memberType;

    /** 是否逻辑删除（0-否，1-是） */
    @Excel(name = "是否逻辑删除", readConverterExp = "0=-否，1-是")
    private Integer isDeleted;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setNickname(String nickname) 
    {
        this.nickname = nickname;
    }

    public String getNickname() 
    {
        return nickname;
    }

    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }

    public void setGender(Integer gender) 
    {
        this.gender = gender;
    }

    public Integer getGender() 
    {
        return gender;
    }

    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    public void setMemberType(Integer memberType) 
    {
        this.memberType = memberType;
    }

    public Integer getMemberType() 
    {
        return memberType;
    }

    public void setIsDeleted(Integer isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() 
    {
        return isDeleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("username", getUsername())
            .append("password", getPassword())
            .append("nickname", getNickname())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("avatar", getAvatar())
            .append("gender", getGender())
            .append("status", getStatus())
            .append("memberType", getMemberType())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
