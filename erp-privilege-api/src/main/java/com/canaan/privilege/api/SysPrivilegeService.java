package com.canaan.privilege.api;

import java.util.List;


public interface SysPrivilegeService {

	public List<String> listPermStringByUserId(Integer userId);
}
