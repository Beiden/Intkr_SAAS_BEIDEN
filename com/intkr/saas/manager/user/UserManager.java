package com.intkr.saas.manager.user;

import java.util.List;

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
import com.intkr.saas.domain.bo.user.MoneyOperBO;
import com.intkr.saas.domain.bo.user.RoleApplyBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.dbo.user.UserDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * 
 * @author beidenhuang
 * @datetime 2011-10-24 下午03:37:11
 * @version 1.0
 */
public interface UserManager extends BaseManager<UserBO, UserDO> {

	public boolean isSecurePasswordInit(Long userId);

	public boolean isEmailCanUse(Long saasId, String email);

	public boolean isPhoneCanUse(Long saasId, String phone);

	public boolean isAccountCanUse(Long saasId, String account);

	public UserBO getByAccount(Long saasId, String account);

	public UserBO getByPhone(Long saasId, String phone);

	public UserBO getByEmail(Long saasId, String email);

	// sns
	public ContactBO fill(ContactBO contact);

	// account
	public MoneyOperBO fill(MoneyOperBO contact);

	public RoleApplyBO fill(RoleApplyBO roleApply);

	public void updateMoney(UserBO user);

	public void updateMoney(Long userId, Long money);

	// order
	public DemandBO fill(DemandBO demand);

	public OrderBO fill(OrderBO order);

	// shopping
	public ShopCommentBO fill(ShopCommentBO comment);

	public OrderTimeLineBO fill(OrderTimeLineBO orderTimeLine);

	// shop
	public ShopBO fill(ShopBO shop);

	public ShopActivityBO fill(ShopActivityBO activity);

	// fs
	public MediaBO fill(MediaBO media);

	public MsgBO fill(MsgBO message);

	// cms
	public ArticleBO fill(ArticleBO article);

	public PageBO fill(PageBO article);

	public <T> List<T> fill(List<T> list);

	public List<UserBO> selectAll(Long saasId);

	// 注册一个用户
	public UserBO register(Long saasId, String phone, String password);

}
