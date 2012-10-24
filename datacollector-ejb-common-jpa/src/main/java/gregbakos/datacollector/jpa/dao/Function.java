package gregbakos.datacollector.jpa.dao;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "T_FUNCTIONS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Function extends EntityBase{
	private static final long serialVersionUID = -3482520142445897832L;

	@Column(length=255)
	private String code;

	@Column(name="COMMENT_", length=255)
	private String comment;

	@Column(nullable=true, precision=1)
	private Long menuItem;

	@Column(length=255)
	private String menuLabel;

	@Column(length=255)
	private String name;

	@Transient
	private Boolean hasChild;

	//bi-directional many-to-one association to Function
    @ManyToOne
	@JoinColumn(name="ROOTFUNCTION_ID")
	private Function parentFunction;

	//bi-directional many-to-one association to Function
	@OneToMany(mappedBy="parentFunction")
	private List<Function> childFunctions;

	//bi-directional many-to-one association to RoleFunction
	@OneToMany(mappedBy="function")
	private List<RoleFunction> roleFunctions;

    public Function() {    	
    }

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getMenuItem() {
		return this.menuItem;
	}

	public void setMenuItem(Long menuItem) {
		this.menuItem = menuItem;
	}

	public String getMenuLabel() {
		return this.menuLabel;
	}

	public void setMenuLabel(String menuLabel) {
		this.menuLabel = menuLabel;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Function getParentFunction() {
		return this.parentFunction;
	}

	public void setParentFunction(Function parentFunction) {
		this.parentFunction = parentFunction;
	}
	
	public List<Function> getChildFunctions() {
		return this.childFunctions;
	}

	public void setChildFunctions(List<Function> childFunctions) {
		this.childFunctions = childFunctions;
	}
	
	public List<RoleFunction> getRoleFunctions() {
		return this.roleFunctions;
	}

	public void setRoleFunctions(List<RoleFunction> roleFunctions) {
		this.roleFunctions = roleFunctions;
	}
	
	public Boolean getHasChild() {
		return hasChild;
	}

	public void setHasChild(Boolean hasChild) {
		this.hasChild = hasChild;
	}
}
