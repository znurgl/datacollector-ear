package gregbakos.datacollector.jpa.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_ROLE_FUNCTIONS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class RoleFunction  extends EntityBase{
	private static final long serialVersionUID = -3696885335844686903L;

	//bi-directional many-to-one association to Function
    @ManyToOne
	@JoinColumn(name="FUNCTION_ID")
	private Function function;

	//bi-directional many-to-one association to Role
    @ManyToOne
	@JoinColumn(name="ROLE_ID")
	private Role role;

    @Column(nullable=true, precision=1)
    private Boolean write;      
    
    public RoleFunction() {
    }

	public Function getFunction() {
		return this.function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}
	
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public void setWrite(Boolean write) {
		this.write = write;
	}

	public Boolean getWrite() {
		return write;
	}
}
