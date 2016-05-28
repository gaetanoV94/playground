package ch.rasc.nosql.rethink;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public class Post {

	private String id;
	private long stackexchangeId;
	private long postTypeId;
	private long acceptedAnswerId;
	private OffsetDateTime  creationDate;
	private long stackexchangeScore;
	private long viewCount;
	private String body;
	private String title;
	private Float score;

	public Post() {
		this.id = UUID.randomUUID().toString();
	}

	public Float getScore() {
		return this.score;
	}

	private List<String> tags;

	public String getId() {
		return this.id;
	}

	public long getStackexchangeId() {
		return this.stackexchangeId;
	}

	public void setStackexchangeId(long stackexchangeId) {
		this.stackexchangeId = stackexchangeId;
	}

	public long getPostTypeId() {
		return this.postTypeId;
	}

	public void setPostTypeId(long postTypeId) {
		this.postTypeId = postTypeId;
	}

	public long getAcceptedAnswerId() {
		return this.acceptedAnswerId;
	}

	public void setAcceptedAnswerId(long acceptedAnswerId) {
		this.acceptedAnswerId = acceptedAnswerId;
	}

	public OffsetDateTime  getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(OffsetDateTime  creationDate) {
		this.creationDate = creationDate;
	}

	public long getStackexchangeScore() {
		return this.stackexchangeScore;
	}

	public void setStackexchangeScore(long stackexchangeScore) {
		this.stackexchangeScore = stackexchangeScore;
	}

	public long getViewCount() {
		return this.viewCount;
	}

	public void setViewCount(long viewCount) {
		this.viewCount = viewCount;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getTags() {
		return this.tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", stackexchangeId=" + stackexchangeId + ", postTypeId="
				+ postTypeId + ", acceptedAnswerId=" + acceptedAnswerId
				+ ", creationDate=" + creationDate + ", stackexchangeScore="
				+ stackexchangeScore + ", viewCount=" + viewCount + ", body=" + body
				+ ", title=" + title + ", score=" + score + ", tags=" + tags + "]";
	}


}
