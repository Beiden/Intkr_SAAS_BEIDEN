package com.intkr.saas.util.dever;

import com.intkr.saas.util.db.Context;

public class MakerEngineTest {

	public static void main(String[] args) throws Exception {
		mysql();
	}

	private static void es() {
		Context.setEsUrls("http://es.d.intkr.com/");
		Context.setEsIndex("test_from_beiden");
		Context.setEsType("doc");
		Context.setTableDesc("测试表格");
		Context.setClazPackage("com.intkr.saas");
		Context.setModule(".admin.test");
		Context.init();

		DOMaker.maker();
		BOMaker.maker();
		DaoMaker.maker();
		DaoEsImplMaker.maker();
		ManagerMaker.maker();
		ManagerImplMaker.maker();
		ActionMaker.maker();

		MgrJavaMaker.maker();
		MgrVMMaker.maker();
		AddUpdateJavaMaker.maker();
		ModelMaker.maker();
		AddUpdateVMMaker.maker();
		JsMaker.maker();
	}

	private static void mysql() {
		Context.setJdbcDriver("com.mysql.jdbc.Driver");
		Context.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/saas_cms?useUnicode=true&amp;characterEncoding=utf8");
		Context.setJdbcUser("root");
		Context.setJdbcPasswd("IntKr!@34");
		Context.setTableDesc("运费模版");
		Context.setTableName("delivery_fee_template");
		Context.setClazPackage("com.intkr.saas");
		Context.setModule(".shop");
		Context.init();

		MapperMaker.maker();
		DOMaker.maker();
		BOMaker.maker();
		DaoMaker.maker();
		DaoImplMaker.maker();
		ManagerMaker.maker();
		ManagerImplMaker.maker();
		ActionMaker.maker();

		MgrJavaMaker.maker();
		MgrVMMaker.maker();
		AddUpdateJavaMaker.maker();
		AddUpdateVMMaker.maker();
		ModelMaker.maker();
		JsMaker.maker();
	}

}
