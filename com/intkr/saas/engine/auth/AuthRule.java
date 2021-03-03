package com.intkr.saas.engine.auth;

import java.util.List;

/**
 * 
 * @author beidenhuang
 */
public class AuthRule {

	// anon,authc,perms,perms["right"],roles["role"],permsa["right"],rolesa["role"]
	private String type;
	private String action;
	private String uri;
	private String conf;
	private String falseConf;
	private List<String> perms;
	private List<String> roles;
	private List<String> permsa;
	private List<String> rolesa;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getConf() {
		return conf;
	}

	public void setConf(String conf) {
		this.conf = conf;
	}

	public List<String> getPerms() {
		return perms;
	}

	public void setPerms(List<String> perms) {
		this.perms = perms;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<String> getPermsa() {
		return permsa;
	}

	public void setPermsa(List<String> permsa) {
		this.permsa = permsa;
	}

	public List<String> getRolesa() {
		return rolesa;
	}

	public void setRolesa(List<String> rolesa) {
		this.rolesa = rolesa;
	}

	public String getFalseConf() {
		return falseConf;
	}

	public void setFalseConf(String falseConf) {
		this.falseConf = falseConf;
	}

}
