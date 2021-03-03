package com.intkr.saas.manager.item;

import java.util.List;

import com.intkr.saas.domain.bo.item.AuctionDetailBO;
import com.intkr.saas.domain.bo.item.DemandBO;
import com.intkr.saas.domain.bo.order.BidsDetailBO;
import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.bo.order.OrderDetailBO;
import com.intkr.saas.domain.bo.order.PaticipateBO;
import com.intkr.saas.domain.bo.shop.ShopCommentBO;
import com.intkr.saas.domain.bo.shopping.ShoppingCartBO;
import com.intkr.saas.domain.bo.sns.AttentionBO;
import com.intkr.saas.domain.bo.sns.CollectBO;
import com.intkr.saas.domain.bo.sns.LikeBO;
import com.intkr.saas.domain.bo.sns.PraiseDownBO;
import com.intkr.saas.domain.bo.sns.PraiseUpBO;
import com.intkr.saas.domain.dbo.item.DemandDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2016-4-15 上午11:13:10
 * @version 1.0
 */
public interface DemandManager extends BaseManager<DemandBO, DemandDO> {

	public AttentionBO fill(AttentionBO attention);

	public PaticipateBO fill(PaticipateBO paticipate);

	public LikeBO fill(LikeBO like);

	public PraiseUpBO fill(PraiseUpBO praiseUp);

	public PraiseDownBO fill(PraiseDownBO praiseDown);

	public ShopCommentBO fill(ShopCommentBO comment);

	public OrderDetailBO fill(OrderDetailBO orderDetail);

	public OrderBO fill(OrderBO order);

	public CollectBO fill(CollectBO collect);

	public ShoppingCartBO fill(ShoppingCartBO shoppingCart);

	public AuctionDetailBO fill(AuctionDetailBO auctionDetail);

	public BidsDetailBO fill(BidsDetailBO bidsDetail);

	public List<?> fill(List<?> list);

}
