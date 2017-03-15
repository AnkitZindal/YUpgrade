package com.sap.yupgrade.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: QuestionMaster
 *
 */
@Entity
@Table(name="\"QuestionMaster\"")
public class QuestionMaster implements Serializable {
	   
	@Id
	@Column(name = "\"QUES_ID\"", nullable = false, length = 10) 
	private Integer QUES_ID;
	
	@Column(name = "\"DESCRIPTION\"", nullable = false, length = 100) 
	private String DESCRIPTION;
	
	@Column(name = "\"ACTIVE\"", nullable = false) 
	private boolean ACTIVE;
	private static final long serialVersionUID = 1L;
	
	@OneToMany
	private List<ResonseMaster> RESPONSES;

	public QuestionMaster() {
		super();
	}   
	public Integer getQUES_ID() {
		return this.QUES_ID;
	}

	public void setQUES_ID(Integer QUES_ID) {
		this.QUES_ID = QUES_ID;
	}   
	public String getDESCRIPTION() {
		return this.DESCRIPTION;
	}

	public List<ResonseMaster> getRESPONSES() {
		return RESPONSES;
	}
	public void setRESPONSES(List<ResonseMaster> rESPONSES) {
		RESPONSES = rESPONSES;
	}
	public void setDESCRIPTION(String DESC) {
		this.DESCRIPTION = DESC;
	}   
	public boolean getACTIVE() {
		return this.ACTIVE;
	}

	public void setACTIVE(boolean ACTIVE) {
		this.ACTIVE = ACTIVE;
	}
   
}
