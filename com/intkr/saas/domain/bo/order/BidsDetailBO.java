package com.intkr.saas.domain.bo.order;

import java.util.HashMap;
import java.util.Map;

import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.domain.bo.item.DemandBO;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.util.JsonUtil;

/**
 * 
 * @author Beiden
 * @date 2016-4-13 下午1:43:58
 * @version 1.0
 */
public class BidsDetailBO extends BaseBO {

	private Long id;

	private Long bidsId;

	private Long userId;

	private String title;

	private Long price;

	private String content;

	private String feature;

	private BidsBO bids;

	private UserBO user;

	private ItemBO bidsItem;

	private DemandBO bidsDemand;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBidsId() {
		return bidsId;
	}

	public void setBidsId(Long bidsId) {
		this.bidsId = bidsId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public UserBO getUser() {
		return user;
	}

	public void setUser(UserBO user) {
		this.user = user;
	}

	public ItemBO getBidsItem() {
		return bidsItem;
	}

	public void setBidsItem(ItemBO bidsItem) {
		this.bidsItem = bidsItem;
	}

	public DemandBO getBidsDemand() {
		return bidsDemand;
	}

	public void setBidsDemand(DemandBO bidsDemand) {
		this.bidsDemand = bidsDemand;
	}

	public Object getFeature(String key) {
		Map<String, Object> map = JsonUtil.parse(feature, Map.class);
		if (map == null) {
			return null;
		}
		return map.get(key);
	}

	public void setFeature(String key, Object value) {
		Map<String, Object> map = JsonUtil.parse(feature, Map.class);
		if (map == null) {
			map = new HashMap<String, Object>();
		}
		map.put(key, value);
		this.feature = JsonUtil.format(map);
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public BidsBO getBids() {
		return bids;
	}

	public void setBids(BidsBO bids) {
		this.bids = bids;
	}

}
