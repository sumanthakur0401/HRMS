package com.cg.hrms.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hrms.dtos.TitleDTO;
import com.cg.hrms.entities.Title;
import com.cg.hrms.services.serviceinterface.TitleService;

@RestController
@RequestMapping("/api/v1/titles")
public class TitleController {
	
	@Autowired
	private TitleService titleService;

	 @PostMapping("/add")
	    public ResponseEntity<String> addTitle(@RequestBody TitleDTO titleDto) {
	        Title t = titleDto.convertToTitle();
	        titleService.saveTitle(t);
	        return ResponseEntity.status(HttpStatus.CREATED).body("New title added Successfully");
	    }

	    @GetMapping("/page/{page}/size/{size}")
	    public ResponseEntity<Page<TitleDTO>> getAllTitles(@PathVariable int page, @PathVariable int size) {
	        Pageable pageable = PageRequest.of(page, size);
	        Page<TitleDTO> titles = titleService.getAllTitles(pageable);
	        return ResponseEntity.ok(titles);
	    }

	    @GetMapping("/fromdate/{fromdate}/page/{page}/size/{size}")
	    public ResponseEntity<Page<TitleDTO>> getTitlesByFromDate(
	            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate,
	            @PathVariable int page, @PathVariable int size) {
	        Pageable pageable = PageRequest.of(page, size);
	        Page<Title> titles = titleService.getTitlesByFromDate(pageable, fromDate);
	        Page<TitleDTO> titleDTOs = titles.map(Title::convertToDTO);
	        return ResponseEntity.ok(titleDTOs);
	    }

	    @GetMapping("/title/{title}/page/{page}/size/{size}")
	    public ResponseEntity<Page<TitleDTO>> getTitlesByTitle(
	            @PathVariable String title, @PathVariable int page, @PathVariable int size) {
	        Pageable pageable = PageRequest.of(page, size);
	        Page<Title> titles = titleService.getTitlesByTitle(pageable, title);
	        return ResponseEntity.ok(titles.map(Title::convertToDTO));
	    }

	    @GetMapping("/empno/{empNo}/fromdate/{fromDate}/page/{page}/size/{size}")
	    public ResponseEntity<Page<TitleDTO>> getTitlesByEmpNoAndFromDate(@PathVariable int empNo,
	            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate,
	            @PathVariable int page, @PathVariable int size) {
	        Pageable pageable = PageRequest.of(page, size);
	        Page<Title> titles = titleService.getTitlesByEmpNoAndFromDate(pageable, empNo, fromDate);
	        return ResponseEntity.ok(titles.map(Title::convertToDTO));
	    }

	    @GetMapping("/empno/{empNo}/title/{title}/page/{page}/size/{size}")
	    public ResponseEntity<Page<TitleDTO>> getTitlesByEmpNoAndTitle(
	            @PathVariable int empNo, @PathVariable String title, @PathVariable int page, @PathVariable int size) {
	        Pageable pageable = PageRequest.of(page, size);
	        Page<Title> titles = titleService.getTitlesByEmpNoAndTitle(pageable, empNo, title);
	        return ResponseEntity.ok(titles.map(Title::convertToDTO));
	    }

	    @PutMapping("/fromdate/{fromDate}")
	    public ResponseEntity<String> updateTitlesByFromDate(
	            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate,
	            @RequestBody TitleDTO updatedTitle) {
	        if (fromDate == null || updatedTitle == null) {
	            return ResponseEntity.badRequest().body("Date and updatedTitle cannot be null");
	        }
	        titleService.updateTitlesByFromDate(fromDate, updatedTitle.convertToTitle());
	        return ResponseEntity.ok("Titles updated Successfully");
	    }

	    @PutMapping("/title/{title}")
	    public ResponseEntity<String> updateTitlesByTitle(@PathVariable String title, @RequestBody TitleDTO updatedTitle) {
	        if (title == null || updatedTitle == null) {
	            return ResponseEntity.badRequest().body("Title and updatedTitle cannot be null");
	        }
	        titleService.updateTitlesByTitle(title, updatedTitle.convertToTitle());
	        return ResponseEntity.ok("Titles updated Successfully");
	    }

	    @PutMapping("/empno/{empNo}")
	    public ResponseEntity<String> updateTitlesByEmpNo(@PathVariable int empNo, @RequestBody TitleDTO updatedTitle) {
	        if (updatedTitle == null) {
	            return ResponseEntity.badRequest().body("UpdatedTitle cannot be null");
	        }
	        titleService.updateTitlesByEmpNo(empNo, updatedTitle.convertToTitle());
	        return ResponseEntity.ok("Titles updated Successfully");
	    }

	    @PutMapping("/empno/{empNo}/fromdate/{fromDate}/title/{title}")
	    public ResponseEntity<String> updateTitleByEmpNoAndFromDateAndTitle(@PathVariable int empNo,
	            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate, @PathVariable String title,
	            @RequestBody TitleDTO updatedTitle) {
	        if (fromDate == null || title == null || updatedTitle == null) {
	            return ResponseEntity.badRequest().body("Date, title, and updatedTitle cannot be null");
	        }
	        titleService.updateTitleByEmpNoAndFromDateAndTitle(empNo, fromDate, title, updatedTitle.convertToTitle());
	        return ResponseEntity.ok("Title updated Successfully");
	    }

	    @DeleteMapping("/empno/{empNo}/fromdate/{fromDate}/title/{title}")
	    public ResponseEntity<String> deleteTitleByEmpNoAndFromDateAndTitle(@PathVariable int empNo,
	            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate, @PathVariable String title) {
	        if (fromDate == null || title == null) {
	            return ResponseEntity.badRequest().body("Date and title cannot be null");
	        }
	        titleService.deleteTitleByEmpNoAndFromDateAndTitle(empNo, fromDate, title);
	        return ResponseEntity.ok("Title deleted Successfully");
	    }

	    @DeleteMapping("/empno/{empNo}")
	    public ResponseEntity<String> deleteTitlesByEmpNo(@PathVariable int empNo) {
	        titleService.deleteTitlesByEmpNo(empNo);
	        return ResponseEntity.ok("Titles deleted Successfully");
	    }

	    @DeleteMapping("/fromdate/{fromDate}")
	    public ResponseEntity<String> deleteTitlesByFromDate(
	            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate) {
	        if (fromDate == null) {
	            return ResponseEntity.badRequest().body("Date cannot be null");
	        }
	        titleService.deleteTitlesByFromDate(fromDate);
	        return ResponseEntity.ok("Titles deleted Successfully");
	    }

	    @DeleteMapping("/title/{title}")
	    public ResponseEntity<String> deleteTitlesByTitle(@PathVariable String title) {
	        if (title == null) {
	            return ResponseEntity.badRequest().body("Title cannot be null");
	        }
	        titleService.deleteTitlesByTitle(title);
	        return ResponseEntity.ok("Titles deleted Successfully");
	    }

}
