package com.dao;

import java.util.List;

import com.model.Waiter;
/**
 * 服务员接口，该接口中定义了对服务员信息进行操作的有关方法
 * @author ping
 *
 */
public interface waiterDao {
	/**
	 * 通过服务ID查找服务员对象
	 * @param writerId 服务Id号
	 * @return 返回一个服务员对象
	 */
	public Waiter findWriterById(int writerId);
	/**
	 * 查找服务员表中所有的服务员信息
	 * @return 返回服务员信息列表
	 */
	public List<Waiter> findAllWaiter();
	/**
	 * 通过服务Id号查找该服务员的密码
	 * @param waiterId 服务Id号
	 * @return 返回该服务Id号的密码
	 */
	public String checkPassword(int waiterId);

}
