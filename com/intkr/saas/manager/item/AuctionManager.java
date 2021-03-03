package com.intkr.saas.manager.item;

import java.util.List;

import com.intkr.saas.domain.bo.item.AuctionBO;
import com.intkr.saas.domain.bo.item.AuctionDetailBO;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.bo.order.OrderDetailBO;
import com.intkr.saas.domain.bo.order.PaticipateBO;
import com.intkr.saas.domain.bo.shop.ShopCommentBO;
import com.intkr.saas.domain.bo.shopping.ItemCollectBO;
import com.intkr.saas.domain.bo.sns.AttentionBO;
import com.intkr.saas.domain.bo.sns.LikeBO;
import com.intkr.saas.domain.bo.sns.PraiseDownBO;
import com.intkr.saas.domain.bo.sns.PraiseUpBO;
import com.intkr.saas.domain.dbo.item.AuctionDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2016-5-18 上午10:31:35
 * @version 1.0
 */
public interface AuctionManager extends BaseManager<AuctionBO, AuctionDO> {

	public AttentionBO fill(AttentionBO attention);

	public PaticipateBO fill(PaticipateBO paticipate);

	public LikeBO fill(LikeBO like);

	public PraiseUpBO fill(PraiseUpBO praiseUp);

	public PraiseDownBO fill(PraiseDownBO praiseDown);

	public ShopCommentBO fill(ShopCommentBO comment);

	public ItemBO fill(ItemBO item);

	public OrderDetailBO fill(OrderDetailBO orderDetail);

	public OrderBO fill(OrderBO order);

	public ItemCollectBO fill(ItemCollectBO collect);

	public AuctionDetailBO fill(AuctionDetailBO auctionDetail);

	public List<?> fill(List<?> list);

}
