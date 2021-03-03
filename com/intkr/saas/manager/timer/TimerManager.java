package com.intkr.saas.manager.timer;

import java.util.List;

import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.domain.bo.timer.TimerBO;
import com.intkr.saas.domain.dbo.timer.TimerDO;

/**
 * 
 * @author Beiden
 * @date 2016-6-2 下午8:51:40
 * @version 1.0
 */
public interface TimerManager extends BaseManager<TimerBO, TimerDO> {

	public List<TimerBO> getLastMinute();

	public TimerBO getByCode(String code);

}
