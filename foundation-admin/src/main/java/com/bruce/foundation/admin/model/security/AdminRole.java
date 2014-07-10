package com.bruce.foundation.admin.model.security;

import java.util.Date;

public class AdminRole {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin_role.id
     *
     * @mbggenerated Thu Jul 10 18:39:11 CST 2014
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin_role.role_name
     *
     * @mbggenerated Thu Jul 10 18:39:11 CST 2014
     */
    private String roleName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin_role.status
     *
     * @mbggenerated Thu Jul 10 18:39:11 CST 2014
     */
    private Short status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin_role.create_time
     *
     * @mbggenerated Thu Jul 10 18:39:11 CST 2014
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin_role.update_time
     *
     * @mbggenerated Thu Jul 10 18:39:11 CST 2014
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin_role.id
     *
     * @return the value of admin_role.id
     *
     * @mbggenerated Thu Jul 10 18:39:11 CST 2014
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin_role.id
     *
     * @param id the value for admin_role.id
     *
     * @mbggenerated Thu Jul 10 18:39:11 CST 2014
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin_role.role_name
     *
     * @return the value of admin_role.role_name
     *
     * @mbggenerated Thu Jul 10 18:39:11 CST 2014
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin_role.role_name
     *
     * @param roleName the value for admin_role.role_name
     *
     * @mbggenerated Thu Jul 10 18:39:11 CST 2014
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin_role.status
     *
     * @return the value of admin_role.status
     *
     * @mbggenerated Thu Jul 10 18:39:11 CST 2014
     */
    public Short getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin_role.status
     *
     * @param status the value for admin_role.status
     *
     * @mbggenerated Thu Jul 10 18:39:11 CST 2014
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin_role.create_time
     *
     * @return the value of admin_role.create_time
     *
     * @mbggenerated Thu Jul 10 18:39:11 CST 2014
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin_role.create_time
     *
     * @param createTime the value for admin_role.create_time
     *
     * @mbggenerated Thu Jul 10 18:39:11 CST 2014
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin_role.update_time
     *
     * @return the value of admin_role.update_time
     *
     * @mbggenerated Thu Jul 10 18:39:11 CST 2014
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin_role.update_time
     *
     * @param updateTime the value for admin_role.update_time
     *
     * @mbggenerated Thu Jul 10 18:39:11 CST 2014
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}