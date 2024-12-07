package com.cg.hrms.services.serviceinterface;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cg.hrms.dtos.TitleDTO;
import com.cg.hrms.entities.Title;

@Service
public interface TitleService {
	void saveTitle(Title title);

	Page<TitleDTO> getAllTitles(Pageable pageable);

	Title getTitleByEmpNoFromDateAndTitle(int empNo, Date fromDate, String title);

	List<Title> getTitlesByFromDate(Date fromDate);

	// work in progress
	List<Title> getTitlesByTitle(String title);

	List<Title> getTitlesByTitleAndFromDate(String title, Date fromDate);

	Page<Title> getTitlesByEmpNoAndFromDate(Pageable pageable,int empNo, Date fromDate);

	Page<Title> getTitlesByEmpNoAndTitle(Pageable pageable, int empNo, String title);

	Page<Title> getTitlesByEmpNo(Pageable pageable, int empNo);

	Page<Title> getTitlesByFromDate(Pageable pageable, Date fromDate);

	Page<Title> getTitlesByTitle(Pageable pageable, String title);

	void updateTitlesByFromDate(Date fromDate, Title updatedTitle);

	void updateTitlesByTitle(String title, Title updatedTitle);

	void updateTitlesByEmpNo(int empNo, Title updatedTitle);

	void updateTitleByEmpNoAndFromDateAndTitle(int empNo, Date fromDate, String title, Title updatedTitle);

	void deleteTitleByEmpNoAndFromDateAndTitle(int empNo, Date fromDate, String title);

	void deleteTitlesByEmpNo(int empNo);

	void deleteTitlesByFromDate(Date fromDate);

	void deleteTitlesByTitle(String title);
	
	List<Title> findTitlesByTitle(String title);

}
