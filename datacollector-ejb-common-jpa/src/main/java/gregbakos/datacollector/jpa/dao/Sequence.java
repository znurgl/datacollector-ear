package gregbakos.datacollector.jpa.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_SEQUENCES")
public class Sequence implements Serializable {
	private static final long serialVersionUID = -6766192480287853855L;

	@Id
	@Column(length = 100)
	private String seq_name;

	@Column(nullable = false)
	private Long seq_count;

	public Sequence() {
		super();
	}

	public String getSeq_name() {
		return this.seq_name;
	}

	public void setSeq_name(String seq_name) {
		this.seq_name = seq_name;
	}

	public Long getSeq_count() {
		return this.seq_count;
	}

	public void setSeq_count(Long seq_count) {
		this.seq_count = seq_count;
	}

}
