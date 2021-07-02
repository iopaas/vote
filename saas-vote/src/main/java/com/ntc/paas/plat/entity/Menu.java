package com.ntc.paas.plat.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author allen.yuan
 * @date 2021年6月26日 下午2:28:10
 */
public class Menu extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 8365216355151778031L;

	// 菜单ID(主键)
	private Long id;

	// 菜单名称
	private String name;

	// 菜单等级
	private Integer level;

	// 菜单父id
	private Long parent_id;

	// 菜单url
	private String url;

	// 菜单ico
	private String icon;

	// 菜单排序
	private Integer order;

	// 菜单状态
	private Integer status;

	// 子菜单
	private List<Menu> childs;

	public Long getId() {
		return id == null ? 0 : id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name == null ? "" : name.trim();
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level == null ? 0 : level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Long getParent_id() {
		return parent_id == null ? 0 : parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}

	public String getUrl() {
		return url == null ? "" : url.trim();
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon == null ? "" : icon.trim();
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getOrder() {
		return order == null ? 0 : order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getStatus() {
		return status == null ? 0 : status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Menu> getChilds() {
		if (childs == null) {
			childs = new ArrayList<Menu>();
		}
		return childs;
	}

	public void setChilds(List<Menu> childs) {
		this.childs = childs;
	}
}