package gregbakos.datacollector.jpa.dao;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_CUSTOMERGROUPINGS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class CustomerGrouping extends EntityBase {
	private static final long serialVersionUID = 7065834904323831459L;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Customer customer;

	@ManyToOne
	@JoinColumn(nullable = true)
	private CustomerGroup customerGroup;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CustomerGroup getCustomerGroup() {
		return customerGroup;
	}

	public void setCustomerGroup(CustomerGroup customerGroup) {
		this.customerGroup = customerGroup;
	}

	public CustomerGrouping() {
		super();
	}

}
