package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.annotation.CodeGroupReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_CUSTOMER_ATTRIBUTES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class CustomerAttribute extends EntityBase {
	private static final long serialVersionUID = -8648948458033112489L;

	@Column(length = 300)
	private String customerAttrib;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Customer customer;

	@ManyToOne
	@CodeGroupReference(CodeGroupCode = "CUSTATTRIB")
	@JoinColumn(nullable = false)
	private Code customerAttribType;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Code getCustomerAttribType() {
		return customerAttribType;
	}

	public void setCustomerAttribType(Code customerAttribType) {
		this.customerAttribType = customerAttribType;
	}

	public CustomerAttribute() {
		super();
	}

	public String getCustomerAttrib() {
		return customerAttrib;
	}

	public void setCustomerAttrib(String customerAttrib) {
		this.customerAttrib = customerAttrib;
	}

}
