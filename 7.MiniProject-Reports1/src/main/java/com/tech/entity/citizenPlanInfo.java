package com.tech.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="citizen_plan_info")
public class citizenPlanInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer CITIZEN_ID;
	private String NAME;
	private String EMAIL;
	private Integer PHNO;
	private String GENDER;
	private Integer SSN;
	private String PLAN_NAME;
	private String PLAN_STATUS;
	private LocalDate PLAN_START_DATE;
	private LocalDate PLAN_END_DATE;
	@CreationTimestamp
	@Column(updatable=false)
	private LocalDate CREATE_DATE;
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDate UPDATE_DATE;

}
