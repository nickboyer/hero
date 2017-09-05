package com.nickboyer.core.entry;

import java.util.ArrayList;
import java.util.List;

public class SysMenu {
	private Integer menuId;

	private String menuName;

	private String menuUrl;

	private Integer parentId;

	private String menuOrder;

	private String menuIcon;

	private String menuType;

	private Integer menuState;

	private List<SysMenu> menus = new ArrayList<>();

	public void addMenu(SysMenu menu) {

		menus.add(menu);
	}

	/**
	 * @return menus
	 */
	public List<SysMenu> getMenus() {
		return menus;
	}

	/**
	 * @param menus 要设置的 menus
	 */
	public void setMenus(List<SysMenu> menus) {
		this.menus = menus;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName == null ? null : menuName.trim();
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl == null ? null : menuUrl.trim();
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder == null ? null : menuOrder.trim();
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon == null ? null : menuIcon.trim();
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType == null ? null : menuType.trim();
	}

	public Integer getMenuState() {
		return menuState;
	}

	public void setMenuState(Integer menuState) {
		this.menuState = menuState;
	}
}