package com.linzx.admin.system.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.linzx.admin.system.domain.Dept;
import com.linzx.admin.system.mapper.DeptMapper;
import com.linzx.admin.system.service.IDeptService;
import com.linzx.core.common.exception.BizException;
import com.linzx.core.framework.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门 服务层实现
 * @author linzixiang
 * @date 2020-06-10 20:45:37
 */
@Service("deptService")
public class DeptService extends BaseService<Long, Dept, DeptMapper> implements IDeptService {

    private DeptMapper deptMapper;

    public DeptService(DeptMapper deptMapper) {
        this.deptMapper = deptMapper;
    }

    @Override
    protected DeptMapper getMapper() {
        return this.deptMapper;
    }

    @Override
    public List<Dept> buildDeptTree(List<Dept> deptList) {
        Dept rootTree = new Dept();
        rootTree.setId(0L);
        rootTree.setParentId(-1L);
        rootTree.setChildren(new ArrayList<>());
        setDeptChildren(rootTree, deptList);
        return rootTree.getChildren();
    }

    /**
     * 递归设置children
     */
    private void setDeptChildren(Dept rootTree, List<Dept> deptList) {
        for(Dept menu : deptList) {
            if (ObjectUtil.equal(menu.getParentId(), rootTree.getId())) {
                List<Dept> children = rootTree.getChildren();
                if (children == null) {
                    children = new ArrayList<>();
                    rootTree.setChildren(children);
                }
                children.add(menu);
            }
        }
        List<Dept> children = rootTree.getChildren();
        if (children != null && children.size() > 0) {
            for(Dept dept : children) {
                setDeptChildren(dept, deptList);
            }
        }
    }

    public void setDeptAncestors(Dept dept) {
        if (dept.getParentId() == null || dept.getParentId() < 0) {
            // parentId不合法
            return;
        }
        if (ObjectUtil.equal(dept.getParentId(), 0)) {
            // 顶层Dept
            dept.setAncestors(Convert.toStr(dept.getId()));
            return;
        }
        Dept parentDept = this.getById(dept.getParentId()).get();
        dept.setAncestors(parentDept.getAncestors() + "," + dept.getId());
    }

}
