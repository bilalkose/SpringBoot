package com.bilalkose.business.services.impl;

import com.bilalkose.business.dto.EmployeeDto;
import com.bilalkose.business.services.EmployeeServices;
import com.bilalkose.data.entity.EmployeeEntity;
import com.bilalkose.data.repository.EmployeeRepository;
import com.bilalkose.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeServices {

    //db ile alakalı işlemleri yapmamız gerekir!!
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;


    //List
    //http://localhost:8080/api/v1/employees
    @GetMapping("/employees")
    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> listDto = new ArrayList<>();
        Iterable<EmployeeEntity> entities = employeeRepository.findAll();
        for (EmployeeEntity entity:entities){
            EmployeeDto employeeDto = entityToDto(entity); //her bir dönen entity ' i dto ' ya çevirelim
            listDto.add(employeeDto);
        }
        return listDto;
    }

    //Save
    //http://localhost:8080/api/v1/employees/
    @PostMapping("/employees")
    @Override
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
        //gelen verileri doğru almak istiyorsak requestBody'i yazmamız gerekir.
        EmployeeEntity employeeEntity =  dtoToEntity(employeeDto);
        employeeRepository.save(employeeEntity);
        return employeeDto;
    }

    //Find
    //http://localhost:8080/api/v1/employees/1
    @GetMapping("/employees/{id}")
    @Override
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(name = "id") Long id) {
        EmployeeEntity employee= employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee "+id+" numaralı product id bulunamadı !!!!"));

        EmployeeDto employeeDto = entityToDto(employee);//model
        return ResponseEntity.ok(employeeDto);
    }



    //Update
    //http://localhost:8080/api/v1/employees/
    @PutMapping("/employees/{id}")
    @Override
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDetails) throws Throwable {
        EmployeeEntity employeeEntity = dtoToEntity(employeeDetails); //ModelMapper
        EmployeeEntity employee = (EmployeeEntity) employeeRepository.findById(id).orElseThrow( ()-> new ResourceNotFoundException("Employee not exist noth id + " + id) );
        employee.setFirstName(employeeEntity.getFirstName());
        employee.setEmailId(employeeEntity.getEmailId());
        employee.setLastName(employeeEntity.getLastName());
        EmployeeEntity updatedEmployee = (EmployeeEntity) employeeRepository.save(employee);
        EmployeeDto employeeDto = entityToDto(updatedEmployee);
        return ResponseEntity.ok(employeeDto);
    }

    //Delete
    //http://localhost:8080/api/v1/employees/1
    @DeleteMapping("/employees/{id}")
    @Override
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) throws Throwable {
        EmployeeEntity employee = (EmployeeEntity) employeeRepository.findById(id).orElseThrow( ()-> new ResourceNotFoundException("Employee not exist noth id + " + id) );
        employeeRepository.delete(employee);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    ////////////////////////////////////////////

    //Model Mapper

    //Entity -> DTO
    @Override
    public EmployeeDto entityToDto(EmployeeEntity employeeEntity) {
        EmployeeDto employeeDto = modelMapper.map(employeeEntity, EmployeeDto.class);
        return employeeDto;
    }

    //DTO -> Entity
    @Override
    public EmployeeEntity dtoToEntity(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
        return employeeEntity;
    }
}
