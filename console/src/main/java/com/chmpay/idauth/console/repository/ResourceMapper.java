package com.chmpay.idauth.console.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chmpay.idauth.console.model.Resource;
import com.chmpay.idauth.console.vo.ResourceVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zhangshuxin
 * @date 2019-06-12
 */
@Repository
public interface ResourceMapper extends BaseMapper<Resource> {
    /**
     * 批量插入
     * @param resourceList 资源列表
     */
    void insertList(List<Resource> resourceList);


    List<Resource> selectListByRoleId(String roleId);

    List<Resource> findByMenuId(String id);

    Page<ResourceVo> queryResourceService(@Param("params") Map<String, Object> params);

    int update(Resource resource);
}
