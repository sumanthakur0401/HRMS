package com.cg.hrms.services.serviceimplementation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hrms.entities.DeptManager;
import com.cg.hrms.exceptions.ResourceNotFoundException;
import com.cg.hrms.repos.DeptManagerRepo;
import com.cg.hrms.services.serviceinterface.DeptManagerService;
import com.cg.hrms.utility.DeptManagerCompositeKey;

@Service
public class DeptManagerServiceImp implements DeptManagerService {

    @Autowired
    private DeptManagerRepo deptManagerRepo;

    @Override
    public DeptManager addDeptManager(DeptManager deptManager) {
        return deptManagerRepo.save(deptManager);
    }

    @Override
    public List<DeptManager> getAllDeptManagers() {
        return deptManagerRepo.findAll();
    }

    @Override
    public DeptManager findDeptManagerByEmpNoAndDeptNo(int empNo, String deptNo) {
        return deptManagerRepo.findById(new DeptManagerCompositeKey(empNo, deptNo)).orElse(null);
    }

    @Override
    public List<DeptManager> findAllDeptManagersByDeptNoAndFromDate(String deptNo, Date fromDate) {
        return deptManagerRepo.findAllByIdDeptNoAndFromDate(deptNo, fromDate);
    }

    @Override
    public List<DeptManager> findAllDeptManagersByEmpNoAndFromDate(int empNo, Date fromDate) {
        return deptManagerRepo.findAllByIdEmpNoAndFromDate(empNo, fromDate);
    }

    @Override
    public List<DeptManager> findAllDeptManagersByEmpNoDeptNoAndFromDate(int empNo, String deptNo, Date fromDate) {
        DeptManager deptManager = deptManagerRepo.findByIdAndFromDate(new DeptManagerCompositeKey(empNo, deptNo), fromDate);
        if (deptManager != null) {
            return List.of(deptManager); // Return a list with a single element
        }
        return List.of(); // Return an empty list if no matching record is found
    }

    @Override
    public DeptManager updateDeptManagerByEmpNoAndDeptNo(int empNo, String deptNo, DeptManager deptManager) {
        DeptManager existingDeptManager = deptManagerRepo.findById(new DeptManagerCompositeKey(empNo, deptNo)).orElse(null);
        if (existingDeptManager != null) {
            existingDeptManager.setFromDate(deptManager.getFromDate());
            existingDeptManager.setToDate(deptManager.getToDate());
            return deptManagerRepo.save(existingDeptManager);
        }
        return null;
    }

    @Override
    public DeptManager updateDeptManagerByEmpNoAndFromDate(int empNo, Date fromDate, DeptManager deptManager) {
        DeptManager existingDeptManager = deptManagerRepo.findByIdAndFromDate(new DeptManagerCompositeKey(empNo, deptManager.getId().getDeptNo()), fromDate);
        if (existingDeptManager != null) {
            existingDeptManager.setToDate(deptManager.getToDate());
            return deptManagerRepo.save(existingDeptManager);
        }
        return null;
    }

    @Override
    public List<DeptManager> updateDeptManagerByDeptNoAndFromDate(String deptNo, Date fromDate, DeptManager deptManager) {
        List<DeptManager> existingDeptManagers = deptManagerRepo.findAllByIdDeptNoAndFromDate(deptNo, fromDate);
        for (DeptManager existingDeptManager : existingDeptManagers) {
            existingDeptManager.setToDate(deptManager.getToDate());
            deptManagerRepo.save(existingDeptManager);
        }
        return existingDeptManagers;
    }

    @Override
    public DeptManager updateDeptManagerByEmpNoDeptNoAndFromDate(int empNo, String deptNo, Date fromDate, DeptManager deptManager) {
        DeptManager existingDeptManager = deptManagerRepo.findByIdAndFromDate(new DeptManagerCompositeKey(empNo, deptNo), fromDate);
        if (existingDeptManager != null) {
            existingDeptManager.setToDate(deptManager.getToDate());
            return deptManagerRepo.save(existingDeptManager);
        }
        return null;
    }

    @Override
    public String deleteDeptManagerByEmpNoAndDeptNo(int empNo, String deptNo) {
        DeptManager deptManager = deptManagerRepo.findById(new DeptManagerCompositeKey(empNo, deptNo)).orElse(null);
        if (deptManager != null) {
            deptManagerRepo.delete(deptManager);
            return "DeptManager deleted successfully.";
        }
        return "DeptManager not found.";
    }

    @Override
    public DeptManager deleteDeptManagerByEmpNoDeptNoAndFromDate(int empNo, String deptNo, Date fromDate) {
        DeptManager deptManager = deptManagerRepo.findByIdAndFromDate(new DeptManagerCompositeKey(empNo, deptNo), fromDate);
        if (deptManager != null) {
            deptManagerRepo.delete(deptManager);
            return deptManager;
        }
        return null;
    }

    @Override
    public List<DeptManager> deleteDeptManagerByDeptNoAndFromDate(String deptNo, Date fromDate) {
        List<DeptManager> deptManagers = deptManagerRepo.findAllByIdDeptNoAndFromDate(deptNo, fromDate);
        if (!deptManagers.isEmpty()) {
            deptManagerRepo.deleteAll(deptManagers);
            return deptManagers;
        }
        return List.of();
    }

    @Override
    public DeptManager deleteDeptManager(int empNo, String deptNo, Date fromDate) {
        DeptManager deptManager = deptManagerRepo.findByIdAndFromDate(new DeptManagerCompositeKey(empNo, deptNo), fromDate);
        if (deptManager != null) {
            deptManagerRepo.delete(deptManager);
            return deptManager;
        }
        return null;
    }

    @Override
    public DeptManager deleteDeptManagerByEmpNoAndFromDate(int empNo, Date fromDate) {
        List<DeptManager> deptManagers = deptManagerRepo.findAllByIdEmpNoAndFromDate(empNo, fromDate);
        if (!deptManagers.isEmpty()) {
            deptManagers.forEach(deptManagerRepo::delete);
            return deptManagers.get(0); // Return the first one as an example
        } else {
            throw new ResourceNotFoundException("No DeptManager found for empNo: " + empNo + " and fromDate: " + fromDate);
        }
    }
}
