package com.spring.boot.cricketLeage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="teams")
public class Teams {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="teamid")
	private Long teamId;
	@Column(name="teamname")
	private String teamName;
	@Column(name="captain")
	private String captain;
	@Column(name="city")
	private String city;
	
	public Teams() {}
	
	public Teams(String teamName, String captain, String city) {
		super();
		this.teamName = teamName;
		this.captain = captain;
		this.city = city;
	}
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getCaptain() {
		return captain;
	}
	public void setCaptain(String captain) {
		this.captain = captain;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return String.format("Teams [teamId=%s, teamName=%s, captain=%s, city=%s]", teamId, teamName, captain, city);
	}

	
}
