package com.tech.techy.Business;

import com.tech.techy.Dtos.*;
import com.tech.techy.Entity.*;
import com.tech.techy.Service.BuyDetailService;
import com.tech.techy.Service.BuyService;
import com.tech.techy.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BuyDetailBusiness {

    @Autowired
    private BuyDetailService buyDetailService;

    @Autowired
    private BuyService buyService;

    @Autowired
    private ProductService productService;

    private List<BuyDetail> buyDetailList;

    private List<BuyDetailDto> buyDetailDtoList = new ArrayList<>();

    public List<BuyDetailDto> findAll() {
        this.buyDetailList = this.buyDetailService.findAll();
        this.buyDetailList.stream().forEach(buyDetail -> {
            BuyDetailDto buyDetailDto = new BuyDetailDto();
            buyDetailDto.setPkId(buyDetail.getPkId());
            buyDetailDto.setProductQuantity(buyDetail.getProductQuantity());

            Buy buy = buyDetail.getFkIdBuys();
            if(buy != null) {
                BuyDto buyDto = new BuyDto();
                buyDto.setPkId(buy.getPkId());

                //FkCliente en la tabla de compra
                Customer customer = buyDetail.getFkIdBuys().getFkIdCustomer();
                CustomerDto customerDto = new CustomerDto();
                customerDto.setPkId(customer.getPkId());
                customerDto.setTypeCustomer(customer.getTypeCustomer());
                buyDto.setFkIdCustomer(customerDto);

                //FkUsuario en la tabla de cliente
                User user = buyDetail.getFkIdBuys().getFkIdCustomer().getFkIdUser();
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
                Role role = buyDetail.getFkIdBuys().getFkIdCustomer().getFkIdUser().getNumRole();
                RoleDto roleDto = new RoleDto();
                roleDto.setPkNumRoles(role.getPkNumRoles());
                roleDto.setName(role.getName());
                userDto.setNumRole(roleDto);


                buyDto.setProductQuantity(buy.getProductQuantity());
                buyDto.setDateBuy(buy.getDateBuy());
                buyDto.setSubTotal(buy.getSubTotal());
                buyDto.setTotalIva(buy.getTotalIva());
                buyDto.setTotal(buy.getTotal());
                buyDetailDto.setFkIdBuys(buyDto);
            }

            Product product = buyDetail.getFkIdProducts();
            if(product != null) {
                ProductDto productDto = new ProductDto();
                productDto.setPkId(product.getPkId());
                Category category = buyDetail.getFkIdProducts().getFkIdCategories();
                CategoryDto categoryDto = new CategoryDto();
                categoryDto.setPkId(category.getPkId());
                categoryDto.setName(category.getName());
                productDto.setFkIdCategories(categoryDto);
                productDto.setName(product.getName());
                productDto.setPrice(product.getPrice());
                productDto.setProductQuantity(product.getProductQuantity());
                productDto.setDescription(productDto.getDescription());
                productDto.setMeasurementUnit(product.getMeasurementUnit());
                productDto.setProductReference(product.getProductReference());
                buyDetailDto.setFkIdProducts(productDto);
            }

            buyDetailDtoList.add(buyDetailDto);
        });
        return this.buyDetailDtoList;
    }

    public BuyDetail findById(int id) {
        return this.buyDetailService.findById(id);
    }

    public void createBuyDetail(BuyDetailDto buyDetailDto) throws Exception {
        BuyDetail buyDetail = new BuyDetail();

        //fkcompras
        BuyDto buyDto = buyDetailDto.getFkIdBuys();
        Buy buy = buyService.findById(buyDto.getPkId());
        buyDetail.setFkIdBuys(buy);

        //fkproductos
        ProductDto productDto = buyDetailDto.getFkIdProducts();
        Product product = productService.findById(productDto.getPkId());
        buyDetail.setFkIdProducts(product);

        buyDetail.setProductQuantity(buyDetailDto.getProductQuantity());

        this.buyDetailService.create(buyDetail);

    }

    public void updateBuyDetail(int id, BuyDetailDto updatedBuyDetailDto) throws Exception {
        BuyDetail existingBuyDetail = buyDetailService.findById(id);
        if(existingBuyDetail == null) {
            throw new Exception("Detalle de compra no encontrado!");
        }

        existingBuyDetail.setProductQuantity(updatedBuyDetailDto.getProductQuantity());

        if(updatedBuyDetailDto.getFkIdBuys() != null) {
            int buyId = updatedBuyDetailDto.getFkIdBuys().getPkId();
            Buy buy = buyService.findById(buyId);
            if(buy == null) {
                throw new Exception("El id " + buyId + " no se encuentra!");
            }
            existingBuyDetail.setFkIdBuys(buy);
        }

        if(updatedBuyDetailDto.getFkIdProducts() != null) {
            int productId = updatedBuyDetailDto.getFkIdProducts().getPkId();
            Product product = productService.findById(productId);
            if(product == null) {
                throw new Exception("El id " + productId + " no se encuentra!");
            }
            existingBuyDetail.setFkIdProducts(product);
        }
        this.buyDetailService.update(existingBuyDetail);
    }

    public void deleteBuyDetail(int id) throws Exception{
        BuyDetail existingBuyDetail = buyDetailService.findById(id);
        if (existingBuyDetail == null) {
            throw new Exception("Detalle de la compra no encontrado!");
        }

        this.buyDetailService.delete(existingBuyDetail);
    }
}
