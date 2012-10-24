package gregbakos.datacollector.jpa.dao;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_CONTROL_SCRIPTS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ControlScript extends EntityBase {
	private static final long serialVersionUID = -2009005582324654432L;

	@Column(length = 100)
	private String name;

	@Column(length = 20)
	private String versionNumber;

	@Column(length = 4000)
	private String description;

	@Lob()
	@Column(name = "SCRIPT_COMMAND")
	private String scriptCommand;

	// bi-directional many-to-one association to TaskType
	@OneToMany(mappedBy = "controlScript")
	private List<TaskType> TaskTypes;

	public ControlScript() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getScriptCommand() {
		return scriptCommand;
	}

	public void setScriptCommand(String scriptCommand) {
		this.scriptCommand = scriptCommand;
	}

	public List<TaskType> getTaskTypes() {
		return TaskTypes;
	}

	public void setTaskTypes(List<TaskType> taskTypes) {
		TaskTypes = taskTypes;
	}
}