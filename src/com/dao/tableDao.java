package com.dao;

import java.util.List;

import com.model.Table;
/**
 * 桌台接口，该接口中定义了对桌台表操作的有关方法
 * @author ping
 *
 */
public interface tableDao {
	/**
	 * 通过桌台Id号查找桌台对象信息
	 * @param tableId 桌台Id号
	 * @return table 返回一个桌台对象信息
	 */
	//通过桌台号查询
	public Table findTableById(int tableId);
	/**
	 * 通过桌台的状态查找所有该状态的桌台信息
	 * @param state 桌台状态信息
	 * @return tableList 返回一个桌台列表
	 */
	//通过坐台的状态查询
	public List<Table> findTableByState(int state);
	/**
	 * 查找所有的桌台信息
	 * @return tableList 返回所有的桌台信息列表
	 */
	//查询所有桌台
	public List<Table> findAllTable();
	/**
	 * 通过大厅的Id号查找该大厅下所有的桌台信息
	 * @param roomId 大厅Id号
	 * @return tableList 返回该大厅下所有的桌台信息列表
	 */
	//通过房间号查询
	public List<Table> findTableByroomId(int roomId);
	/**
	 * 更新桌台,修改桌台相关的属性
	 * @param table 传递一个桌台对象
	 */
	//更新桌台信息
	public void update(Table table);
	/**
	 * 通过桌台Id号来修改该桌台的状态
	 * @param tableId 桌台Id号
	 * @param state 需要修改的桌台状态
	 */
	public void updateState(int tableId,int state);
	/**
	 * 删除所有的桌台信息
	 */
	public void deleteAllTable();

}
