package com.bruce.foundation.model.share;

/**
 * 客户端通用分享对象
 * 
 * @author liqian
 * 
 */
public class GenericSharedInfo {

	private WxSharedInfo wxSharedInfo;

	private WeiboSharedInfo weiboSharedInfo;

	public GenericSharedInfo() {
		super();
	}

	public GenericSharedInfo(WxSharedInfo wxSharedInfo,
			WeiboSharedInfo weiboSharedInfo) {
		super();
		this.wxSharedInfo = wxSharedInfo;
		this.weiboSharedInfo = weiboSharedInfo;
	}

	public WxSharedInfo getWxSharedInfo() {
		return wxSharedInfo;
	}

	public void setWxSharedInfo(WxSharedInfo wxSharedInfo) {
		this.wxSharedInfo = wxSharedInfo;
	}

	public WeiboSharedInfo getWeiboSharedInfo() {
		return weiboSharedInfo;
	}

	public void setWeiboSharedInfo(WeiboSharedInfo weiboSharedInfo) {
		this.weiboSharedInfo = weiboSharedInfo;
	}

	public static class WxSharedInfo {
		private String title;
		private String content;
		private String iconUrl;
		private String link;

		public WxSharedInfo(String title, String content, String iconUrl,
				String link) {
			super();
			this.title = title;
			this.content = content;
			this.iconUrl = iconUrl;
			this.link = link;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getIconUrl() {
			return iconUrl;
		}

		public void setIconUrl(String iconUrl) {
			this.iconUrl = iconUrl;
		}

		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}

	}

	public static class WeiboSharedInfo {
		private String title;
		private String content;
		private String iconUrl;
		private String link;

		public WeiboSharedInfo(String title, String content, String iconUrl,
				String link) {
			super();
			this.title = title;
			this.content = content;
			this.iconUrl = iconUrl;
			this.link = link;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getIconUrl() {
			return iconUrl;
		}

		public void setIconUrl(String iconUrl) {
			this.iconUrl = iconUrl;
		}

		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}

	}
}
