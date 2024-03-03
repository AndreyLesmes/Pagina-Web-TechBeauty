package com.tech.techy.Business;

import com.tech.techy.Dtos.BuyDto;
import com.tech.techy.Dtos.CustomerDto;
import com.tech.techy.Dtos.RoleDto;
import com.tech.techy.Dtos.UserDto;
import com.tech.techy.Entity.Buy;
import com.tech.techy.Entity.Customer;
import com.tech.techy.Entity.Role;
import com.tech.techy.Entity.User;
import com.tech.techy.Service.BuyService;
import com.tech.techy.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BuyBusiness {

    @Autowired
    private BuyService buyService;

    @Autowired
    private CustomerService customerService;

    private List<Buy> buyList;

    private List<BuyDto> buyDtoList = new ArrayList<>();

    public List<BuyDto> findAll() {
        this.buyList = this.buyService.findAll();
        this.buyList.stream().forEach(buy -> {
            BuyDto buyDto = new BuyDto();
            buyDto.setPkId(buy.getPkId());

            Customer customer = buy.getFkIdCustomer();
            CustomerDto customerDto = new CustomerDto();
            customerDto.setPkId(customer.getPkId());
            customerDto.setTypeCustomer(customer.getTypeCustomer());
            buyDto.setFkIdCustomer(customerDto);

            //FkUsuario en la tabla de cliente
            User user = buy.getFkIdCustomer().getFkIdUser();
            UserDto userDto = new UserDto();
            userDto.setPkId(user.getPkId());
            userDto.setName(user.getName());
            userDto.setLastName(user.getLastName());
            userDto.setTelephone(user.getTelephone());
            userDto.setAddress(user.getAddress());
            userDto.setEmail(user.getEmail());
            userDto.setPasswordU(user.getPasswordU());
            customerDto.setFkIdUser(userDto);

            //FkRol en la tabla de usuario
            Role role = buy.getFkIdCustomer().getFkIdUser().getNumRole();
            RoleDto roleDto = new RoleDto();
            roleDto.setPkNumRoles(role.getPkNumRoles());
            roleDto.setName(role.getName());
            userDto.setNumRole(roleDto);

            buyDto.setProductQuantity(buy.getProductQuantity());
            buyDto.setDateBuy(buy.getDateBuy());
            buyDto.setSubTotal(buy.getSubTotal());
            buyDto.setTotalIva(buy.getTotalIva());
            buyDto.setTotal(buy.getTotal());

            buyDtoList.add(buyDto);
        });
        return this.buyDtoList;
    }

    public Buy findById(int id) {
        return this.buyService.findById(id);
    }

    public void createBuy(BuyDto buyDto) throws Exception {
        Buy buy = new Buy();
        CustomerDto customerDto = buyDto.getFkIdCustomer();
        Customer customer = customerService.findById(customerDto.getPkId());

        buy.setFkIdCustomer(customer);
        buy.setProductQuantity(buyDto.getProductQuantity());
        buy.setDateBuy(buyDto.getDateBuy());
        buy.setSubTotal(buyDto.getSubTotal());
        buy.setTotalIva(buyDto.getTotalIva());
        buy.setTotal(buyDto.getTotal());

        this.buyService.create(buy);
    }

    public void updateBuy(int id, BuyDto updatedBuyDto) throws Exception {
        Buy existingerBuy = buyService.findById(id);
        if(existingerBuy == null) {
            throw new Exception("Compra no encontrada!");
        }

        existingerBuy.setProductQuantity(updatedBuyDto.getProductQuantity());
        existingerBuy.setDateBuy(updatedBuyDto.getDateBuy());
        existingerBuy.setSubTotal(updatedBuyDto.getSubTotal());
        existingerBuy.setTotalIva(updatedBuyDto.getTotalIva());
        existingerBuy.setTotal(updatedBuyDto.getTotal());

        if (updatedBuyDto.getFkIdCustomer() != null) {
            int customerId = updatedBuyDto.getFkIdCustomer().getPkId();
            Customer customer = customerService.findById(customerId);
            if (customer == null) {
                throw new Exception("El id " + customerId + " no se encuentra!");
            }
        }
        this.buyService.update(existingerBuy);
    }

    public void deleteBuy(int id) throws Exception{
        Buy existingBuy = buyService.findById(id);
        if (existingBuy == null) {
            throw new Exception("Compra no encontrada!");
        }

        this.buyService.delete(existingBuy);
    }
}
