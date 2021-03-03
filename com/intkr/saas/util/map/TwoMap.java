package com.intkr.saas.util.map;

import com.intkr.saas.util.JsonUtil;

/**
 * 
 * @author Beiden
 * @date 2016-8-18 下午1:34:49
 * @version 1.0
 */
public class TwoMap<A, B> {

	public A one;

	public B two;

	public A getOne() {
		return one;
	}

	public void setOne(A one) {
		this.one = one;
	}

	public B getTwo() {
		return two;
	}

	public void setTwo(B two) {
		this.two = two;
	}

	public String toString() {
		return JsonUtil.toJson(this);
	}

}
