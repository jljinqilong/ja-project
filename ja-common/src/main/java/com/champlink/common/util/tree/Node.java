package com.champlink.common.util.tree;

import java.util.List;
/**
 * 节点实体类
 * @author Administrator
 *
 */
public class Node {

    /**
     * 编号
     */
    private String id;


    /**
     * 名称
     */
    private String name;


    /**
     * 下一个节点
     */
    private List<Node> next;

    
    private boolean isParent;
    

    public boolean isParent() {
    	
    	isParent = next==null? false:next.isEmpty()?false:true;
		return isParent;
	}


	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}


	public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public List<Node> getNext() {
        return next;
    }


    public void setNext(List<Node> next) {
        this.next = next;
    }

}
