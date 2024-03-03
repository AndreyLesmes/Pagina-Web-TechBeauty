package com.tech.techy.Business;

import com.tech.techy.Dtos.CustomerDto;
import com.tech.techy.Dtos.RoleDto;
import com.tech.techy.Dtos.UserDto;
import com.tech.techy.Entity.Customer;
import com.tech.techy.Entity.Role;
import com.tech.techy.Entity.User;
import com.tech.techy.Service.CustomerService;
import com.tech.techy.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerBusiness {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    private List<Customer> customerList;

    private List<CustomerDto> customerDtoList = new ArrayList<>();

    public List<CustomerDto> findAll(){
        this.customerList=this.customerService.findAll();
        this.customerList.stream().forEach(customer -> {
            CustomerDto customerDto = new CustomerDto();
            customerDto.setPkId(customer.getPkId());
            customerDto.setTypeCustomer(customer.getTypeCustomer());

            User user = customer.getFkIdUser();
            if(user != null) {
                UserDto userDto = new UserDto();
                userDto.setPkId(user.getPkId());

                Role role = user.getNumRole();
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
                customerDto.setFkIdUser(userDto);
            }
            customerDtoList.add(customerDto);
        });
        return this.customerDtoList;
    }

    public Customer findById(int id) {
        return this.customerService.findById(id);
    }

    public void createCustomer(CustomerDto customerDto) throws Exception {
        Customer customer = new Customer();

        UserDto userDto = customerDto.getFkIdUser();
        User user = userService.findById(userDto.getPkId());

        if (user == null) {
            throw new Exception("Role not found with ID: " + userDto.getPkId());
        }

        customer.setFkIdUser(user);
        customer.setPkId(customerDto.getPkId());
        customer.setTypeCustomer(customerDto.getTypeCustomer());

        this.customerService.create(customer);

    }

    public void updateCustomer(int id, CustomerDto updatedCustomerDto) throws Exception {
        Customer existingCustomer = customerService.findById(id);
        if(existingCustomer == null) {
            throw new Exception("Cliente no encontrado!");
        }
        existingCustomer.setPkId(updatedCustomerDto.getPkId());
        existingCustomer.setTypeCustomer(updatedCustomerDto.getTypeCustomer());

        if(updatedCustomerDto.getFkIdUser() != null) {
            int userId = updatedCustomerDto.getFkIdUser().getPkId();
            User user = userService.findById(userId);
            if (user == null) {
                throw new Exception("El id " + userId + " no se encuentra!");
            }
            existingCustomer.setFkIdUser(user);
        }
        this.customerService.update(existingCustomer);
    }

    public void deleteCustomer(int id) throws Exception{
        Customer existingCustomer = customerService.findById(id);
        if (existingCustomer == null) {
            throw new Exception("Cliente no encontrado!");
        }

        this.customerService.delete(existingCustomer);
    }
}
