package com.bruce.foundation.admin.mapper.security;

import com.bruce.foundation.admin.model.security.AdminUserRole;
import com.bruce.foundation.admin.model.security.AdminUserRoleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminUserRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_user_role
     *
     * @mbggenerated Thu Jul 10 18:39:11 CST 2014
     */
    int countByExample(AdminUserRoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_user_role
     *
     * @mbggenerated Thu Jul 10 18:39:11 CST 2014
     */
    int deleteByExample(AdminUserRoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_user_role
     *
     * @mbggenerated Thu Jul 10 18:39:11 CST 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_user_role
     *
     * @mbggenerated Thu Jul 10 18:39:11 CST 2014
     */
    int insert(AdminUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_user_role
     *
     * @mbggenerated Thu Jul 10 18:39:11 CST 2014
     */
    int insertSelective(AdminUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_user_role
     *
     * @mbggenerated Thu Jul 10 18:39:11 CST 2014
     */
    List<AdminUserRole> selectByExample(AdminUserRoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_user_role
     *
     * @mbggenerated Thu Jul 10 18:39:11 CST 2014
     */
    AdminUserRole selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_user_role
     *
     * @mbggenerated Thu Jul 10 18:39:11 CST 2014
     */
    int updateByExampleSelective(@Param("record") AdminUserRole record, @Param("example") AdminUserRoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_user_role
     *
     * @mbggenerated Thu Jul 10 18:39:11 CST 2014
     */
    int updateByExample(@Param("record") AdminUserRole record, @Param("example") AdminUserRoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_user_role
     *
     * @mbggenerated Thu Jul 10 18:39:11 CST 2014
     */
    int updateByPrimaryKeySelective(AdminUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin_user_role
     *
     * @mbggenerated Thu Jul 10 18:39:11 CST 2014
     */
    int updateByPrimaryKey(AdminUserRole record);
}