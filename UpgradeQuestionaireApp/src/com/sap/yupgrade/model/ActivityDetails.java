package com.sap.yupgrade.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ActivityDetails
 *
 */
@Entity
@Table(name="\"UpgradeActivityDetails\"")
public class ActivityDetails implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public ActivityDetails() {
		super();
	}
	@Id
	@Column(name = "\"ID\"", nullable = false, length = 10) 
	private Integer ID;
	
	@Column(name = "\"DESCRIPTION\"", nullable = false, length = 100) 
	private String name;
	
	@Column(name = "\"SDC_SHARE\"") 
	private int sdcShare;
	
	@Column(name = "\"ES_SHARE\"") 
	private int esShare;
	
	@Column(name = "\"ONSITE_SHARE\"") 
	private int onsiteShare;
	
	@Column(name = "\"TOTAL_EFFORT_INDEX\"") 
	private int indexOfEffort;
	
	@Column(name = "\"OVERLAP\"") 
	private int overlap;
	
	private int totalCalculatedEffort;
	private int totalCalculatedOnsiteEffort;
	private int totalCalculatedOffshoreEffort;
	
	

	public int getTotalCalculatedEffort() {
		return totalCalculatedEffort;
	}

	public void setTotalCalculatedEffort(int totalCalculatedEffort) {
		this.totalCalculatedEffort = totalCalculatedEffort;
	}

	public int getTotalCalculatedOnsiteEffort() {
		return totalCalculatedOnsiteEffort;
	}

	public void setTotalCalculatedOnsiteEffort(int totalCalculatedOnsiteEffort) {
		this.totalCalculatedOnsiteEffort = totalCalculatedOnsiteEffort;
	}

	public int getTotalCalculatedOffshoreEffort() {
		return totalCalculatedOffshoreEffort;
	}

	public void setTotalCalculatedOffshoreEffort(int totalCalculatedOffshoreEffort) {
		this.totalCalculatedOffshoreEffort = totalCalculatedOffshoreEffort;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public ActivityDetails(String name, int sdcShare, int esShare, int onsiteShare, int indexOfEffort,
			int overlap) {
		super();
		this.name = name;
		this.sdcShare = sdcShare;
		this.esShare = esShare;
		this.onsiteShare = onsiteShare;
		this.indexOfEffort = indexOfEffort;
		this.overlap = overlap;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSdcShare() {
		return sdcShare;
	}

	public void setSdcShare(int sdcShare) {
		this.sdcShare = sdcShare;
	}

	public int getEsShare() {
		return esShare;
	}

	public void setEsShare(int esShare) {
		this.esShare = esShare;
	}

	public int getOnsiteShare() {
		return onsiteShare;
	}

	public void setOnsiteShare(int onsiteShare) {
		this.onsiteShare = onsiteShare;
	}

	public int getIndexOfEffort() {
		return indexOfEffort;
	}

	public void setIndexOfEffort(int indexOfEffort) {
		this.indexOfEffort = indexOfEffort;
	}

	public int getOverlap() {
		return overlap;
	}

	public void setOverlap(int overlap) {
		this.overlap = overlap;
	}

	@Override
	public String toString() {
		return "ActivityDetails [ID=" + ID + ", name=" + name + ", sdcShare=" + sdcShare + ", esShare=" + esShare
				+ ", onsiteShare=" + onsiteShare + ", indexOfEffort=" + indexOfEffort + ", overlap=" + overlap
				+ ", totalCalculatedEffort=" + totalCalculatedEffort + ", totalCalculatedOnsiteEffort="
				+ totalCalculatedOnsiteEffort + ", totalCalculatedOffshoreEffort=" + totalCalculatedOffshoreEffort
				+ "]";
	}
	
	
}
