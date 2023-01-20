package com.bilalkose.business.services;

import com.bilalkose.business.dto.EmployeeDto;
import com.bilalkose.data.entity.EmployeeEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface EmployeeServices {

    //CRUD
    public List<EmployeeDto> getAllEmployees();
    public EmployeeDto createEmployee(EmployeeDto employeeDto);
    public ResponseEntity<EmployeeDto> getEmployeeById(Long id) throws Throwable; //id'ye göre BULMAYI sağlarız. sonuçlandı mı sonuçlanmadı mı yapısı-> ResponseEntity
    public ResponseEntity<EmployeeDto> updateEmployee(Long id, EmployeeDto employeeDto) throws Throwable;
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(Long id) throws Throwable;

    //model Mapper
    public EmployeeDto entityToDto(EmployeeEntity employeeEntity); //çevirme işlemi. entity'den dto'ya çevirmek için
    public EmployeeEntity dtoToEntity(EmployeeDto employeeDto); //çevirme işlemi. dto'dan entity'ye çevirmek için
}
