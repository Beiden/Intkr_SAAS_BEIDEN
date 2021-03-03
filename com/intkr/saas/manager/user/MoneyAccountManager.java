package com.intkr.saas.manager.user;

import java.util.List;

import com.intkr.saas.domain.bo.user.MoneyAccountBO;
import com.intkr.saas.domain.bo.user.MoneyAccountFlowBO;
import com.intkr.saas.domain.dbo.user.MoneyAccountDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2017-10-02 17:59:27
 * @version 1.0
 */
public interface MoneyAccountManager extends BaseManager<MoneyAccountBO, MoneyAccountDO> {

	/**
	 * 注册后的初始化数据
	 */
	public void signUpInit(Long saasId, Long userId);

	public MoneyAccountBO get(Long saasId, String type, Long userId, String code);

	public MoneyAccountBO getOne(MoneyAccountBO account);

	public List<MoneyAccountBO> select(String type, Long userId);

	public MoneyAccountFlowBO fill(MoneyAccountFlowBO moneyFlow);

	public <T> List<T> fill(List<T> list);

}
