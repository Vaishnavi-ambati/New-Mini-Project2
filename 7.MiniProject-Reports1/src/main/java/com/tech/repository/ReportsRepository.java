package com.tech.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tech.entity.citizenPlanInfo;

@Repository
public interface ReportsRepository extends JpaRepository<citizenPlanInfo, Integer>{

//	@Query(value="select * from citizen_plan_info where PLAN_NAME=:#{#planName} and PLAN_STATUS=:#{#planStatus} and GENDER=:#{#gender} "
//			+ "and PLAN_START_DATE=:#{#startDate} and PLAN_END_DATE=:#{#endDate}"
//			,nativeQuery=true)
//	public List<citizenPlanInfo> getRecordsByFilter(String planName, String planStatus, String gender, LocalDate startDate, LocalDate endDate);
//	
//	@Query(value="select * from citizen_plan_info", nativeQuery=true)
//	public List<citizenPlanInfo> getAllRecords();
	
	@Query(value="select distinct PLAN_NAME from citizen_plan_info", nativeQuery=true)
	public List<String> getUniquePlanNames();
	
	@Query(value="select distinct PLAN_STATUS from citizen_plan_info", nativeQuery=true)
	public List<String> getUniquePlanStatus();

}
