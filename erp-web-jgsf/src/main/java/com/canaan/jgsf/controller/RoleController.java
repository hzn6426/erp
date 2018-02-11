package com.canaan.jgsf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.canaan.authorization.api.SysRoleService;
import com.canaan.authorization.dto.MenuButtonTreeDTO;
import com.canaan.authorization.dto.RoleDTO;
import com.canaan.common.SearchResult;
import com.canaan.jgsf.common.MBaseController;
import com.canaan.jgsf.common.ResponseResult;
import com.canaan.jgsf.util.Assert;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "角色管理", description="角色的增/删/改/查/授权功能!")
@RequestMapping(value = "/role", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class RoleController extends MBaseController<RoleDTO> {
	
	@Resource
	private SysRoleService roleService;
	
	
	@ApiOperation(value = "查询列表")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseResult<RoleDTO> list(@RequestParam int page, @RequestParam int limit) {
		RoleDTO role = new RoleDTO();
		SearchResult<RoleDTO> roleResult = super.list(role, page, limit);
		return ResponseResult.build(roleResult.getTotalSize(), roleResult.getDataList());
	}
	@ApiOperation(value = "查询")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public RoleDTO get(@PathVariable Integer id) {
		return super.get(id);
	}
	
	@ApiOperation(value = "保存")
	@RequestMapping(method=RequestMethod.POST)
	public void save(@RequestBody RoleDTO role) {
		Assert.CheckArgument(role);
		super.save(role);
	}
	
	@ApiOperation(value = "更新")
	@RequestMapping(method=RequestMethod.PUT)
	public void update(@RequestBody RoleDTO role) {
		Assert.CheckArgument(role);
		super.update(role);
	}
	
	
	@ApiOperation(value = "删除")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Integer id) {
		Assert.CheckArgument(id);
		super.delete(new RoleDTO().setId(id));
	}
	
	@ApiOperation(value = "根据角色及权限树节点，加载该节点子节点权限树，根绝点时<code>treeNodeId</code>为-1")
	@RequestMapping(value = "/listLazyPermTree", method = RequestMethod.GET)
	public List<MenuButtonTreeDTO> listLazyPermTree(@RequestParam Integer roleId, @RequestParam String treeNodeId) {
		Assert.CheckArgument(roleId, treeNodeId);
		List<Integer> roleIdList = new ArrayList<>(1);
		roleIdList.add(roleId);
		return roleService.listLazyMenusOrButtonsByRoleIdList(roleIdList, treeNodeId);
	}
	
	@ApiOperation(value = "为对象将选中的权限树节点列表对应的菜单或按钮进行授权")
	@RequestMapping(value = "/saveRolePerms", method = RequestMethod.POST)
	public void saveRolePerms(@RequestParam Integer roleId, @RequestParam(required = false) List<String> treeNodeIds) {
		Assert.CheckArgument(roleId);
		roleService.saveRolePerms(treeNodeIds, roleId);
	}
	
	@ApiOperation(value = "批量删除")
	@RequestMapping(method = RequestMethod.DELETE)
	public void delete(@RequestParam List<Integer> ids) {
		Assert.CheckArgument(ids);
		super.deleteBatch(ids);
	}
}
