package com.canaan.jgsf.shiro;

import java.util.HashSet;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.canaan.authorization.api.SysUserService;
import com.canaan.authorization.dto.UserDTO;
import com.canaan.authorization.state.UserState;
import com.canaan.jgsf.util.ShiroUtil;

import lombok.Setter;


public class UserRealm extends AuthorizingRealm {
	
	@Setter
	private SysUserService userService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	ShiroUser user = (ShiroUser)principals.getPrimaryPrincipal();
        String userName = user.getUserName();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(new HashSet<>(userService.listRoleNameByUserName(userName)));
        authorizationInfo.setStringPermissions(new HashSet<>(userService.listPermsByUserName(userName)));

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String)token.getPrincipal();

        UserDTO user = userService.getByUserName(username);

        if(user == null) {
            throw new UnknownAccountException();//没找到帐号
        }

        if(UserState.LOCK.equals(user.getState())) {
            throw new LockedAccountException(); //帐号锁定
        }

        ShiroUser shiroUser = ShiroUtil.wrapUser(user);
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
        		shiroUser, //用户名
                user.getUserPasswd(), //密码
//                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }
    

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}
