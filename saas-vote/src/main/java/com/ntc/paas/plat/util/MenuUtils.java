package com.ntc.paas.plat.util;

import java.util.ArrayList;
import java.util.List;

import com.ntc.paas.plat.entity.Menu;

/**
 * 菜单助手类
 * 
 * @author allen.yuan
 * @date 2021年6月23日 上午10:06:40
 */
public class MenuUtils {

	private static final int one_level = 1; // 一级
	private static final int two_level = 2; // 二级
	private static final int three_level = 3; // 三级

	/**
	 * 菜单转换成树型列表
	 * 
	 * @param rootList
	 * 
	 * @return
	 */
	public static List<Menu> initTreeList(List<Menu> rootList) {

		// 定义结果
		List<Menu> menuList = new ArrayList<Menu>();
		if (rootList == null || rootList.size() == 0) {
			return menuList;
		}

		// 加载一级菜单
		for (Menu oneMenu : rootList) {
			if (one_level == oneMenu.getLevel()) {
				menuList.add(oneMenu);
			}
		}

		// 为一级菜单设置子菜单，getChild是递归调用的
		for (Menu menu : menuList) {
			menu.setChilds(getChild(two_level, menu.getId(), rootList));
		}

		return menuList;
	}

	/**
	 * 递归查找子菜单
	 * 
	 * @param id       当前菜单id
	 * @param rootList 要查找的列表
	 * @return
	 */
	private static List<Menu> getChild(int level, Long parent_id, List<Menu> rootList) {

		// 子菜单
		List<Menu> childList = new ArrayList<Menu>();
		for (Menu menu : rootList) {

			// 遍历所有节点，将父菜单id与传过来的id比较
			if (menu.getLevel() == level && menu.getParent_id().longValue() == parent_id.longValue()) {

				menu.setChilds(getChild(three_level, menu.getId(), rootList));
				childList.add(menu);
			}
		}

		return childList;
	}
}
