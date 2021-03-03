package com.intkr.saas.manager.db.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.db.DatabaseDAO;
import com.intkr.saas.domain.bo.db.DatabaseBO;
import com.intkr.saas.domain.bo.db.DatasourceBO;
import com.intkr.saas.domain.bo.db.DatasourceDatabaseBO;
import com.intkr.saas.domain.bo.db.FieldBO;
import com.intkr.saas.domain.bo.db.SqlResultBO;
import com.intkr.saas.domain.bo.db.TableBO;
import com.intkr.saas.domain.dbo.db.DatabaseDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.db.DatabaseManager;
import com.intkr.saas.manager.db.DatasourceDatabaseManager;
import com.intkr.saas.manager.db.DatasourceManager;
import com.intkr.saas.manager.db.FieldManager;
import com.intkr.saas.manager.db.TableManager;
import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.util.db.ColumnDO;
import com.intkr.saas.util.db.DBUtil;
import com.intkr.saas.util.db.TableDO;

/**
 * 
 * 
 * @table database_tab
 * 
 * @author Beiden
 * @date 2020-04-02 19:03:12
 * @version 1.0
 */
@Repository("db.DatabaseManager")
public class DatabaseManagerImpl extends BaseManagerImpl<DatabaseBO, DatabaseDO> implements DatabaseManager {

	@Resource
	private DatabaseDAO databaseDAO;

	@Resource
	private DatasourceDatabaseManager datasourceDatabaseManager;

	@Resource
	private DatasourceManager datasourceManager;

	@Resource
	private TableManager tableManager;

	@Resource
	private FieldManager fieldManager;

	public BaseDAO<DatabaseDO> getBaseDAO() {
		return databaseDAO;
	}

	public void syncTables(DatabaseBO database) {
		if (database == null) {
			return;
		}
		DatasourceDatabaseBO datasourceDatabase = datasourceDatabaseManager.getDefault(database.getId());
		if (datasourceDatabase == null) {
			return;
		}
		DatasourceBO datasource = datasourceManager.get(datasourceDatabase.getDatasourceId());
		String jdbcDriver = "com.mysql.jdbc.Driver";
		String jdbcUrl = "jdbc:mysql://" + datasource.getHost() + ":" + datasource.getPort() + "/" + database.getDbName() + "?useUnicode=true&amp;characterEncoding=" + datasource.getCharacterEncoding();
		String jdbcUser = datasource.getUser();
		String jdbcPasswd = datasource.getPwd();
		Connection con = DBUtil.getConnection(jdbcDriver, jdbcUrl, jdbcUser, jdbcPasswd);
		List<TableDO> tableList = DBUtil.getTables(con);
		DBUtil.close(con);
		for (TableDO tableDO : tableList) {
			processTable(database, tableDO);
		}
	}

	public List<String> getTableNamesRt(Long databaseId) {
		if (databaseId == null || databaseId <= 0) {
			return new ArrayList<String>();
		}
		DatabaseBO database = get(databaseId);
		if (database == null) {
			return new ArrayList<String>();
		}
		DatasourceDatabaseBO datasourceDatabase = datasourceDatabaseManager.getDefault(database.getId());
		if (datasourceDatabase == null) {
			return new ArrayList<String>();
		}
		DatasourceBO datasource = datasourceManager.get(datasourceDatabase.getDatasourceId());
		String jdbcDriver = "com.mysql.jdbc.Driver";
		String jdbcUrl = "jdbc:mysql://" + datasource.getHost() + ":" + datasource.getPort() + "/" + database.getDbName() + "?useUnicode=true&characterEncoding=" + datasource.getCharacterEncoding();
		String jdbcUser = datasource.getUser();
		String jdbcPasswd = datasource.getPwd();
		// Connection con = DbPool.getConnection(jdbcDriver, jdbcUrl, jdbcUser,
		// jdbcPasswd);
		Connection con = DBUtil.getConnection(jdbcDriver, jdbcUrl, jdbcUser, jdbcPasswd);
		List<String> tableList = DBUtil.getTableNames(con);
		DBUtil.close(con);
		return tableList;
	}

