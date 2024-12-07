package com.cg.hrms.services.serviceimplementation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cg.hrms.dtos.TitleDTO;
import com.cg.hrms.entities.Employee;
import com.cg.hrms.entities.Title;
import com.cg.hrms.exceptions.EmployeeNotFoundException;
import com.cg.hrms.exceptions.ResourceNotFoundException;
import com.cg.hrms.repos.DepartmentRepo;
import com.cg.hrms.repos.DeptEmpRepo;
import com.cg.hrms.repos.EmployeeRepo;
import com.cg.hrms.repos.TitleRepo;
import com.cg.hrms.services.serviceinterface.TitleService;
import com.cg.hrms.utility.TitleCompositeKey;

@Service
public class TitleServiceImpl implements TitleService {

	@Autowired
	private EmployeeRepo empRepo;

	@Autowired
	private DepartmentRepo deptRepo;

	@Autowired
	private DeptEmpRepo deptEmpRepo;

	@Autowired
	private TitleRepo titleRepository;

	@Override
	public void saveTitle(Title title) {
		Employee emp = empRepo.findById(title.getEmpNo()).orElseThrow(
				() -> new EmployeeNotFoundException("Employee with ID " + title.getEmpNo() + " not found"));

		title.setEmployee(emp);
		titleRepository.save(title);
	}

	@Override
	public Page<TitleDTO> getAllTitles(Pageable pageable) {
		Page<Title> titles = titleRepository.findAll(pageable);

		return titles.map(Title::convertToDTO);
	}

	@Override
	public Title getTitleByEmpNoFromDateAndTitle(int empNo, Date fromDate, String title) {
		TitleCompositeKey key = new TitleCompositeKey(empNo, title, fromDate);
		return titleRepository.findById(key).orElseThrow(() -> new ResourceNotFoundException("Title not found"));
	}

	@Override
	public List<Title> getTitlesByFromDate(Date fromDate) {
		return titleRepository.findByFromDate(fromDate);
	}

//working on

	@Override
	public List<Title> getTitlesByTitle(String title) {
		return titleRepository.findByIdTitle(title);
	}

	@Override
	public List<Title> getTitlesByTitleAndFromDate(String title, Date fromDate) {
		return titleRepository.findByTitleAndFromDate(title, fromDate);
	}

	@Override
	public Page<Title> getTitlesByEmpNoAndFromDate(Pageable pageable, int empNo, Date fromDate) {
		return titleRepository.findByEmpNoAndFromDate(pageable, empNo, fromDate);
	}

	@Override
	public Page<Title> getTitlesByEmpNoAndTitle(Pageable pageable, int empNo, String title) {
		return titleRepository.findByEmpNoAndTitle(pageable, empNo, title);
	}

	@Override
	public Page<Title> getTitlesByEmpNo(Pageable pageable, int empNo) {
		return titleRepository.findAllByEmpNo(pageable, empNo);
	}

	@Override
	public Page<Title> getTitlesByFromDate(Pageable pageable, Date fromDate) {
		return titleRepository.findAllByFromDate(pageable, fromDate);
	}

	@Override
	public Page<Title> getTitlesByTitle(Pageable pageable, String title) {
		return titleRepository.findAllByTitle(pageable, title);
	}

	@Override
	public void updateTitlesByFromDate(Date fromDate, Title updatedTitle) {
		if (fromDate == null || updatedTitle == null) {
			throw new IllegalArgumentException("Date and updatedTitle cannot be null");
		}
		List<Title> titles = titleRepository.findByFromDate(fromDate);
		if (titles.isEmpty()) {
			throw new RuntimeException("No titles found for the given fromDate");
		}
		for (Title title : titles) {
			title.setToDate(updatedTitle.getToDate());
			// Set other fields if needed
			titleRepository.save(title);
		}
	}

	@Override
	public void updateTitlesByTitle(String title, Title updatedTitle) {
		if (title == null || updatedTitle == null) {
			throw new IllegalArgumentException("Title and updatedTitle cannot be null");
		}
		List<Title> titles = titleRepository.findByIdTitle(title);
		if (titles.isEmpty()) {
			throw new RuntimeException("No titles found for the given title");
		}
		for (Title titleObj : titles) {
			titleObj.setToDate(updatedTitle.getToDate());
			// Set other fields if needed
			titleRepository.save(titleObj);
		}
	}

	@Override
	public void updateTitlesByEmpNo(int empNo, Title updatedTitle) {
		if (updatedTitle == null) {
			throw new IllegalArgumentException("UpdatedTitle cannot be null");
		}
		List<Title> titles = titleRepository.findByEmpNoAndTitle(empNo, updatedTitle.getTitle());
		if (titles.isEmpty()) {
			throw new RuntimeException("No titles found for the given empNo and title");
		}
		for (Title title : titles) {
			title.setToDate(updatedTitle.getToDate());
			titleRepository.save(title);
		}
	}

	@Override
	public void updateTitleByEmpNoAndFromDateAndTitle(int empNo, Date fromDate, String title, Title updatedTitle) {
		if (fromDate == null || title == null || updatedTitle == null) {
			throw new IllegalArgumentException("Date, title, and updatedTitle cannot be null");
		}
		Title titleObj = titleRepository.findByEmpNoAndFromDateAndTitle(empNo, fromDate, title);
		if (titleObj == null) {
			throw new RuntimeException("Title not found for the given empNo, fromDate, and title");
		}

		titleRepository.save(updatedTitle);
	}

	@Override
	public void deleteTitleByEmpNoAndFromDateAndTitle(int empNo, Date fromDate, String title) {
		if (fromDate == null || title == null) {
			throw new IllegalArgumentException("Date and title cannot be null");
		}
		Title titleObj = titleRepository.findByEmpNoAndFromDateAndTitle(empNo, fromDate, title);
		if (titleObj != null) {
			titleRepository.delete(titleObj);
		} else {
			throw new RuntimeException("Title not found for the given empNo, fromDate, and title");
		}
	}

	@Override
	public void deleteTitlesByEmpNo(int empNo) {
		List<Title> titles = titleRepository.findByEmpNo(empNo);
		if (titles.isEmpty()) {
			throw new RuntimeException("No titles found for the given empNo");
		}
		titleRepository.deleteAll(titles);
	}

	@Override
	public void deleteTitlesByFromDate(Date fromDate) {
		if (fromDate == null) {
			throw new IllegalArgumentException("Date cannot be null");
		}
		List<Title> titles = titleRepository.findByFromDate(fromDate);
		if (titles.isEmpty()) {
			throw new RuntimeException("No titles found for the given fromDate");
		}
		titleRepository.deleteAll(titles);
	}

	@Override
	public void deleteTitlesByTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null");
		}
		List<Title> titles = titleRepository.findByIdTitle(title);
		if (titles.isEmpty()) {
			throw new RuntimeException("No titles found for the given title");
		}
		titleRepository.deleteAll(titles);
	}

	@Override
    public List<Title> findTitlesByTitle(String title) {
        return titleRepository.findByIdTitle(title);
    }
}
