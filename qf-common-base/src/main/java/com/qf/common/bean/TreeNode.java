package com.qf.common.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 千锋健哥
 */
@Data
public class TreeNode implements Serializable {
    private static final long serialVersionUID = 1L;

    protected int id;
    protected int parentId;
    protected List<TreeNode> children = new ArrayList<>(16);

    public void add(TreeNode node) {
        children.add(node);
    }
}
