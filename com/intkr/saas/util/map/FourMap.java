package com.intkr.saas.util.map;

import com.intkr.saas.util.JsonUtil;

/**
 * 
 * @author Beiden
 * @date 2016-8-18 下午1:34:49
 * @version 1.0
 */
public class FourMap<A, B, C, D> {

	public A one;

	public B two;

	public C three;

	public D four;

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

	public C getThree() {
		return three;
	}

	public void setThree(C three) {
		this.three = three;
	}

	public D getFour() {
		return four;
	}

	public void setFour(D four) {
		this.four = four;
	}

	public String toString() {
		return JsonUtil.toJson(this);
	}

}
