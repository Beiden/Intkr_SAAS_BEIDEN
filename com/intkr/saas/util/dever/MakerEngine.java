package com.intkr.saas.util.dever;

import com.intkr.saas.util.db.Context;

public class MakerEngine {

	public static void maker() {
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
		JsMaker.maker();
	}

	public static void delete(String subDirectory, String tableName, String subPackage) {
		Context.init();
		MapperMaker.delete();
		DOMaker.delete();
		BOMaker.delete();
		DaoMaker.delete();
		DaoImplMaker.delete();
		ManagerMaker.delete();
		ManagerImplMaker.delete();
		ActionMaker.delete();

		MgrJavaMaker.delete();
		MgrVMMaker.delete();
		AddUpdateJavaMaker.delete();
		AddUpdateVMMaker.delete();
		JsMaker.delete();
	}

}
