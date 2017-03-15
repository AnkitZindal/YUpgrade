package com.sap.yupgrade.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ResonseMaster
 *
 */
@Entity

public class ResonseMaster implements Serializable {

	   
	@Id
	private Integer RESP_ID;
	
	@ManyToOne
	private QuestionMaster QUESTION;
	
	private String DESC;
	private String COMLEXITY;
	private static final long serialVersionUID = 1L;


	public ResonseMaster() {
		super();
	}   
	public Integer getRESP_ID() {
		return this.RESP_ID;
	}

	public void setRESP_ID(Integer RESP_ID) {
		this.RESP_ID = RESP_ID;
	}   
	 
	public QuestionMaster getQUESTION() {
		return QUESTION;
	}
	public void setQUESTION(QuestionMaster qUESTION) {
		QUESTION = qUESTION;
	}
	public String getDESC() {
		return this.DESC;
	}

	public void setDESC(String DESC) {
		this.DESC = DESC;
	}   
	public String getCOMLEXITY() {
		return this.COMLEXITY;
	}

	public void setCOMLEXITY(String COMLEXITY) {
		this.COMLEXITY = COMLEXITY;
	}
   
}
