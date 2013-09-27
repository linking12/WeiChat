package net.chat.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "T_ROLES")
public class Roles implements Serializable {
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long roleId;

	@Column(name = "enable")
	private Long enable;

	@Column(name = "name")
	private String name;

	@ManyToMany(mappedBy = "roleList")
	private List<User> userList;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "T_ROLE_RESOURCES", joinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "resource_id", referencedColumnName = "id", nullable = false, updatable = false) })
	private Set<Resources> resourcesList;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getEnable() {
		return enable;
	}

	public void setEnable(Long enable) {
		this.enable = enable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public Set<Resources> getResourcesList() {
		return resourcesList;
	}

	public void setResourcesList(Set<Resources> resourcesList) {
		this.resourcesList = resourcesList;
	}

	@Override
	public String toString() {
		return "Roles [id=" + roleId + ", enable=" + enable + ", name=" + name
				+ "]";
	}
}
