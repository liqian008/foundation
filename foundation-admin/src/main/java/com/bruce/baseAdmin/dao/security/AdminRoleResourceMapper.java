package com.bruce.baseAdmin.dao.security;

import com.bruce.baseAdmin.model.security.AdminRoleResource;
import com.bruce.baseAdmin.model.security.AdminRoleResourceCriteria;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminRoleResourceMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table admin_role_resource
     * @mbggenerated  Wed Aug 07 10:02:03 CST 2013
     */
    int countByExample(AdminRoleResourceCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table admin_role_resource
     * @mbggenerated  Wed Aug 07 10:02:03 CST 2013
     */
    int deleteByExample(AdminRoleResourceCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table admin_role_resource
     * @mbggenerated  Wed Aug 07 10:02:03 CST 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table admin_role_resource
     * @mbggenerated  Wed Aug 07 10:02:03 CST 2013
     */
    int insert(AdminRoleResource record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table admin_role_resource
     * @mbggenerated  Wed Aug 07 10:02:03 CST 2013
     */
    int insertSelective(AdminRoleResource record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table admin_role_resource
     * @mbggenerated  Wed Aug 07 10:02:03 CST 2013
     */
    List<AdminRoleResource> selectByExample(AdminRoleResourceCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table admin_role_resource
     * @mbggenerated  Wed Aug 07 10:02:03 CST 2013
     */
    AdminRoleResource selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table admin_role_resource
     * @mbggenerated  Wed Aug 07 10:02:03 CST 2013
     */
    int updateByExampleSelective(@Param("record") AdminRoleResource record,
            @Param("example") AdminRoleResourceCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table admin_role_resource
     * @mbggenerated  Wed Aug 07 10:02:03 CST 2013
     */
    int updateByExample(@Param("record") AdminRoleResource record,
            @Param("example") AdminRoleResourceCriteria example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table admin_role_resource
     * @mbggenerated  Wed Aug 07 10:02:03 CST 2013
     */
    int updateByPrimaryKeySelective(AdminRoleResource record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table admin_role_resource
     * @mbggenerated  Wed Aug 07 10:02:03 CST 2013
     */
    int updateByPrimaryKey(AdminRoleResource record);
}