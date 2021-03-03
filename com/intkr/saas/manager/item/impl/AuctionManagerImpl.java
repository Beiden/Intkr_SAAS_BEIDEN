package com.intkr.saas.manager.item.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.item.AuctionDAO;
import com.intkr.saas.domain.bo.item.AuctionBO;
import com.intkr.saas.domain.bo.item.AuctionDetailBO;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.bo.order.OrderDetailBO;
import com.intkr.saas.domain.bo.order.PaticipateBO;
import com.intkr.saas.domain.bo.shop.ShopCommentBO;
import com.intkr.saas.domain.bo.shopping.ItemCollectBO;
import com.intkr.saas.domain.bo.sns.AttentionBO;
import com.intkr.saas.domain.bo.sns.CollectBO;
import com.intkr.saas.domain.bo.sns.LikeBO;
import com.intkr.saas.domain.bo.sns.MsgBO;
import com.intkr.saas.domain.bo.sns.PraiseDownBO;
import com.intkr.saas.domain.bo.sns.PraiseUpBO;
import com.intkr.saas.domain.dbo.item.AuctionDO;
import com.intkr.saas.domain.type.order.OrderDetailType;
import com.intkr.saas.domain.type.sns.AttentionType;
import com.intkr.saas.domain.type.sns.CollectType;
import com.intkr.saas.domain.type.sns.CommentType;
import com.intkr.saas.domain.type.sns.LikeType;
import com.intkr.saas.domain.type.sns.PaticipateType;
import com.intkr.saas.domain.type.sns.PraiseDownType;
import com.intkr.saas.domain.type.sns.PraiseUpType;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.item.AuctionManager;

/**
 * 
 * @author Beiden
 * @date 2016-5-18 上午10:32:19
 * @version 1.0
 */
@Repository("AuctionManager")
public class AuctionManagerImpl extends BaseManagerImpl<AuctionBO, AuctionDO> implements AuctionManager {

	@Resource
	private AuctionDAO auctionDAO;

	public BaseDAO<AuctionDO> getBaseDAO() {
		return auctionDAO;
	}

	public OrderDetailBO fill(OrderDetailBO orderDetail) {
		if (orderDetail == null) {
			return null;
		}
		if (!OrderDetailType.Auction.getCode().equals(orderDetail.getType())) {
			return orderDetail;
		}
		orderDetail.setProperty("auction", get(orderDetail.getRelatedId()));
		return orderDetail;
	}

	public OrderBO fill(OrderBO order) {
		if (order == null || order.getOrderDetails() == null) {
			return order;
		}
		for (OrderDetailBO detail : order.getOrderDetails()) {
			fill(detail);
		}
		return order;
	}

	public CollectBO fill(CollectBO collect) {
		if (collect == null) {
			return collect;
		}
		if (!CollectType.Auction.getCode().equals(collect.getType())) {
			return collect;
		}
		collect.setRelatedObject(get(collect.getRelatedId()));
		return collect;
	}

	public List<?> fill(List<?> list) {
		if (list == null) {
			return new ArrayList<MsgBO>();
		}
		for (Object obj : list) {
			if (obj instanceof CollectBO) {
				fill((CollectBO) obj);
			} else if (obj instanceof OrderBO) {
				fill((OrderBO) obj);
			} else if (obj instanceof OrderDetailBO) {
				fill((OrderDetailBO) obj);
			} else if (obj instanceof AuctionDetailBO) {
				fill((AuctionDetailBO) obj);
			} else if (obj instanceof PaticipateBO) {
				fill((PaticipateBO) obj);
			} else if (obj instanceof AttentionBO) {
				fill((AttentionBO) obj);
			}
		}
		return list;
	}

	public AttentionBO fill(AttentionBO attention) {
		if (attention == null) {
			return attention;
		}
		if (AttentionType.Auction.getCode().equals(attention.getType()) || AttentionType.Auction.getCode().equals(attention.getType()) || AttentionType.Bids.getCode().equals(attention.getType())) {
			attention.setRelatedObject(get(attention.getRelatedId()));
		}
		return attention;
	}

	public LikeBO fill(LikeBO like) {
		if (like == null) {
			return like;
		}
		if (!LikeType.Auction.getCode().equals(like.getType())) {
			return like;
		}
		like.setRelatedObject(get(like.getRelatedId()));
		return like;
	}

	public PraiseUpBO fill(PraiseUpBO praiseUp) {
		if (praiseUp == null) {
			return praiseUp;
		}
		if (!PraiseUpType.Auction.getCode().equals(praiseUp.getType())) {
			return praiseUp;
		}
		praiseUp.setRelatedObject(get(praiseUp.getRelatedId()));
		return praiseUp;
	}

	public PraiseDownBO fill(PraiseDownBO praiseDown) {
		if (praiseDown == null) {
			return praiseDown;
		}
		if (!PraiseDownType.Auction.getCode().equals(praiseDown.getType())) {
			return praiseDown;
		}
		praiseDown.setRelatedObject(get(praiseDown.getRelatedId()));
		return praiseDown;
	}

	public ShopCommentBO fill(ShopCommentBO comment) {
		if (comment == null) {
			return comment;
		}
		if (!CommentType.Auction.getCode().equals(comment.getType())) {
			return comment;
		}
		comment.setProperty("auction", get(comment.getRelatedId()));
		return comment;
	}

	public ItemBO fill(ItemBO item) {
		// if (item == null) {
		// return item;
		// }
		// if (!ItemTradeMethod.Auction.getCode().equals(item.getTradeMethod()))
		// {
		// return item;
		// }
		// item.setAuction(get(item.getTradeMethodId()));
		// return item;
		throw new RuntimeException("donot supported!");
	}

	public AuctionDetailBO fill(AuctionDetailBO auctionDetail) {
		if (auctionDetail == null) {
			return null;
		}
		auctionDetail.setAuction(get(auctionDetail.getAuctionId()));
		return auctionDetail;
	}

	public PaticipateBO fill(PaticipateBO paticipate) {
		if (paticipate == null) {
			return paticipate;
		}
		if (PaticipateType.Auction.getCode().equals(paticipate.getType()) || //
				PaticipateType.Auction.getCode().equals(paticipate.getType()) || //
				PaticipateType.AuctionHit.getCode().equals(paticipate.getType()) || //
				PaticipateType.BidsHit.getCode().equals(paticipate.getType()) || //
				PaticipateType.Bids.getCode().equals(paticipate.getType())) {
			paticipate.setRelatedObject(get(paticipate.getRelatedId()));
		}
		return paticipate;
	}

	public ItemCollectBO fill(ItemCollectBO collect) {
		throw new RuntimeException("donot supported!");
	}

}
