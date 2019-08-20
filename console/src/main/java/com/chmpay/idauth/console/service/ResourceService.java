package com.chmpay.idauth.console.service;

import com.chmpay.idauth.console.model.Resource;
import com.chmpay.idauth.console.vo.PageVo;
import com.chmpay.idauth.console.vo.ResourceVo;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;


public interface ResourceService {
				   
	
	Resource findOne(String id);
	
	Resource save(Resource resource);
	
	int saveAndFlush(Resource resource);

	List<Resource> findByRoleId(String roleId);
	
	
	void delete(String id);
	
	
	List<Resource> findByMenuId(String menuId);

    Page<ResourceVo> queryResourceService(Map<String, Object> params, PageVo pageVo);


	

 
}