	public SqlResultBO executeSql(DatasourceBO datasource, DatabaseBO database, String sql) {
		try {
			if (datasource == null) {
				return null;
			}
			if (database == null) {
				return null;
			}
			if (sql == null || "".equals(sql)) {
				return null;
			}
			String jdbcDriver = "com.mysql.jdbc.Driver";
			String jdbcUrl = "jdbc:mysql://" + datasource.getHost() + ":" + datasource.getPort() + "/" + database.getDbName() + "?useUnicode=true&characterEncoding=" + datasource.getCharacterEncoding();
			String jdbcUser = datasource.getUser();
			String jdbcPasswd = datasource.getPwd();
			// Connection con = DbPool.getConnection(jdbcDriver, jdbcUrl,
			// jdbcUser, jdbcPasswd);
			Connection con = DBUtil.getConnection(jdbcDriver, jdbcUrl, jdbcUser, jdbcPasswd);
			Statement stmt = con.createStatement();
			SqlResultBO sqlResult = new SqlResultBO();
			if (sql.contains("insert")) {
				boolean result = stmt.execute(sql);
			} else if (sql.contains("delete")) {
				boolean result = stmt.execute(sql);
			} else if (sql.contains("update")) {
				boolean result = stmt.execute(sql);
			} else if (sql.contains("select")) {
				if (!sql.contains("limit")) {
					sql += " limit 20";
				}
				ResultSet rs = stmt.executeQuery(sql);
				ResultSetMetaData rsmd = rs.getMetaData();
				int count = rsmd.getColumnCount();
				for (int i = 0; i < count; i++) {
					String header = rsmd.getColumnName(i + 1);
					sqlResult.addHeader(header);
				}
				while (rs.next()) {
					List<Object> data = new ArrayList<Object>();
					for (String header : sqlResult.getHeaders()) {
						Object value = rs.getObject(header);
						data.add(value);
					}
					sqlResult.addRow(data);
				}
				rs.close();
			}
			stmt.close();
			DBUtil.close(con);
			sqlResult.setSql(sql);
			sqlResult.setResult(true);
			return sqlResult;
		} catch (SQLException e) {
			logger.error("", e);
			return null;
		}
	}

	public DatasourceBO getDefaultDatasource(Long databaseId) {
		if (databaseId == null || databaseId <= 0) {
			return null;
		}
		DatabaseBO database = get(databaseId);
		if (database == null) {
			return null;
		}
		DatasourceDatabaseBO datasourceDatabase = datasourceDatabaseManager.getDefault(database.getId());
		if (datasourceDatabase == null) {
			return null;
		}
		DatasourceBO datasource = datasourceManager.get(datasourceDatabase.getDatasourceId());
		return datasource;
	}

	private void processTable(DatabaseBO database, TableDO tableDO) {
		TableBO tableBO = tableManager.get(database.getId(), tableDO.getName());
		if (tableBO != null) {// update表信息
			TableBO table = tableManager.get(database.getId(), tableDO.getName());
			if (table.getIndexDesc() == null || "".equals(table.getIndexDesc())) {
				tableManager.updateIndexDesc(table.getId(), "<pre>" + tableDO.getIndexDesc() + "</pre>");
			}
			fieldManager.fill(table);
			for (ColumnDO columnDO : tableDO.getColumns()) {
				FieldBO fieldBO = getField(table, columnDO);
				if (fieldBO != null) {
					if (fieldBO.getNote() != null && !fieldBO.getNote().endsWith(columnDO.getDesc())) {
						fieldBO.setNote(fieldBO.getNote() + columnDO.getDesc());
					} else if (fieldBO.getNote() != null && fieldBO.getNote().endsWith(columnDO.getDesc())) {

					} else {
						fieldBO.setNote(columnDO.getDesc());
					}
					fieldBO.setAllowNull(columnDO.getNullAble());
					fieldBO.setDbLength(columnDO.getColumnSize());
					fieldBO.setType(columnDO.getClazType());
					fieldBO.setDbType(columnDO.getType());
					fieldManager.update(fieldBO);
				} else {
					fieldBO = new FieldBO();
					fieldBO.setId(fieldManager.getId());
					fieldBO.setSaasId(database.getSaasId());
					fieldBO.setDatabaseId(database.getId());
					fieldBO.setTableId(tableBO.getId());
					fieldBO.setDbName(columnDO.getName());
					if (columnDO.getDesc() == null || "".equals(columnDO.getDesc())) {
						fieldBO.setName(columnDO.getName());
					} else {
						fieldBO.setName(columnDO.getDesc());
					}
					fieldBO.setAllowNull(columnDO.getNullAble());
					fieldBO.setDbLength(columnDO.getColumnSize());
					fieldBO.setType(columnDO.getClazType());
					fieldBO.setDbType(columnDO.getType());
					fieldBO.setSort(fieldBO.getId().doubleValue());
					fieldManager.insert(fieldBO);
				}
			}
			return;
		}
		tableBO = createTable(database, tableDO);
		Double sort = 100d;
		for (ColumnDO columnDO : tableDO.getColumns()) {
			FieldBO fieldBO = new FieldBO();
			fieldBO.setId(fieldManager.getId());
			fieldBO.setSaasId(database.getSaasId());
			fieldBO.setDatabaseId(database.getId());
			fieldBO.setTableId(tableBO.getId());
			fieldBO.setDbName(columnDO.getName());
			if (columnDO.getDesc() == null || "".equals(columnDO.getDesc())) {
				fieldBO.setName(columnDO.getName());
			} else {
				fieldBO.setName(columnDO.getDesc());
			}
			fieldBO.setAllowNull(columnDO.getNullAble());
			fieldBO.setDbLength(columnDO.getColumnSize());
			fieldBO.setType(columnDO.getClazType());
			fieldBO.setDbType(columnDO.getType());
			fieldBO.setSort(sort);
			sort += 100;
			fieldManager.insert(fieldBO);
		}
	}

