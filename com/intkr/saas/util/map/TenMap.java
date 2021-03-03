package com.intkr.saas.util.map;

import com.intkr.saas.util.JsonUtil;

/**
 * 
 * @author Beiden
 * @date 2016-8-18 下午1:34:49
 * @version 1.0
 */
public class TenMap<A, B, C, D, E, F, G, H, I, J> {

	public A one;

	public B two;

	public C three;

	public D four;

	public E five;

	public F six;

	public G seven;

	public H eight;

	public I nine;

	public J ten;

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

	public E getFive() {
		return five;
	}

	public void setFive(E five) {
		this.five = five;
	}

	public F getSix() {
		return six;
	}

	public void setSix(F six) {
		this.six = six;
	}

	public G getSeven() {
		return seven;
	}

	public void setSeven(G seven) {
		this.seven = seven;
	}

	public H getEight() {
		return eight;
	}

	public void setEight(H eight) {
		this.eight = eight;
	}

	public I getNine() {
		return nine;
	}

	public void setNine(I nine) {
		this.nine = nine;
	}

	public J getTen() {
		return ten;
	}

	public void setTen(J ten) {
		this.ten = ten;
	}

	public String toString() {
		return JsonUtil.toJson(this);
	}

}
