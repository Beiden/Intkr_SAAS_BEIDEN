package com.intkr.saas.domain.bo.item;

import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.domain.bo.user.UserBO;

/**
 * 
 * @author Beiden
 * @date 2016-4-13 下午1:43:58
 * @version 1.0
 */
public class AuctionDetailBO extends BaseBO {

	private Long id;

	private Long userId;

	private Long auctionId;

	private Long price;

	private String feature;

	private AuctionBO auction;

	private UserBO user;

	private ItemBO auctionItem;

	private DemandBO auctionDemand;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(Long auctionId) {
		this.auctionId = auctionId;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public UserBO getUser() {
		return user;
	}

	public void setUser(UserBO user) {
		this.user = user;
	}

	public ItemBO getAuctionItem() {
		return auctionItem;
	}

	public void setAuctionItem(ItemBO auctionItem) {
		this.auctionItem = auctionItem;
	}

	public DemandBO getAuctionDemand() {
		return auctionDemand;
	}

	public void setAuctionDemand(DemandBO auctionDemand) {
		this.auctionDemand = auctionDemand;
	}

	public AuctionBO getAuction() {
		return auction;
	}

	public void setAuction(AuctionBO auction) {
		this.auction = auction;
	}

}
