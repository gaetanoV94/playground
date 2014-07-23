package ch.rasc.mongodb.author.morphia;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;

@Entity(noClassnameStored = true)
@Indexes({ @Index("word1,word2") })
public class Word12 {

	@Id
	private ObjectId id;

	private String word1;

	private String word2;

	private int count;

	private List<Word3> word3;

	public ObjectId getId() {
		return id;
	}

	public String getWord1() {
		return word1;
	}

	public void setWord1(String word1) {
		this.word1 = word1;
	}

	public String getWord2() {
		return word2;
	}

	public void setWord2(String word2) {
		this.word2 = word2;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Word3> getWord3() {
		return word3;
	}

	public void setWord3(List<Word3> word3) {
		this.word3 = word3;
	}

}
