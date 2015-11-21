package childbreath.service;

public class MediaInfo {
		private long m_createTime;
		private String m_mediaId;
		private String m_title;
		private String m_description;
		public long getCreateTime() {
			return m_createTime;
		}
		public void setCreateTime(long createTime) {
			m_createTime = createTime;
		}
		public String getMediaId() {
			return m_mediaId;
		}
		public void setMediaId(String mediaId) {
			m_mediaId = mediaId;
		}
		public String getTitle() {
			return m_title;
		}
		public void setTitle(String title) {
			m_title = title;
		}
		public String getDescription() {
			return m_description;
		}
		public void setDescription(String description) {
			m_description = description;
		}
}
