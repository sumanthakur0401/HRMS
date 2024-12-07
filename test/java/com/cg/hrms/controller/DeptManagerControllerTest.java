package com.cg.hrms.controller;

import com.cg.hrms.entities.DeptManager;
import com.cg.hrms.services.serviceinterface.DeptManagerService;
import com.cg.hrms.utility.DeptManagerCompositeKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
public class DeptManagerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeptManagerService deptManagerService;

    private DeptManager deptManager;
    private DeptManagerCompositeKey compositeKey;
    private Date fromDate;
    private Date toDate;

    @BeforeEach
    public void setup() {
        fromDate = new Date();
        toDate = new Date(fromDate.getTime() + 86400000L); 
        compositeKey = new DeptManagerCompositeKey(1, "D01");
        deptManager = new DeptManager();
        deptManager.setId(compositeKey);
        deptManager.setFromDate(fromDate);
        deptManager.setToDate(toDate);
    }

    @Test
    public void testGetAllDeptManagers() throws Exception {
        when(deptManagerService.getAllDeptManagers()).thenReturn(List.of(deptManager));

        mockMvc.perform(get("/api/v1/deptmanager/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id.empNo", is(1)))
                .andExpect(jsonPath("$[0].id.deptNo", is("D01")));
    }

    @Test
    public void testFindDeptManagerByEmpNoAndDeptNo() throws Exception {
        when(deptManagerService.findDeptManagerByEmpNoAndDeptNo(1, "D01")).thenReturn(deptManager);

        mockMvc.perform(get("/api/v1/deptmanager/empno/1/deptno/D01")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id.empNo", is(1)))
                .andExpect(jsonPath("$.id.deptNo", is("D01")));
    }

    @Test
    public void testFindAllDeptManagersByDeptNoAndFromDate() throws Exception {
        when(deptManagerService.findAllDeptManagersByDeptNoAndFromDate("D01", fromDate)).thenReturn(List.of(deptManager));
        mockMvc.perform(get("/api/v1/deptmanager/deptno/D01/fromdate/" + fromDate.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id.deptNo", is("D01")));
    }

    @Test
    public void testFindAllDeptManagersByEmpNoAndFromDate() throws Exception {
        when(deptManagerService.findAllDeptManagersByEmpNoAndFromDate(1, fromDate)).thenReturn(List.of(deptManager));

        mockMvc.perform(get("/api/v1/deptmanager/empno/1/fromdate/" + fromDate.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id.empNo", is(1)));
    }

    @Test
    public void testFindAllDeptManagersByEmpNoDeptNoAndFromDate() throws Exception {
        when(deptManagerService.findAllDeptManagersByEmpNoDeptNoAndFromDate(1, "D01", fromDate)).thenReturn(List.of(deptManager));

        mockMvc.perform(get("/api/v1/deptmanager/empno/1/deptno/D01/fromdate/" + fromDate.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id.deptNo", is("D01")));
    }

    @Test
    public void testUpdateDeptManagerByEmpNoAndDeptNo() throws Exception {
        when(deptManagerService.updateDeptManagerByEmpNoAndDeptNo(1, "D01", deptManager)).thenReturn(deptManager);

        mockMvc.perform(put("/api/v1/deptmanager/1/D01")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": {\"empNo\": 1, \"deptNo\": \"D01\"}, \"fromDate\": \"" + fromDate.toString() + "\", \"toDate\": \"" + toDate.toString() + "\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Employee assigned to department updated Successfully"));
    }

    @Test
    public void testUpdateDeptManagerByEmpNoAndFromDate() throws Exception {
        when(deptManagerService.updateDeptManagerByEmpNoAndFromDate(1, fromDate, deptManager)).thenReturn(deptManager);

        mockMvc.perform(put("/api/v1/deptmanager/empno/1/fromdate/" + fromDate.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": {\"empNo\": 1, \"deptNo\": \"D01\"}, \"fromDate\": \"" + fromDate.toString() + "\", \"toDate\": \"" + toDate.toString() + "\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Employee details updated Successfully"));
    }

    @Test
    public void testUpdateDeptManagerByDeptNoAndFromDate() throws Exception {
        when(deptManagerService.updateDeptManagerByDeptNoAndFromDate("D01", fromDate, deptManager)).thenReturn(List.of(deptManager));

        mockMvc.perform(put("/api/v1/deptmanager/deptno/D01/fromdate/" + fromDate.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": {\"empNo\": 1, \"deptNo\": \"D01\"}, \"fromDate\": \"" + fromDate.toString() + "\", \"toDate\": \"" + toDate.toString() + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id.deptNo", is("D01")));
    }

    @Test
    public void testUpdateDeptManagerByEmpNoDeptNoAndFromDate() throws Exception {
        when(deptManagerService.updateDeptManagerByEmpNoDeptNoAndFromDate(1, "D01", fromDate, deptManager)).thenReturn(deptManager);

        mockMvc.perform(put("/api/v1/deptmanager/empno/1/deptno/D01/fromdate/" + fromDate.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": {\"empNo\": 1, \"deptNo\": \"D01\"}, \"fromDate\": \"" + fromDate.toString() + "\", \"toDate\": \"" + toDate.toString() + "\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("DeptManager updated Successfully"));
    }

    @Test
    public void testDeleteDeptManagerByEmpNoAndDeptNo() throws Exception {
        when(deptManagerService.deleteDeptManagerByEmpNoAndDeptNo(1, "D01")).thenReturn("DeptManager deleted successfully.");

        mockMvc.perform(delete("/api/v1/deptmanager/empno/1/deptno/D01"))
                .andExpect(status().isOk())
                .andExpect(content().string("DeptManager deleted Successfully"));
    }

    @Test
    public void testDeleteDeptManagerByEmpNoAndFromDate() throws Exception {
        when(deptManagerService.deleteDeptManagerByEmpNoAndFromDate(1, fromDate)).thenReturn(deptManager);

        mockMvc.perform(delete("/api/v1/deptmanager/empno/1/fromdate/" + fromDate.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id.empNo", is(1)));
    }

    @Test
    public void testDeleteDeptManagerByDeptNoAndFromDate() throws Exception {
        when(deptManagerService.deleteDeptManagerByDeptNoAndFromDate("D01", fromDate)).thenReturn(List.of(deptManager));

        mockMvc.perform(delete("/api/v1/deptmanager/deptno/D01/fromdate/" + fromDate.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id.deptNo", is("D01")));
    }

    @Test
    public void testDeleteDeptManagerByEmpNoDeptNoAndFromDate() throws Exception {
        when(deptManagerService.deleteDeptManagerByEmpNoDeptNoAndFromDate(1, "D01", fromDate)).thenReturn(deptManager);

        mockMvc.perform(delete("/api/v1/deptmanager/empno/1/deptno/D01/fromdate/" + fromDate.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id.deptNo", is("D01")));
    }
}
