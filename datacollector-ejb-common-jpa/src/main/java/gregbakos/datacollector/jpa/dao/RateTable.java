package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.annotation.TranslatableAttribute;
import gregbakos.datacollector.jpa.utils.ProviderCheckEntityListener;
import gregbakos.datacollector.jpa.utils.TranslatorEntityListener;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "T_RATE_TABLES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ TranslatorEntityListener.class,
		ProviderCheckEntityListener.class })
public class RateTable extends EntityBase {
	private static final long serialVersionUID = 1982307808299705881L;

	@Column(nullable = false, length = 100)
	private String name;

	@Transient
	@TranslatableAttribute(attribute = "name")
	private String i18nName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Calendar startingDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar endingDate;

	@Column(name = "COMMENT_", length = 4000)
	private String comment;

	@Transient
	@TranslatableAttribute(attribute = "comment")
	private String i18nComment;

	// bi-directional many-to-one association to Provider
	@ManyToOne
	@JoinColumn(nullable = false)
	private Provider provider;

	// bi-directional many-to-one association to ProvisionType
	@ManyToOne
	@JoinColumn(nullable = false)
	private ProvisionType provisionType;

	// bi-directional many-to-one association to Provision
	@OneToMany(mappedBy = "rateTable")
	private Set<Provision> provisions;

	// bi-directional many-to-one association to RateItem
	@OneToMany(mappedBy = "rateTable")
	private Set<RateItem> rateItems;

	public RateTable() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getI18nName() {
		return i18nName;
	}

	public void setI18nName(String i18nName) {
		this.i18nName = i18nName;
	}

	public Calendar getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(Calendar startingDate) {
		this.startingDate = startingDate;
	}

	public Calendar getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(Calendar endingDate) {
		this.endingDate = endingDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getI18nComment() {
		return i18nComment;
	}

	public void setI18nComment(String i18nComment) {
		this.i18nComment = i18nComment;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public ProvisionType getProvisionType() {
		return provisionType;
	}

	public void setProvisionType(ProvisionType provisionType) {
		this.provisionType = provisionType;
	}

	public Set<Provision> getProvisions() {
		return provisions;
	}

	public void setProvisions(Set<Provision> provisions) {
		this.provisions = provisions;
	}

	public Set<RateItem> getRateItems() {
		return rateItems;
	}

	public void setRateItems(Set<RateItem> rateItems) {
		this.rateItems = rateItems;
	}
}