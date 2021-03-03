package com.intkr.saas.manager.user.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.user.UserDAO;
import com.intkr.saas.domain.Transfer;
import com.intkr.saas.domain.bo.article.ArticleBO;
import com.intkr.saas.domain.bo.fs.MediaBO;
import com.intkr.saas.domain.bo.item.DemandBO;
import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.bo.order.OrderTimeLineBO;
import com.intkr.saas.domain.bo.page.PageBO;
import com.intkr.saas.domain.bo.shop.ShopActivityBO;
import com.intkr.saas.domain.bo.shop.ShopBO;
import com.intkr.saas.domain.bo.shop.ShopCommentBO;
import com.intkr.saas.domain.bo.sns.ContactBO;
import com.intkr.saas.domain.bo.sns.MsgBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.bo.user.MoneyOperBO;
import com.intkr.saas.domain.bo.user.RoleApplyBO;
import com.intkr.saas.domain.dbo.user.UserDO;
import com.intkr.saas.domain.type.user.UserStatus;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.user.MoneyAccountManager;
import com.intkr.saas.manager.user.RoleManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.manager.user.UserRoleManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * 
 * @author beidenhuang
 * @datetime 2011-10-9 上午11:44:14
 * @version 1.0
 */
@Repository("UserManager")
public class UserManagerImpl extends BaseManagerImpl<UserBO, UserDO> implements UserManager {

	@Resource
	private UserDAO accountDAO = null;
	
	@Resource
	private UserRoleManager authManager;

	@Resource
	private RoleManager roleManager;
	
	@Resource
	private MoneyAccountManager moneyAccountManager;

	public BaseDAO<UserDO> getBaseDAO() {
		return accountDAO;
	}

	public UserBO getByAccount(Long saasId, String account) {
		if (account == null || "".equals(account)) {
			return null;
		}
		UserBO query = new UserBO();
		query.setSaasId(saasId);
		query.setAccount(account);
		return selectOne(query);
	}

	public UserBO getByEmail(Long saasId, String email) {
		if (email == null || "".equals(email)) {
			return null;
		}
		UserBO query = new UserBO();
		query.setSaasId(saasId);
		query.setEmail(email);
		return selectOne(query);
	}

	public UserBO getByPhone(Long saasId, String phone) {
		if (phone == null || "".equals(phone)) {
			return null;
		}
		UserBO query = new UserBO();
		query.setSaasId(saasId);
		query.setPhone(phone);
		return selectOne(query);
	}

	public boolean isEmailCanUse(Long saasId, String email) {
		if (email == null || "".equals(email)) {
			return false;
		}
		UserBO query = new UserBO();
		query.setSaasId(saasId);
		query.setEmail(email);
		return count(query) == 0;
	}

	public boolean isPhoneCanUse(Long saasId, String phone) {
		if (phone == null || "".equals(phone) || phone.length() != 11) {
			return false;
		}
		UserBO query = new UserBO();
		query.setSaasId(saasId);
		query.setPhone(phone);
		return count(query) == 0;
	}

	public boolean isAccountCanUse(Long saasId, String account) {
		if (account == null || "".equals(account)) {
			return false;
		}
		UserBO query = new UserBO();
		query.setSaasId(saasId);
		query.setAccount(account);
		return count(query) == 0;
	}

	public ContactBO fill(ContactBO contact) {
		if (contact == null) {
			return null;
		}
		contact.setContact(get(contact.getContactId()));
		contact.setUser(get(contact.getUserId()));
		return contact;
	}

	public DemandBO fill(DemandBO contact) {
		if (contact == null) {
			return null;
		}
		contact.setUser(get(contact.getUserId()));
		return contact;
	}

	public MoneyOperBO fill(MoneyOperBO contact) {
		if (contact == null) {
			return null;
		}
		contact.setUser(get(contact.getUserId()));
		return contact;
	}

	public ShopActivityBO fill(ShopActivityBO contact) {
		if (contact == null) {
			return null;
		}
		contact.setUser(get(contact.getUserId()));
		return contact;
	}

	public MediaBO fill(MediaBO media) {
		if (media == null) {
			return null;
		}
		media.setUser(get(media.getUserId()));
		return media;
	}

