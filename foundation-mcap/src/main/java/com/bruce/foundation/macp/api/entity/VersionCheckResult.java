package com.bruce.foundation.macp.api.entity;

public class VersionCheckResult {

	private int updateStatus;
	private String updateTitle;
	private String updateRemark;
	private String updateUrl;
	
	private String agreeText;
	private String deniedText;
	
	public VersionCheckResult() {
		super();
	}

	public VersionCheckResult(int updateStatus, String updateTitle,
			String updateRemark, String updateUrl, String agreeText, String deniedText) {
		super();
		this.updateStatus = updateStatus;
		this.updateTitle = updateTitle;
		this.updateRemark = updateRemark;
		this.updateUrl = updateUrl;
		this.agreeText = agreeText;
		this.deniedText = deniedText;
	}

	public int getUpdateStatus() {
		return updateStatus;
	}

	public void setUpdateStatus(int updateStatus) {
		this.updateStatus = updateStatus;
	}

	public String getUpdateTitle() {
		return updateTitle;
	}

	public void setUpdateTitle(String updateTitle) {
		this.updateTitle = updateTitle;
	}

	public String getUpdateRemark() {
		return updateRemark;
	}

	public void setUpdateRemark(String updateRemark) {
		this.updateRemark = updateRemark;
	}

	public String getUpdateUrl() {
		return updateUrl;
	}

	public void setUpdateUrl(String updateUrl) {
		this.updateUrl = updateUrl;
	}

	public String getAgreeText() {
		return agreeText;
	}

	public void setAgreeText(String agreeText) {
		this.agreeText = agreeText;
	}

	public String getDeniedText() {
		return deniedText;
	}

	public void setDeniedText(String deniedText) {
		this.deniedText = deniedText;
	}
	
	

}
