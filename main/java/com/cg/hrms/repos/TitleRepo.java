package com.cg.hrms.repos;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.cg.hrms.entities.Title;
import com.cg.hrms.utility.TitleCompositeKey;

@Repository
public interface TitleRepo extends JpaRepository<Title, TitleCompositeKey> {
	@NonNull
	Optional<Title> findById(@NonNull TitleCompositeKey id);

	@Query("SELECT t FROM Title t WHERE t.id.fromDate = :fromDate")
	List<Title> findByFromDate(Date fromDate);

	@Query("SELECT t FROM Title t WHERE t.id.empNo = :empNo AND t.id.fromDate = :fromDate AND t.id.title = :title")
	Title findByEmpNoAndFromDateAndTitle(int empNo, Date fromDate, String title);

	List<Title> findByIdTitle(String title);

	@Query("SELECT t FROM Title t WHERE t.id.title = :title AND t.id.fromDate = :fromDate")
	List<Title> findByTitleAndFromDate(String title, Date fromDate);

	@Query("SELECT t FROM Title t WHERE t.id.empNo = :empNo AND t.id.fromDate = :fromDate")
	Page<Title> findByEmpNoAndFromDate(Pageable pageable, int empNo, Date fromDate);

	@Query("SELECT t FROM Title t WHERE t.id.empNo = :empNo AND t.id.title = :title")
	Page<Title> findByEmpNoAndTitle(Pageable pageable, int empNo, String title);

	@Query("SELECT t FROM Title t WHERE t.id.empNo = :empNo AND t.id.fromDate = :fromDate")
	List<Title> findByEmpNoAndFromDate(int empNo, Date fromDate);

	@Query("SELECT t FROM Title t WHERE t.id.empNo = :empNo AND t.id.title = :title")
	List<Title> findByEmpNoAndTitle(int empNo, String title);

	@Query("SELECT t FROM Title t WHERE t.id.empNo = :empNo")
	Page<Title> findAllByEmpNo(Pageable pageable, @Param("empNo") int empNo);

	@Query("SELECT t FROM Title t WHERE t.id.fromDate = :fromDate")
	Page<Title> findAllByFromDate(Pageable pageable, Date fromDate);

	@Query("SELECT t FROM Title t WHERE t.id.title = :title")
	Page<Title> findAllByTitle(Pageable pageable, String title);

	@Query("SELECT t FROM Title t WHERE t.id.empNo = :empNo")
	List<Title> findByEmpNo( @Param("empNo") int empNo);
	
}
