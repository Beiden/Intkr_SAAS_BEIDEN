package com.intkr.saas.manager.sns.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.sns.SysImgDAO;
import com.intkr.saas.domain.bo.sns.SysImgBO;
import com.intkr.saas.domain.dbo.sns.SysImgDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.sns.SysImgManager;

/**
 * 
 * @author Beiden
 * @date 2016-5-26 下午2:23:56
 * @version 1.0
 */
@Repository("SysImgManager")
public class SysImgManagerImpl extends BaseManagerImpl<SysImgBO, SysImgDO> implements SysImgManager {

	@Resource
	private SysImgDAO sysImgDAO;

	public BaseDAO<SysImgDO> getBaseDAO() {
		return sysImgDAO;
	}

}
