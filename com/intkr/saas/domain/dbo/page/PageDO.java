package com.intkr.saas.domain.dbo.page;

import java.util.Date;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 13:00:20
 * @version 1.0
 */
public class PageDO extends BaseDO {

	private Long saasId;

	// 类型
	private String type;

	// 形式
	private String form;

	// 作者
	private Long userId;

	// 标题
	private String title;

	// 状态
	private String status;

	// SEO 关键字
	private String keywords;

	// SEO 描述
	private String description;

	// 摘要
	private String summary;

	// 正文
	private String content;

	// 发布时间
	private Date publicTime;

	// 是否允许评论:2表示空
	private Integer allowComment;

	// 特色图片ID:2表示空
	private Long speImgId;

	// 浏览次数
	private Integer viewCount;

	// 评论次数
	private Integer commentCount;

	// 点赞次数
	private Integer praiseUpCount;

	// 点戳次数
	private Integer praiseDownCount;

	// 喜欢次数
	private Integer likeCount;

	// 收藏次数
	private Integer collectCount;

	// 关注次数
	private Integer attentionCount;

	// 拓展字段
	private String feature;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPublicTime() {
		return publicTime;
	}

	public void setPublicTime(Date publicTime) {
		this.publicTime = publicTime;
	}

	public Integer getAllowComment() {
		return allowComment;
	}

	public void setAllowComment(Integer allowComment) {
		this.allowComment = allowComment;
	}

	public Long getSpeImgId() {
		return speImgId;
	}

	public void setSpeImgId(Long speImgId) {
		this.speImgId = speImgId;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getPraiseUpCount() {
		return praiseUpCount;
	}

	public void setPraiseUpCount(Integer praiseUpCount) {
		this.praiseUpCount = praiseUpCount;
	}

	public Integer getPraiseDownCount() {
		return praiseDownCount;
	}

	public void setPraiseDownCount(Integer praiseDownCount) {
		this.praiseDownCount = praiseDownCount;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getCollectCount() {
		return collectCount;
	}

	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}

	public Integer getAttentionCount() {
		return attentionCount;
	}

	public void setAttentionCount(Integer attentionCount) {
		this.attentionCount = attentionCount;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

}