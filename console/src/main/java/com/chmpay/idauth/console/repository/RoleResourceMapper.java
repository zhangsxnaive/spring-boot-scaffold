package com.chmpay.idauth.console.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chmpay.idauth.console.model.RoleResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author zhangshuxin
 * @date 2019-06-12
 */
@Repository
public interface RoleResourceMapper extends BaseMapper<RoleResource> {

    void insertList(List<RoleResource> roleResourceList);

    List<RoleResource> findByRoleId(String id);

    List<RoleResource> findByResourceIdIn(Set<String> resourceids);

    void deleteByResourceIds(Set<String> resourceids);
}
