package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.utils.ProviderCheckEntityListener;

import java.util.Calendar;
import java.util.Date;
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
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "T_CONTRACTS", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"CONTRACTNUMBER", "PROVIDER_ID" }) })
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ ProviderCheckEntityListener.class })
public class Contract extends EntityBase {
	private static final long serialVersionUID = 695307454916583935L;

	@Column(nullable = false, length = 100)
	private String contractNumber;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar endingDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Calendar startingDate;

	// bi-directional many-to-one association to Provider
	@ManyToOne
	@JoinColumn(nullable = false)
	private Provider provider;

	// bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(nullable = false)
	private Customer customer;

	// bi-directional many-to-one association to Provision
	@OneToMany(mappedBy = "contract")
	private Set<Provision> provisions;

	public Contract() {
		super();
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public Calendar getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(Calendar endingDate) {
		this.endingDate = endingDate;
	}

	public Calendar getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(Calendar startingDate) {
		this.startingDate = startingDate;
	}

	public Date getStartingDateDate() {
		return startingDate == null ? null : startingDate.getTime();
	}

	public Date getEndingDateDate() {
		return endingDate == null ? null : endingDate.getTime();
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<Provision> getProvisions() {
		return provisions;
	}

	public void setProvisions(Set<Provision> provisions) {
		this.provisions = provisions;
	}
}