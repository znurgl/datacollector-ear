package gregbakos.datacollector.jpa.dao;

import gregbakos.datacollector.jpa.utils.ConstraintListener;
import gregbakos.datacollector.jpa.utils.DeleteConstraintCheckEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.openswing.swing.message.receive.java.ValueObject;

@MappedSuperclass
@EntityListeners({ DeleteConstraintCheckEntityListener.class,
		ConstraintListener.class })
public class EntityBase implements ValueObject {
	private static final long serialVersionUID = -3207029880583858381L;

	@Id
	@GeneratedValue(generator = "ID_SEQUENCE")
	private Long id;

	@Version
	private Long lockVersion;

	public final Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public final Long getLockVersion() {
		return lockVersion;
	}

	public final void setLockVersion(Long lockVersion) {
		this.lockVersion = lockVersion;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (!this.getClass().isInstance(o)) {
			return false;
		} else {
			return getId().equals(((EntityBase) o).getId());
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public int hashCode() {
		return getId() == null ? -1 : getId().intValue();
	}

}
