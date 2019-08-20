package com.chmpay.idauth.console.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chmpay.idauth.console.model.Role;
import com.chmpay.idauth.console.vo.FirstClsssMenu;
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
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 查询角色信息
     *
     * @param params
     * @return
     */
    Page<Role> queryRoleService(@Param("params") Map<String, Object> params);

    List<FirstClsssMenu> queryRoleResourceService(@Param("params") Map<String, Object> params);
}
