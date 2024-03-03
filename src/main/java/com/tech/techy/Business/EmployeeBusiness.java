package com.tech.techy.Business;

import com.tech.techy.Dtos.EmployeeDto;
import com.tech.techy.Dtos.RoleDto;
import com.tech.techy.Dtos.StatusDto;
import com.tech.techy.Dtos.UserDto;
import com.tech.techy.Entity.Employee;
import com.tech.techy.Entity.Role;
import com.tech.techy.Entity.Status;
import com.tech.techy.Entity.User;
import com.tech.techy.Service.EmployeeService;
import com.tech.techy.Service.StatusService;
import com.tech.techy.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeBusiness {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

    @Autowired
    private StatusService statusService;

    private List<Employee> employeeList;

    private List<EmployeeDto> employeeDtoList = new ArrayList<>();

    public List<EmployeeDto> findAll(){
        this.employeeList = this.employeeService.findAll();
        this.employeeList.stream().forEach(employee -> {
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setPkId(employee.getPkId());
            employeeDto.setEps(employee.getEps());
            employeeDto.setSocialClass(employee.getSocialClass());
            employeeDto.setMaritalStatus(employee.getMaritalStatus());

            User user = employee.getFkIdUser();
            if(user != null) {
                UserDto userDto = new UserDto();
                userDto.setPkId(user.getPkId());
                Role role = employee.getFkIdUser().getNumRole();
                RoleDto roleDto = new RoleDto();
                roleDto.setPkNumRoles(role.getPkNumRoles());
                roleDto.setName(role.getName());
                userDto.setNumRole(roleDto);
                userDto.setName(user.getName());
                userDto.setLastName(user.getLastName());
                userDto.setTelephone(user.getTelephone());
                userDto.setAddress(user.getAddress());
                userDto.setEmail(user.getEmail());
                userDto.setPasswordU(user.getPasswordU());
                employeeDto.setFkIdUser(userDto);
            }

            Status status = employee.getFkIdStatus();
            if (status != null) {
                StatusDto statusDto = new StatusDto();
                statusDto.setPkId(status.getPkId());
                statusDto.setName(status.getName());
                employeeDto.setFkIdStatus(statusDto);
            }
            employeeDtoList.add(employeeDto);
        });
        return this.employeeDtoList;
    }

    public Employee findById(int id) {
        return this.employeeService.findById(id);
    }

    public void createEmployee(EmployeeDto employeeDto) throws Exception {
        Employee employee = new Employee();

        employee.setPkId(employeeDto.getPkId());

        UserDto userDto = employeeDto.getFkIdUser();
        User user = userService.findById(userDto.getPkId());

        if (user == null) {
            throw new Exception("Role not found with ID: " + userDto.getNumRole());
        }

        employee.setFkIdUser(user);

        StatusDto statusDto = employeeDto.getFkIdStatus();
        Status status = statusService.findById(statusDto.getPkId());
        if(status == null) {
            throw new Exception("Role not found with ID: " + statusDto.getPkId());
        }

        employee.setFkIdStatus(status);
        employee.setEps(employeeDto.getEps());
        employee.setSocialClass(employeeDto.getSocialClass());
        employee.setMaritalStatus(employeeDto.getMaritalStatus());

        this.employeeService.create(employee);

    }

    public void updateEmployee(int id, EmployeeDto updatedEmployeeDto) throws Exception {
        Employee existingEmployee = employeeService.findById(id);
        if(existingEmployee == null) {
            throw new Exception("Empleado no encontrado!");
        }

        existingEmployee.setEps(updatedEmployeeDto.getEps());
        existingEmployee.setSocialClass(updatedEmployeeDto.getSocialClass());
        existingEmployee.setMaritalStatus(updatedEmployeeDto.getMaritalStatus());

        if(updatedEmployeeDto.getFkIdUser() != null) {
            int userId = updatedEmployeeDto.getFkIdUser().getPkId();
            User user = userService.findById(userId);
            if (user == null) {
                throw new Exception("El id " + userId + " no se encuentra!");
            }
            existingEmployee.setFkIdUser(user);
        }

        if(updatedEmployeeDto.getFkIdStatus() != null) {
            int statusId = updatedEmployeeDto.getFkIdStatus().getPkId();
            Status status = statusService.findById(statusId);
            if (status == null) {
                throw new Exception("El id " + statusId + " no se encuentra!");
            }
            existingEmployee.setFkIdStatus(status);
        }
        this.employeeService.update(existingEmployee);
    }

    public void deleteEmployee(int id) throws Exception{
        Employee existingEmployee = employeeService.findById(id);
        if (existingEmployee == null) {
            throw new Exception("Empleado no encontrado!");
        }

        this.employeeService.delete(existingEmployee);
    }
}
