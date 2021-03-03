package com.intkr.saas.client.conf;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.intkr.saas.domain.bo.conf.IkIdBO;
import com.intkr.saas.manager.conf.IdManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * 
 * @author Beiden
 * @date 2011-6-30 下午2:15:59
 * @version 1.0
 */
public class IdEngine {

	private static Long idCount = 1000l;

	private static Map<String, IdRange> idCache = new HashMap<String, IdRange>();

	private static DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");

	private static Random random = new Random();

	private static DecimalFormat ddf = new DecimalFormat("0000");

	public synchronized static String getCode() {
		String timeString = df.format(new Date());
		Integer i = random.nextInt(9999);
		return timeString + ddf.format(i.doubleValue());
	}

	public synchronized static long get(String tableName) {
		IdManager idManager = IOC.get(IdManager.class);
		IdRange idRange = null;
		if (!idCache.containsKey(tableName)) {// 第一次加载ID生成数据
			IkIdBO idBO = idManager.getByTableName(tableName);
			if (idBO == null) {
				idBO = new IkIdBO();
				idBO.setTableName(tableName);
				idBO.setCode(1000000l);
				idManager.insert(idBO);
			}
			idBO = idManager.getByTableName(tableName);
			idRange = new IdRange();
			idRange.setId(idBO.getId());
			idRange.setEnd(idBO.getCode() + idCount);
			idRange.setStart(idBO.getCode() + 1);
			idRange.getNow().set(idBO.getCode() + 1);

			idBO.setCode(idRange.getEnd());
			idManager.update(idBO);
			idCache.put(tableName, idRange);
		}
		idRange = idCache.get(tableName);
		if (idRange.getNow().get() > idRange.getEnd()) {
			IkIdBO tmpId = new IkIdBO();
			tmpId.setId(idRange.getId());
			int tryCount = 0;
			do {
				if (tryCount++ >= 10) {// 尝试超过10次再去重新跟数据库同步
					IkIdBO id = idManager.getByTableName(tableName);
					idRange.setId(id.getId());
					idRange.setEnd(id.getCode());
					idRange.setStart(id.getCode() - idCount + 1);
					idRange.getNow().set(id.getCode() - idCount + 1);
				}
				tmpId.setQuery("beforeCode", idRange.getEnd());
				idRange.setStart(idRange.getEnd() + 1);
				idRange.getNow().set(idRange.getEnd() + 1);
				idRange.setEnd(idRange.getEnd() + idCount);
				tmpId.setCode(idRange.getEnd());
			} while (idManager.update(tmpId) == 0);
		}
		long returnId = idRange.getNow().getAndIncrement();
		return returnId;
	}

	/**
	 * 是否是有效的ID
	 * 
	 * @param id
	 * @return
	 */
	public static boolean isValidate(String id) {
		if (id == null && "".equals(id)) {
			return false;
		}
		try {
			return isValidate(Long.valueOf(id));
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isValidate(Long id) {
		if (id == null) {
			return false;
		}
		if (id <= 0l) {
			return false;
		}
		return true;
	}

}