	public ArticleBO fill(ArticleBO article) {
		if (article == null) {
			return null;
		}
		article.setUser(get(article.getUserId()));
		return article;
	}

	public PageBO fill(PageBO article) {
		if (article == null) {
			return null;
		}
		article.setUser(get(article.getUserId()));
		return article;
	}

	public ShopCommentBO fill(ShopCommentBO connemt) {
		if (connemt == null) {
			return null;
		}
		connemt.setUser(get(connemt.getUserId()));
		return connemt;
	}

	public OrderTimeLineBO fill(OrderTimeLineBO orderTimeLine) {
		if (orderTimeLine == null) {
			return null;
		}
		orderTimeLine.setUser(get(orderTimeLine.getUserId()));
		return orderTimeLine;
	}

	public <T> List<T> fill(List<T> list) {
		if (list == null) {
			return new ArrayList<T>();
		}
		for (T bo : list) {
			if (bo instanceof ContactBO) {
				fill((ContactBO) bo);
			} else if (bo instanceof PageBO) {
				fill((PageBO) bo);
			} else if (bo instanceof ArticleBO) {
				fill((ArticleBO) bo);
			} else if (bo instanceof ShopCommentBO) {
				fill((ShopCommentBO) bo);
			} else if (bo instanceof MediaBO) {
				fill((MediaBO) bo);
			} else if (bo instanceof RoleApplyBO) {
				fill((RoleApplyBO) bo);
			} else if (bo instanceof MsgBO) {
				fill((MsgBO) bo);
			} else if (bo instanceof OrderBO) {
				fill((OrderBO) bo);
			} else if (bo instanceof OrderTimeLineBO) {
				fill((OrderTimeLineBO) bo);
			} else if (bo instanceof ShopBO) {
				fill((ShopBO) bo);
			}
		}
		return list;
	}

	public MsgBO fill(MsgBO message) {
		if (message == null) {
			return message;
		}
		message.setFromUser(get(message.getFromUserId()));
		message.setToUser(get(message.getToUserId()));
		return message;
	}

	public RoleApplyBO fill(RoleApplyBO roleApply) {
		if (roleApply == null) {
			return null;
		}
		roleApply.setUser(get(roleApply.getUserId()));
		return roleApply;
	}

	public void updateMoney(UserBO user) {
		UserDO dto = Transfer.toDOByField(user);
		accountDAO.updateMoney(dto);
	}

	public void updateMoney(Long userId, Long money) {
		UserDO dto = new UserDO();
		dto.setId(userId);
		dto.setMoney(money);
		accountDAO.updateMoney(dto);
	}

	public List<UserBO> selectAll(Long saasId) {
		if (saasId == null || saasId <= 0l) {
			return new ArrayList<UserBO>();
		}
		UserBO query = new UserBO();
		query.setSaasId(saasId);
		query.set_pageSize(1000);
		return select(query);
	}

	public OrderBO fill(OrderBO order) {
		if (order == null) {
			return null;
		}
		order.setUser(get(order.getUserId()));
		return order;
	}

	public ShopBO fill(ShopBO shop) {
		if (shop == null) {
			return null;
		}
		shop.setUser(get(shop.getUserId()));
		return shop;
	}

	public boolean isSecurePasswordInit(Long userId) {
		UserBO user = get(userId);
		return user.getSecurePassword() != null && !"".equals(user.getSecurePassword());
	}
	
	public UserBO register(Long saasId, String phone, String password) {
		UserBO user = new UserBO();
		user.setSaasId(saasId);
		user.setId(getId());
		user.setAccount(phone);
		user.setNickName(phone);
		user.setPassword(password);
		user.encryptPassword();
		user.setPhone(phone);
		user.setAvatar("/asset/img/avatar/1001.png");
		user.setStatus(UserStatus.Normal.getCode());
		user.setMoney(0L);
		user.setHasSecureQuestion(2);
		user.setIdentityId(1l);
		user.setIsIdentity(1);
		insert(user);
		authManager.addRole(user.getId(), roleManager.getByCode("member").getId());
		authManager.addRole(user.getId(), roleManager.getByCode("merchant").getId());
		moneyAccountManager.signUpInit(saasId, user.getId());
		return user;
	}

}
