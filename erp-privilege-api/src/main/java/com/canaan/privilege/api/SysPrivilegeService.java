package com.canaan.privilege.api;

import java.util.List;

import com.canaan.common.BaseService;
import com.canaan.privilege.dto.PrivilegeDTO;

public interface SysPrivilegeService {

	List<PrivilegeDTO> listPrivilegesByUserId(Integer userId);
}
