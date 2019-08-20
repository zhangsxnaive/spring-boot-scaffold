package com.chmpay.idauth.console;

import com.chmpay.idauth.console.repository.RoleMapper;
import com.chmpay.idauth.console.util.PageParam;
import com.chmpay.idauth.console.vo.FirstClsssMenu;
import com.chmpay.idauth.console.vo.PageVo;
import com.chmpay.idauth.console.vo.UserVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsoleApplicationTests {

    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void testRoleMapper() {
        PageParam rolePageParam = new PageParam(new PageVo());

        Map<String, Object> map = new HashMap<>();
        map.put("roleId", "6");


        List<FirstClsssMenu> firstClsssMenus = roleMapper.queryRoleResourceService(map);
        firstClsssMenus.forEach(System.out::println);

    }

}
