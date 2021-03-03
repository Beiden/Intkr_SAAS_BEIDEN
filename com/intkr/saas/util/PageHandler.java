package com.intkr.saas.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页处理器
 * 
 * @author beidenhuang
 * @datetime 2019-1-17 下午5:10:28
 */
public class PageHandler<T> {

	public static <T> void process(int pageSize, List<T> data, Handler handle) {
		if (data == null || data.isEmpty()) {
			return;
		}
		if (data.size() > pageSize) {
			int page = 1;
			int fromIndex = 0;
			int toIndex = 0;
			while (toIndex < data.size()) {
				fromIndex = (page - 1) * pageSize;
				toIndex = page * pageSize > data.size() ? data.size() : page * pageSize;
				List<T> subBoList = data.subList(fromIndex, toIndex);
				handle.handle(subBoList);
				page++;
			}
		} else {
			handle.handle(data);
		}
	}

	/**
	 * 每页处理器
	 */
	public static interface Handler<T> {
		public void handle(List<T> data);
	}

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("1");

		PageHandler.process(3, list, new PageHandler.Handler<String>() {
			public void handle(List<String> data) {
				System.out.println(data);
			}
		});
	}

}
