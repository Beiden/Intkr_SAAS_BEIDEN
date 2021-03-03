package com.intkr.saas.manager.item.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.item.DemandDAO;
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
import com.intkr.saas.domain.bo.sns.MsgBO;
import com.intkr.saas.domain.bo.sns.PraiseDownBO;
import com.intkr.saas.domain.bo.sns.PraiseUpBO;
import com.intkr.saas.domain.dbo.item.DemandDO;
import com.intkr.saas.domain.type.order.OrderDetailType;
import com.intkr.saas.domain.type.shoping.ShoppingCartType;
import com.intkr.saas.domain.type.sns.AttentionType;
import com.intkr.saas.domain.type.sns.CollectType;
import com.intkr.saas.domain.type.sns.CommentType;
import com.intkr.saas.domain.type.sns.LikeType;
import com.intkr.saas.domain.type.sns.PaticipateType;
import com.intkr.saas.domain.type.sns.PraiseDownType;
import com.intkr.saas.domain.type.sns.PraiseUpType;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.item.DemandManager;

/**
 * 
 * @author Beiden
 * @date 2016-4-15 上午11:13:41
 * @version 1.0
 */
@Repository("DemandManager")
public class DemandManagerImpl extends BaseManagerImpl<DemandBO, DemandDO> implements DemandManager {

	@Resource
	private DemandDAO itemDAO;

	public BaseDAO<DemandDO> getBaseDAO() {
		return itemDAO;
	}

	public OrderDetailBO fill(OrderDetailBO orderDetail) {
		if (orderDetail == null) {
			return null;
		}
		if (!OrderDetailType.Demand.getCode().equals(orderDetail.getType())) {
			return orderDetail;
		}
		orderDetail.setProperty("demand", get(orderDetail.getRelatedId()));
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

	public ShoppingCartBO fill(ShoppingCartBO shoppingCart) {
		if (shoppingCart == null) {
			return null;
		}
		if (!ShoppingCartType.Demand.getCode().equals(shoppingCart.getType())) {
			return shoppingCart;
		}
		shoppingCart.setRelatedObject(get(shoppingCart.getRelatedId()));
		return shoppingCart;
	}

	public CollectBO fill(CollectBO collect) {
		if (collect == null) {
			return collect;
		}
		if (!CollectType.Demand.getCode().equals(collect.getType())) {
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
			} else if (obj instanceof ShoppingCartBO) {
				fill((ShoppingCartBO) obj);
			} else if (obj instanceof OrderDetailBO) {
				fill((OrderDetailBO) obj);
			} else if (obj instanceof AuctionDetailBO) {
				fill((AuctionDetailBO) obj);
			} else if (obj instanceof BidsDetailBO) {
				fill((BidsDetailBO) obj);
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
		if (AttentionType.Demand.getCode().equals(attention.getType()) || AttentionType.Auction.getCode().equals(attention.getType()) || AttentionType.Bids.getCode().equals(attention.getType())) {
			attention.setRelatedObject(get(attention.getRelatedId()));
		}
		return attention;
	}

	public LikeBO fill(LikeBO like) {
		if (like == null) {
			return like;
		}
		if (!LikeType.Demand.getCode().equals(like.getType())) {
			return like;
		}
		like.setRelatedObject(get(like.getRelatedId()));
		return like;
	}

	public PraiseUpBO fill(PraiseUpBO praiseUp) {
		if (praiseUp == null) {
			return praiseUp;
		}
		if (!PraiseUpType.Demand.getCode().equals(praiseUp.getType())) {
			return praiseUp;
		}
		praiseUp.setRelatedObject(get(praiseUp.getRelatedId()));
		return praiseUp;
	}

	public PraiseDownBO fill(PraiseDownBO praiseDown) {
		if (praiseDown == null) {
			return praiseDown;
		}
		if (!PraiseDownType.Demand.getCode().equals(praiseDown.getType())) {
			return praiseDown;
		}
		praiseDown.setRelatedObject(get(praiseDown.getRelatedId()));
		return praiseDown;
	}

	public ShopCommentBO fill(ShopCommentBO comment) {
		if (comment == null) {
			return comment;
		}
		if (!CommentType.Demand.getCode().equals(comment.getType())) {
			return comment;
		}
		comment.setProperty("demand", get(comment.getRelatedId()));
		return comment;
	}

	public AuctionDetailBO fill(AuctionDetailBO auctionDetail) {
		if (auctionDetail == null) {
			return null;
		}
		auctionDetail.setAuctionDemand(get(auctionDetail.getAuctionId()));
		return auctionDetail;
	}

	public BidsDetailBO fill(BidsDetailBO bidsDetail) {
		if (bidsDetail == null) {
			return null;
		}
		bidsDetail.setBidsDemand(get(bidsDetail.getBidsId()));
		return bidsDetail;
	}

	public PaticipateBO fill(PaticipateBO paticipate) {
		if (paticipate == null) {
			return paticipate;
		}
		if (PaticipateType.Demand.getCode().equals(paticipate.getType()) || //
				PaticipateType.Auction.getCode().equals(paticipate.getType()) || //
				PaticipateType.AuctionHit.getCode().equals(paticipate.getType()) || //
				PaticipateType.BidsHit.getCode().equals(paticipate.getType()) || //
				PaticipateType.Bids.getCode().equals(paticipate.getType())) {
			paticipate.setRelatedObject(get(paticipate.getRelatedId()));
		}
		return paticipate;
	}

}