	private FieldBO getField(TableBO table, ColumnDO column) {
		for (FieldBO field : table.getFields()) {
			if (column.getName().equals(field.getDbName())) {
				return field;
			}
		}
		return null;
	}

	private TableBO createTable(DatabaseBO database, TableDO tableDO) {
		TableBO tableBO;
		tableBO = new TableBO();
		tableBO.setId(tableManager.getId());
		tableBO.setType("table");
		tableBO.setPid(1L);
		tableBO.setDatabaseId(database.getId());
		tableBO.setSaasId(database.getSaasId());
		tableBO.setDbName(tableDO.getName());
		tableBO.setName(tableDO.getName());
		tableBO.setNote(tableDO.getDesc());
		tableBO.setSort(tableBO.getId().doubleValue());
		tableBO.setIndexDesc("<pre>" + tableDO.getIndexDesc() + "</pre>");
		tableManager.insert(tableBO);
		return tableBO;
	}

	public void syncTable(DatabaseBO database, String tableName) {
		if (database == null) {
			return;
		}
		DatasourceDatabaseBO datasourceDatabase = datasourceDatabaseManager.getDefault(database.getId());
		if (datasourceDatabase == null) {
			return;
		}
		DatasourceBO datasource = datasourceManager.get(datasourceDatabase.getDatasourceId());
		String jdbcDriver = "com.mysql.jdbc.Driver";
		String jdbcUrl = "jdbc:mysql://" + datasource.getHost() + ":" + datasource.getPort() + "/" + database.getDbName() + "?useUnicode=true&amp;characterEncoding=" + datasource.getCharacterEncoding();
		String jdbcUser = datasource.getUser();
		String jdbcPasswd = datasource.getPwd();
		// Connection con = DbPool.getConnection(jdbcDriver, jdbcUrl, jdbcUser,
		// jdbcPasswd);
		Connection con = DBUtil.getConnection(jdbcDriver, jdbcUrl, jdbcUser, jdbcPasswd);
		TableDO table = DBUtil.getTable(con, tableName);
		DBUtil.close(con);
		processTable(database, table);
	}

	public void syncTable(Long datasourceId, Long databaseId, String tableName) {
		if (datasourceId == null || databaseId == null || tableName == null || "".equals(tableName)) {
			return;
		}
		DatabaseBO database = get(databaseId);
		if (database == null) {
			return;
		}
		DatasourceBO datasource = datasourceManager.get(datasourceId);
		String jdbcDriver = "com.mysql.jdbc.Driver";
		String jdbcUrl = "jdbc:mysql://" + datasource.getHost() + ":" + datasource.getPort() + "/" + database.getDbName() + "?useUnicode=true&characterEncoding=" + datasource.getCharacterEncoding();
		String jdbcUser = datasource.getUser();
		String jdbcPasswd = datasource.getPwd();
		// Connection con = DbPool.getConnection(jdbcDriver, jdbcUrl, jdbcUser,
		// jdbcPasswd);
		Connection con = DBUtil.getConnection(jdbcDriver, jdbcUrl, jdbcUser, jdbcPasswd);
		TableDO table = DBUtil.getTable(con, tableName);
		DBUtil.close(con);
		processTable(database, table);
	}

