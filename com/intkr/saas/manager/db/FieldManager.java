package com.intkr.saas.manager.db;

import java.util.List;

import com.intkr.saas.domain.bo.db.FieldBO;
import com.intkr.saas.domain.bo.db.TableBO;
import com.intkr.saas.domain.dbo.db.FieldDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * 
 * @table field_tab
 * 
 * @author Beiden
 * @date 2020-04-02 21:01:47
 * @version 1.0
 */
public interface FieldManager extends BaseManager<FieldBO, FieldDO> {

	public List<FieldBO> selectByTableId(Long tableId);

	public TableBO fill(TableBO table);

}
