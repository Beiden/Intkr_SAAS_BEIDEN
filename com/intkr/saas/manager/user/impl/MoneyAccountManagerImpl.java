package com.intkr.saas.manager.user.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.user.MoneyAccountDAO;
import com.intkr.saas.domain.bo.user.MoneyAccountBO;
import com.intkr.saas.domain.bo.user.MoneyAccountFlowBO;
import com.intkr.saas.domain.dbo.user.MoneyAccountDO;
import com.intkr.saas.domain.type.user.MoneyAccount;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.user.MoneyAccountManager;

/**
 * 
 * @author Beiden
 * @date 2017-10-02 17:59:27
 * @version 1.0
 */
@Repository("MoneyAccountManager")
public class MoneyAccountManagerImpl extends BaseManagerImpl<MoneyAccountBO, MoneyAccountDO> implements MoneyAccountManager {

	@Resource
	private MoneyAccountDAO shopMoneyAccountDAO;

	public BaseDAO<MoneyAccountDO> getBaseDAO() {
		return shopMoneyAccountDAO;
	}

	public MoneyAccountBO get(Long saasId, String type, Long userId, String code) {
		MoneyAccountBO query = new MoneyAccountBO();
		query.setSaasId(saasId);
		query.setAccountType(type);
		query.setUserId(userId);
		query.setCode(code);
		MoneyAccountBO returnData = selectOne(query);
		if (returnData != null) {
			return returnData;
		}
		returnData = new MoneyAccountBO();
		returnData.setId(getId());
		returnData.setSaasId(saasId);
		returnData.setAccountType(type);
		returnData.setUserId(userId);
		returnData.setCode(code);
		returnData.setName(MoneyAccount.getByCode(code).getName());
		insert(returnData);
		return selectOne(query);
	}

	public MoneyAccountBO getOne(MoneyAccountBO query) {
		MoneyAccountBO returnData = selectOne(query);
		if (returnData != null) {
			return returnData;
		}
		query.setId(getId());
		query.setName(MoneyAccount.getByCode(query.getCode()).getName());
		insert(query);
		return query;
	}

	public List<MoneyAccountBO> select(String type, Long userId) {
		MoneyAccountBO query = new MoneyAccountBO();
		query.set_pageSize(1000);
		query.setAccountType(type);
		query.setUserId(userId);
		return select(query);
	}

	public MoneyAccountFlowBO fill(MoneyAccountFlowBO moneyFlow) {
		if (moneyFlow == null) {
			return null;
		}
		moneyFlow.setAccount(get(moneyFlow.getAccountId()));
		moneyFlow.setToAccount(get(moneyFlow.getToAccountId()));
		return moneyFlow;
	}

	public <T> List<T> fill(List<T> list) {
		if (list == null) {
			return new ArrayList<T>();
		}
		for (T bo : list) {
			if (bo instanceof MoneyAccountFlowBO) {
				fill((MoneyAccountFlowBO) bo);
			}
		}
		return list;
	}

	public void signUpInit(Long saasId, Long userId) {
		MoneyAccountBO account = new MoneyAccountBO();
		account.setId(getId());
		account.setSaasId(saasId);
		account.setAccountType("account");
		account.setUserId(userId);
		account.setCode("yuepay");
		account.setName("余额");
		insert(account);
	}

}