	public static void main(String[] args) {
		DatabaseManagerImpl manager = IOC.get(DatabaseManagerImpl.class);
		DatabaseBO database = manager.get(1001001);
		// manager.syncTable(database, "abc_classification_config_tab");
		// manager.syncTable(database, "idcreator_tab");
		// manager.syncTable(database, "models_awesomebasemodel");
		// manager.syncTable(database, "crontab_monitor");
		// manager.syncTable(database, "celery_retry_task_tab");
		// manager.syncTable(database, "celery_custom_script");
		// manager.syncTable(database, "djcelery_periodictask");
		// manager.syncTable(database, "djcelery_intervalschedule");
		// manager.syncTable(database, "djcelery_periodictasks");
		// manager.syncTable(database, "djcelery_crontabschedule");

		// manager.syncTable(database, "conf_tab");
		// manager.syncTable(database, "client_tab");
		// manager.syncTable(database, "client_jwt_tab");
		// manager.syncTable(database, "app_upgrade_policy_tab");
		// manager.syncTable(database, "app_version_tab");

		// manager.syncTable(database, "warehouse_tab");
		// manager.syncTable(database, "rule_warehouse_tab");
		// manager.syncTable(database, "shopid_filter_tab");
		// manager.syncTable(database, "shop_whs_tab");
		// manager.syncTable(database, "sku_tab_00000000");
		// manager.syncTable(database, "sku_uid_rang_tab");
		// manager.syncTable(database, "sku_print_mapping_tab");
		// manager.syncTable(database, "sku_mapping_tab");
		// manager.syncTable(database, "sku_size_type_tab");
		// manager.syncTable(database, "category_tab");
		// manager.syncTable(database, "category_tree_operation_log_tab");
		// manager.syncTable(database, "category_tree_tab");
		// manager.syncTable(database, "abc_classification_config_tab");
		// manager.syncTable(database, "export_task_tab");
		// manager.syncTable(database, "zone_v2_tab");
		// manager.syncTable(database, "zone_sector_tab");
		// manager.syncTable(database, "zone_cluster_tab");
		// manager.syncTable(database, "pathway_tab");
		// manager.syncTable(database, "cell_tab");
		// manager.syncTable(database, "cell_size_type_tab");
		// manager.syncTable(database, "cell_struct_attr_tab");
		// manager.syncTable(database, "supplier_tab");
		// manager.syncTable(database, "supplier_sku_tab");
		// manager.syncTable(database, "email_center_tab");
		// manager.syncTable(database, "printer_setting_tab");

		// manager.syncTable(database, "rule_tab");
		// manager.syncTable(database, "rule_group_tab");
		// manager.syncTable(database, "rule_sku_tab");
		// manager.syncTable(database, "life_cycle_rule_tab");
		// manager.syncTable(database, "life_cycle_rule_item_tab");
		// manager.syncTable(database, "putaway_rule_tab");

		// manager.syncTable(database, "dashboard_inbound_sum_tab");
		// manager.syncTable(database, "dashboard_inbound_sku_sum_tab");
		// manager.syncTable(database, "dashboard_inbound_efficiency_tab");
		// manager.syncTable(database, "dashboard_inbound_realtime_order_tab");
		// manager.syncTable(database, "po_inbound_shift_tab");
		// manager.syncTable(database, "asn_quota_plan_tab");
		// manager.syncTable(database, "asn_quota_config_tab");
		// manager.syncTable(database, "asn_quota_tracking_log_tab");
		// manager.syncTable(database, "asn_quota_tracking_log_history_tab");
		// manager.syncTable(database, "purchase_order_tab");
		// manager.syncTable(database, "purchase_order_sku_tab");
		// manager.syncTable(database, "po_inbound_tab");
		// manager.syncTable(database, "po_inbound_batch_info_tab");
		// manager.syncTable(database, "po_inbound_arrival_tab");
		// manager.syncTable(database, "po_inbound_unit_tab");
		// manager.syncTable(database, "po_inbound_batch_info_temp_tab");
		// manager.syncTable(database, "po_inbound_location_tab");
		// manager.syncTable(database, "po_inbound_location_count_tab");
		// manager.syncTable(database, "po_inbound_batch_transfer_tab");
		// manager.syncTable(database, "po_inbound_box_tab");
		// manager.syncTable(database, "po_inbound_sku_list_tab");
		// manager.syncTable(database, "inbound_order_tracking_tab_00000000");

		// manager.syncTable(database, "return_order_tab");
		// manager.syncTable(database, "return_order_sku_list_tab");
		// manager.syncTable(database, "return_order_unit_tab");
		// manager.syncTable(database, "return_ship_order_unit_tab");
		// manager.syncTable(database, "return_order_sync_stock_sku_list_tab");
		// manager.syncTable(database, "return_order_tracking_tab");

		// manager.syncTable(database, "dashboard_inventory_expd_sum_tab");
		// manager.syncTable(database, "dashboard_inventory_count_tab");
		// manager.syncTable(database, "dashboard_location_usage_sum_tab");
		// manager.syncTable(database, "sku_location_new_tab");
		// manager.syncTable(database, "sku_location_temp_tab");
		// manager.syncTable(database, "sku_location_tab");
		// manager.syncTable(database, "location_surplus_capacity_tab");
		// manager.syncTable(database, "sku_unit_tab");
		// manager.syncTable(database, "batch_tab");
		// manager.syncTable(database, "sku_stock_stats_tab");
		// manager.syncTable(database, "sku_unit_tab");
		// manager.syncTable(database, "sku_unit_history_tab_00000000");
		// manager.syncTable(database, "sku_check_tab");
		// manager.syncTable(database, "sku_lock_tab");
		// manager.syncTable(database, "sku_check_list_tab");
		// manager.syncTable(database, "sku_cache_tab");
		// manager.syncTable(database, "inventory_adjust_task_status_log_tab");
		// manager.syncTable(database, "inventory_adjust_task_tab");
		// manager.syncTable(database, "inventory_adjust_sku_location_tab");
		// manager.syncTable(database,
		// "inventory_adjust_sku_location_batch_tab");
		// manager.syncTable(database, "inventory_adjust_sku_unit_tab");
		// manager.syncTable(database, "circle_count_check_tmp_tab");
		// manager.syncTable(database, "cycle_count_task_tab");
		// manager.syncTable(database, "cycle_count_sku_location_approval_tab");
		// manager.syncTable(database, "cycle_count_approval_batch_tab");
		// manager.syncTable(database,
		// "cycle_count_sku_location_log_tab_00000000");
		// manager.syncTable(database, "cycle_count_unit_tab_00000000");
		// manager.syncTable(database, "cycle_count_task_assign_tab");
		// manager.syncTable(database, "cycle_count_sku_location_tab");
		// manager.syncTable(database, "cycle_count_status_log_tab");
		// manager.syncTable(database, "batch_transfer_tab");
		// manager.syncTable(database, "batch_transfer_task_tab");
		// manager.syncTable(database, "batch_transfer_location_tab");
		// manager.syncTable(database, "batch_transfer_location_unit_tab");
		// manager.syncTable(database, "transaction_batch_tab");
		// manager.syncTable(database, "transaction_unit_list_tab");
		// manager.syncTable(database, "transaction_tab");
		// manager.syncTable(database, "transaction_batch_cache_tab");
		// manager.syncTable(database, "transaction_unit_cache_tab");
		// manager.syncTable(database, "transaction_cache_tab");
		// manager.syncTable(database, "transaction_unit_tab");
		// manager.syncTable(database, "transaction_stats_cache_tab");
		// manager.syncTable(database, "transaction_stats_tab");
		// manager.syncTable(database, "transaction_pool_tab_00000000");
		// manager.syncTable(database, "transaction_exc_event_tab");
		// manager.syncTable(database, "transaction_unit_pool_tab");
		// manager.syncTable(database, "replenishment_generate_tab");
		// manager.syncTable(database, "replenishment_sku_daily_sales_tab");
		// manager.syncTable(database, "replenishment_task_tab");
		// manager.syncTable(database, "replenishment_task_sku_location_tab");
		// manager.syncTable(database, "replenishment_running_sku_tab");
		// manager.syncTable(database, "replenishment_forecast_sales_tab");
		// manager.syncTable(database, "replenishment_sku_average_sales_tab");
		// manager.syncTable(database, "replenishment_daily_rule_item_tab");
		// manager.syncTable(database, "replenishment_sku_rule_tab");
		// manager.syncTable(database, "replenishment_daily_rule_tab");

		// manager.syncTable(database, "rack_transfer_check_tmp_tab");
		// manager.syncTable(database, "rack_transfer_task_tab");
		// manager.syncTable(database, "rack_transfer_sku_batch_tab");
		// manager.syncTable(database, "rack_transfer_sku_list_tab");
		// manager.syncTable(database, "rack_transfer_task_log_tab");
		// manager.syncTable(database, "rack_transfer_sku_unit_tab");
		// manager.syncTable(database, "reconciliation_task_tab");

		// manager.syncTable(database, "dashboard_outbound_sku_sum_tab");
		// manager.syncTable(database, "dashboard_outbound_efficiency_tab");
		// manager.syncTable(database, "dashboard_outbound_sum_tab");
		// manager.syncTable(database, "dashboard_outbound_realtime_order_tab");
		// manager.syncTable(database, "order_statistic_tab");
		// manager.syncTable(database, "invoice_tab");
		// manager.syncTable(database, "my_invoice_tab");
		// manager.syncTable(database, "wave_tab");
		// manager.syncTable(database, "wave_rule_tab");
		// manager.syncTable(database, "wave_rule_group_tab");
		// manager.syncTable(database, "custom_wave_rule_tab");
		// manager.syncTable(database, "order_tab_00000000");
		// manager.syncTable(database, "order_sn_tab_00000000");
		// manager.syncTable(database, "order_index_tab");
		// manager.syncTable(database, "order_tracking_tab_00000000");
		// manager.syncTable(database, "order_sku_list_tab_00000000");
		// manager.syncTable(database, "initial_sbs_order_tab");
		// manager.syncTable(database, "order_unit_tab_00000000");
		// manager.syncTable(database, "consignment_tab_00000000");
		// manager.syncTable(database, "allocate_rule_tab");
		// manager.syncTable(database, "pickup_planned_inventory_tab");
		// manager.syncTable(database, "pickup_tab_00000000");
		// manager.syncTable(database, "pickup_order_list_tab_00000000");
		// manager.syncTable(database, "pickup_unit_tab_00000000");
		// manager.syncTable(database, "pickup_device_tab");
		// manager.syncTable(database, "unpicking_order_mapping_tab");
		// manager.syncTable(database,
		// "pickup_planned_and_actual_location_tab");
		// manager.syncTable(database, "pickup_sku_list_tab_00000000");
		// manager.syncTable(database, "packaging_material_tab");
		// manager.syncTable(database, "packaging_suggest_tab");
		// manager.syncTable(database, "shipped_order_tab");
		// manager.syncTable(database, "shipped_order_history_tab");
		// manager.syncTable(database, "return_inbound_order_sku_mapping_tab");
		// manager.syncTable(database, "return_inbound_sku_tab");
		// manager.syncTable(database, "return_buyer_sku_tab");
		// manager.syncTable(database, "return_inbound_tab");
		// manager.syncTable(database, "return_buyer_tracking_log_tab");
		// manager.syncTable(database, "return_buyer_tab");

		// manager.syncTable(database, "move_transfer_outbound_tab");
		// manager.syncTable(database, "move_transfer_outbound_sku_list_tab");
		// manager.syncTable(database, "move_transfer_outbound_tracking_log");
		// manager.syncTable(database, "move_transfer_inbound_sku_tab");
		// manager.syncTable(database, "move_transfer_inbound_unit_tab");
		// manager.syncTable(database, "move_transfer_inbound_tab");

		// manager.syncTable(database, "move_order_tab");
		// manager.syncTable(database, "move_order_record_tab");
		// manager.syncTable(database, "move_order_excel_tab");
		// manager.syncTable(database, "move_order_tracking_log_tab");

		// manager.syncTable(database, "box_tab");
		// manager.syncTable(database, "box_sku_tab");
		// manager.syncTable(database, "box_sku_unit_tab");
		// manager.syncTable(database, "awb_kota_code_tab");

		// manager.syncTable(database, "task_lane_code_tab");
		// manager.syncTable(database, "putaway_task_tab");
		// manager.syncTable(database, "putaway_sku_tab");
		// manager.syncTable(database, "putaway_sku_unit_tab");
		// manager.syncTable(database, "picking_task_tab");
		// manager.syncTable(database, "picking_task_history_tab");
		// manager.syncTable(database, "reverse_task_tab");
		// manager.syncTable(database, "shipped_order_task_tab");

		// manager.syncTable(database, "user_tab");
		// manager.syncTable(database, "user_perm_tab");
		// manager.syncTable(database, "role_perm_tab");
		// manager.syncTable(database, "user_token_tab");
		// manager.syncTable(database, "user_login_tab");
		// manager.syncTable(database, "user_login_log_tab");
		// manager.syncTable(database, "user_behavior_tab");
		// manager.syncTable(database, "business_admin_log_tab");
		// manager.syncTable(database, "business_config_tab");

		// manager.syncTable(database, "task_lane_code_history_tab");
		// manager.syncTable(database, "print_record_tab");
		// manager.syncTable(database, "sheet_status_sync_log_tab_00000000");
		// manager.syncTable(database, "sheet_status_change_log_tab_00000000");
		// manager.syncTable(database, "sheet_status_sync_log_event_tab");
		// manager.syncTable(database, "uploaded_files_info_tab");
		// manager.syncTable(database, "location_modify_log_tab");
		// manager.syncTable(database, "op_history_tab");
		// manager.syncTable(database, "op_history_tab_00000000");
		// manager.syncTable(database, "stock_sync_log_tab");
		// manager.syncTable(database, "sync_stock_record_tab");
		// manager.syncTable(database, "sku_modify_log_tab");
		// manager.syncTable(database, "sku_modify_all_log_tab");

		manager.syncTable(database, "oms_sku_mapping_tab");
		manager.syncTable(database, "oms_mapping_order_sku_price_tab");

	}

