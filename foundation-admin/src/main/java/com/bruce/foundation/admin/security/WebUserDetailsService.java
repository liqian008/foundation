package com.bruce.foundation.admin.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bruce.foundation.admin.model.security.AdminRole;
import com.bruce.foundation.admin.model.security.AdminUser;
import com.bruce.foundation.admin.service.security.AdminRoleService;
import com.bruce.foundation.admin.service.security.AdminUserService;
import com.bruce.foundation.admin.utils.ConstantsUtil;

/**
 * 实现 UserDetailsService 接口，主要是在 loadUserByUsername 方法中验证一个用户
 * 这里需要从数据库中读取验证表单提交过来的用户
 *
 */
@Service 
public class WebUserDetailsService implements UserDetailsService {
	
	private static Logger logger = LoggerFactory.getLogger(WebUserDetailsService.class);
	
	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	
	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private AdminRoleService adminRoleService;
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//该方法负责实现验证并授权
		
		AdminUser adminUser = adminUserService.loadUserByUsername(username);
		
		if (null == adminUser) {
			throw new UsernameNotFoundException(
							messages.getMessage("User.notFound", new Object[] { username }, "Username {0} not found"));
		}
		
		int userId = adminUser.getId();
		String password = adminUser.getPassword();
		boolean userEnabled = adminUser.getStatus() == 1;
		
		//读取当前用户有哪些角色权限
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		Set<AdminRole> userRoles = userEntity.getRoles();
		List<AdminRole> userRoles = adminRoleService.getRolesByUserId(userId);
		
		if(userRoles!=null&&userRoles.size()>0){
			for (AdminRole userRole : userRoles) {
				//这里的 role 参数为自己定义的，要和 SecurityMetadataSource 中的 SecurityConfig 参数对应
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(ConstantsUtil.SECURITY_AUTHORITY_PREFIX + userRole.getId());
				authorities.add(authority);
			} 
		}
		
		//如果是超级用户，则添加超级用户的授权
		if(username.equals("admin")){//这里是把超级用户名写死的，也可以把它实现可配置化
			//ROLE_SUPER 这个权限名字也是自己定义的
			authorities.add(new SimpleGrantedAuthority(ConstantsUtil.SECURITY_AUTHORITY_PREFIX + "SUPER"));
		}
		
		//创建 UserDetails 对象
		WebUserDetails webUserDetails = new WebUserDetails(userId,username, password, userEnabled, authorities);
		return webUserDetails;
		
	}
}
