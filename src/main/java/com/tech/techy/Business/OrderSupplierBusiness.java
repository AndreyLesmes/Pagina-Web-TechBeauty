package com.tech.techy.Business;

import com.tech.techy.Dtos.*;
import com.tech.techy.Entity.*;
import com.tech.techy.Service.EmployeeService;
import com.tech.techy.Service.OrderSupplierService;
import com.tech.techy.Service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderSupplierBusiness {

    @Autowired
    private OrderSupplierService orderSupplierService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SupplierService supplierService;

    private List<OrderSupplier> orderSupplierList;

    private List<OrderSupplierDto> orderSupplierDtoList = new ArrayList<>();

    public List<OrderSupplierDto> findAll(){
        this.orderSupplierList = this.orderSupplierService.findAll();
        this.orderSupplierList.stream().forEach(orderSupplier -> {
            OrderSupplierDto orderSupplierDto = new OrderSupplierDto();
            orderSupplierDto.setPkId(orderSupplier.getPkId());

            Employee employee = orderSupplier.getFkIdEmployee();
            if (employee != null) {
                EmployeeDto employeeDto = new EmployeeDto();
                employeeDto.setPkId(employee.getPkId());
                employeeDto.setEps(employee.getEps());
                employeeDto.setSocialClass(employee.getSocialClass());
                employeeDto.setMaritalStatus(employee.getMaritalStatus());

                //FkUsuario en la tabla empleado
                User user = orderSupplier.getFkIdEmployee().getFkIdUser();
                UserDto userDto = new UserDto();
                userDto.setPkId(user.getPkId());

                //FkRole en la tabla usuario
                Role role = orderSupplier.getFkIdEmployee().getFkIdUser().getNumRole();
                RoleDto roleDto = new RoleDto();
                roleDto.setPkNumRoles(role.getPkNumRoles());
                roleDto.setName(role.getName());
                userDto.setNumRole(roleDto);

                //FkStatus en la tabla Empleado
                Status status = orderSupplier.getFkIdEmployee().getFkIdStatus();
                StatusDto statusDto = new StatusDto();
                statusDto.setPkId(status.getPkId());
                statusDto.setName(status.getName());
                employeeDto.setFkIdStatus(statusDto);

                userDto.setName(user.getName());
                userDto.setLastName(user.getLastName());
                userDto.setTelephone(user.getTelephone());
                userDto.setAddress(user.getAddress());
                userDto.setEmail(user.getEmail());
                userDto.setPasswordU(user.getPasswordU());
                employeeDto.setFkIdUser(userDto);
                orderSupplierDto.setFkIdEmployee(employeeDto);
            }

            Supplier supplier = orderSupplier.getFkNitSupplier();
            if (supplier != null) {
                SupplierDto supplierDto = new SupplierDto();
                supplierDto.setPkNit(supplier.getPkNit());
                supplierDto.setName(supplier.getName());
                supplierDto.setLastName(supplierDto.getLastName());
                supplierDto.setAddress(supplier.getAddress());
                supplierDto.setTelephone(supplier.getTelephone());
                supplierDto.setEmail(supplier.getEmail());
                supplierDto.setCompanyName(supplier.getCompanyName());
                supplierDto.setProductType(supplier.getProductType());
                supplierDto.setSupplierBrand(supplier.getSupplierBrand());
                orderSupplierDto.setFkNitSupplier(supplierDto);
            }

            orderSupplierDto.setProductQuantity(orderSupplier.getProductQuantity());
            orderSupplierDto.setDate(orderSupplier.getDate());
            orderSupplierDto.setPrice(orderSupplier.getPrice());
            orderSupplierDto.setTypePay(orderSupplier.getTypePay());
            orderSupplierDto.setTotalOrder(orderSupplier.getTotalOrder());
            orderSupplierDto.setTotalIva(orderSupplier.getTotalIva());

            orderSupplierDtoList.add(orderSupplierDto);
        });
        return this.orderSupplierDtoList;
    }

    public OrderSupplier findById(int id) {
        return this.orderSupplierService.findById(id);
    }

    public void createOrderSupplier(OrderSupplierDto orderSupplierDto) throws Exception {
        OrderSupplier orderSupplier = new OrderSupplier();
        EmployeeDto employeeDto = orderSupplierDto.getFkIdEmployee();
        Employee employee = employeeService.findById(employeeDto.getPkId());

        if (employee == null) {
            throw new Exception("Role not found with ID: " + employeeDto.getPkId());
        }

        SupplierDto supplierDto = orderSupplierDto.getFkNitSupplier();
        Supplier supplier = supplierService.findById(supplierDto.getPkNit());

        if (supplier == null) {
            throw new Exception("Role not found with ID: " + supplierDto.getPkNit());
        }

        orderSupplier.setFkIdEmployee(employee);
        orderSupplier.setFkNitSupplier(supplier);
        orderSupplier.setProductQuantity(orderSupplierDto.getProductQuantity());
        orderSupplier.setDate(orderSupplierDto.getDate());
        orderSupplier.setPrice(orderSupplierDto.getPrice());
        orderSupplier.setTypePay(orderSupplierDto.getTypePay());
        orderSupplier.setTotalOrder(orderSupplierDto.getTotalOrder());
        orderSupplier.setTotalIva(orderSupplierDto.getTotalIva());

        this.orderSupplierService.create(orderSupplier);

    }

    public void updateOrderSupplier(int id, OrderSupplierDto updatedOrderSupplierDto) throws Exception {
        OrderSupplier existingOrderSupplier = orderSupplierService.findById(id);
        if(existingOrderSupplier == null) {
            throw new Exception("Orden a Proveedor no encontrada!");
        }

        if(updatedOrderSupplierDto.getFkIdEmployee() != null) {
            int employeeId = updatedOrderSupplierDto.getFkIdEmployee().getPkId();
            Employee employee = employeeService.findById(employeeId);
            if (employee == null) {
                throw new Exception("El id " + employeeId + " no se encuentra!");
            }
            existingOrderSupplier.setFkIdEmployee(employee);
        }

        if(updatedOrderSupplierDto.getFkNitSupplier() != null) {
            int supplierId = updatedOrderSupplierDto.getFkNitSupplier().getPkNit();
            Supplier supplier = supplierService.findById(supplierId);
            if (supplier == null) {
                throw new Exception("El id " + supplierId + " no se encuentra!");
            }
            existingOrderSupplier.setFkNitSupplier(supplier);
        }

        existingOrderSupplier.setDate(updatedOrderSupplierDto.getDate());
        existingOrderSupplier.setPrice(updatedOrderSupplierDto.getPrice());
        existingOrderSupplier.setTypePay(updatedOrderSupplierDto.getTypePay());
        existingOrderSupplier.setTotalOrder(updatedOrderSupplierDto.getTotalOrder());
        existingOrderSupplier.setTotalIva(updatedOrderSupplierDto.getTotalIva());

        this.orderSupplierService.update(existingOrderSupplier);
    }

    public void deleteOrderSupplier(int id) throws Exception{
        OrderSupplier existingOrderSupplier = orderSupplierService.findById(id);
        if (existingOrderSupplier == null) {
            throw new Exception("Orden a Probedor no encontrada!");
        }

        this.orderSupplierService.delete(existingOrderSupplier);
    }
}