	public List<TableBO> getMenu(Long databaseId) {
		if (databaseId == null || databaseId <= 0) {
			return new ArrayList<TableBO>();
		}
		TableBO query = new TableBO();
		query.setDatabaseId(databaseId);
		query.setQuery("orderBy", "sort");
		query.setQuery("order", "asc");
		query.set_pageSize(10000);
		List<TableBO> list = tableManager.select(query);
		for (TableBO parent : list) {
			for (TableBO child : list) {
				if (parent.getId().equals(child.getPid())) {
					parent.addChild(child);
					child.setParent(parent);
				}
			}
		}
		List<TableBO> returnList = new ArrayList<TableBO>();
		for (TableBO table : list) {
			if (table.getParent() == null) {
				returnList.add(table);
			}
		}
		return returnList;
	}

	public List<TableBO> getMenu(Long databaseId, String dbNameLike) {
		if (databaseId == null || databaseId <= 0) {
			return new ArrayList<TableBO>();
		}
		TableBO query = new TableBO();
		query.setDatabaseId(databaseId);
		query.setQuery("orderBy", "sort");
		query.setQuery("order", "asc");
		if (dbNameLike != null && !"".equals(dbNameLike)) {
			query.setQuery("dbNameLike", dbNameLike);
			query.setQuery("dbNameLikeSQL", "%" + dbNameLike + "%");
		}
		query.set_pageSize(10000);
		List<TableBO> list = tableManager.select(query);
		for (TableBO parent : list) {
			for (TableBO child : list) {
				if (parent.getId().equals(child.getPid())) {
					parent.addChild(child);
					child.setParent(parent);
				}
			}
		}
		List<TableBO> returnList = new ArrayList<TableBO>();
		for (TableBO table : list) {
			if (table.getParent() == null) {
				returnList.add(table);
			}
		}
		return returnList;
	}

