package com.bruce.foundation.macp.api.entity;

public class VersionCheckResult {

	private int updateStatus;
	private String updateTitle;
	private String updateRemark;
	private String updateUrl;
	
	public VersionCheckResult() {
		super();
	}

	public VersionCheckResult(int updateStatus, String updateTitle,
			String updateRemark, String updateUrl) {
		super();
		this.updateStatus = updateStatus;
		this.updateTitle = updateTitle;
		this.updateRemark = updateRemark;
		this.updateUrl = updateUrl;
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

}
