package com.tech.techy.Business;

import com.tech.techy.Dtos.SupplierDto;
import com.tech.techy.Entity.Supplier;
import com.tech.techy.Service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SupplierBusiness {

    @Autowired
    private SupplierService supplierService;

    private List<Supplier> supplierList;

    private List<SupplierDto> supplierDtoList = new ArrayList<>();

    public List<SupplierDto> findAll() {
        this.supplierList = this.supplierService.findAll();
        this.supplierList.stream().forEach(supplier -> {
            SupplierDto supplierDto = new SupplierDto();
            supplierDto.setPkNit(supplier.getPkNit());
            supplierDto.setName(supplier.getName());
            supplierDto.setLastName(supplier.getLastName());
            supplierDto.setAddress(supplier.getAddress());
            supplierDto.setTelephone(supplier.getTelephone());
            supplierDto.setEmail(supplier.getEmail());
            supplierDto.setCompanyName(supplier.getCompanyName());
            supplierDto.setProductType(supplier.getProductType());
            supplierDto.setSupplierBrand(supplier.getSupplierBrand());

            this.supplierDtoList.add(supplierDto);
        });
        return this.supplierDtoList;
    }

    public Supplier findById(int id) {
        return this.supplierService.findById(id);
    }

    public void createSupplier(SupplierDto supplierDto) throws Exception {
        Supplier supplier = new Supplier();

        supplier.setName(supplierDto.getName());
        supplier.setLastName(supplierDto.getLastName());
        supplier.setAddress(supplierDto.getAddress());
        supplier.setTelephone(supplierDto.getTelephone());
        supplier.setEmail(supplierDto.getEmail());
        supplier.setCompanyName(supplierDto.getCompanyName());
        supplier.setProductType(supplierDto.getProductType());
        supplier.setSupplierBrand(supplierDto.getSupplierBrand());

        this.supplierService.create(supplier);
    }

    public void updateSupplier(int id, SupplierDto updatedSupplierDto) throws Exception {
        Supplier existingSupplier = supplierService.findById(id);

        existingSupplier.setName(updatedSupplierDto.getName());
        existingSupplier.setLastName(updatedSupplierDto.getLastName());
        existingSupplier.setAddress(updatedSupplierDto.getAddress());
        existingSupplier.setTelephone(updatedSupplierDto.getTelephone());
        existingSupplier.setEmail(updatedSupplierDto.getEmail());
        existingSupplier.setCompanyName(updatedSupplierDto.getCompanyName());
        existingSupplier.setProductType(updatedSupplierDto.getProductType());
        existingSupplier.setSupplierBrand(updatedSupplierDto.getSupplierBrand());

        this.supplierService.update(existingSupplier);
    }

    public void deleteSupplier(int id) throws Exception{
        Supplier existingSupplier = supplierService.findById(id);
        if (existingSupplier == null) {
            throw new Exception("Proveedor no encontrado!");
        }

        this.supplierService.delete(existingSupplier);
    }
}
