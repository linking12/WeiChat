package net.chat.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "T_RESOURCES")
public class Resources implements Serializable {
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long resourceId;
	@Column(name = "url")
	private String url;
	@Column(name = "priority")
	private int priority;
	@Column(name = "type")
	private int type;
	@Column(name = "name")
	private String name;
	@ManyToMany(mappedBy = "resourcesList")
	private List<Roles> roleList;

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Roles> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Roles> roleList) {
		this.roleList = roleList;
	}

	@Override
	public String toString() {
		return "Resources [id=" + resourceId + ", url=" + url + ", priority=" + priority + ",type" + type + ",name" + name + "]";
	}

}