	public List<DatasourceBO> selectDatasource(Long databaseId) {
		if (databaseId == null || databaseId <= 0) {
			return new ArrayList<DatasourceBO>();
		}
		DatasourceDatabaseBO query = new DatasourceDatabaseBO();
		query.setDatabaseId(databaseId);
		List<DatasourceDatabaseBO> datasourceDatabaseList = datasourceDatabaseManager.select(query);
		if (datasourceDatabaseList == null) {
			return new ArrayList<DatasourceBO>();
		}
		List<Long> ids = new ArrayList<Long>();
		for (DatasourceDatabaseBO tmp : datasourceDatabaseList) {
			ids.add(tmp.getDatasourceId());
		}
		DatasourceBO dsQuery = new DatasourceBO();
		dsQuery.setQuery("ids", ids);
		dsQuery.setQuery("orderBy", "name");
		dsQuery.setQuery("order", "asc");
		List<DatasourceBO> list = datasourceManager.select(dsQuery);
		return list;
	}

	public DatasourceDatabaseBO fill(DatasourceDatabaseBO datasourceDatabase) {
		if (datasourceDatabase == null) {
			return null;
		}
		datasourceDatabase.setDatabase(get(datasourceDatabase.getDatabaseId()));
		return datasourceDatabase;
	}

}
