package com.autogarage.eindopdracht.Service;

import com.autogarage.eindopdracht.DTO.InvoiceDTO;
import com.autogarage.eindopdracht.DTO.MaintenanceDTO;
import com.autogarage.eindopdracht.Exceptions.RecordNotFoundException;
import com.autogarage.eindopdracht.Model.Invoice;
import com.autogarage.eindopdracht.Model.Maintenance;
import com.autogarage.eindopdracht.Model.MaintenanceItem;
import com.autogarage.eindopdracht.Repository.InvoiceRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceIml implements InvoiceService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    InvoiceRepo invoiceRepo;

    @Autowired
    MaintenanceService maintenanceService;

    // CREATE
    @Override
    public InvoiceDTO createInvoice(Long maintenanceId) {
        MaintenanceDTO maintenanceDTO = maintenanceService.findMaintenanceById(maintenanceId);
        Maintenance maintenance = modelMapper.map(maintenanceDTO, Maintenance.class);
        Invoice newInvoice = new Invoice();

        // Set maintenance
        newInvoice.setMaintenance(maintenance);

        // Set totalPrice
        Integer total = 0;
        for (MaintenanceItem item : maintenance.getMaintenanceItems()) {
            Integer quantity =  item.getQuantity();
            if (item.getPart() != null) {
                Integer totalPrice =  item.getPart().getPrice() * quantity;
                total += totalPrice;

            } else {
                Integer totalPrice =  item.getRepairOperation().getPrice() * quantity;
                total += totalPrice;
            }
        }
        newInvoice.setTotalPrice(total);

        // Set Customer
        newInvoice.setCustomer(maintenance.getCar().getCustomer());
        Invoice savedInvoice = invoiceRepo.save(newInvoice);
        return modelMapper.map(savedInvoice, InvoiceDTO.class);
    }

    // READ
    @Override
    public List<InvoiceDTO> findAllInvoices() {
        List<Invoice> invoices =  invoiceRepo.findAll();
        List<InvoiceDTO> invoiceDTOS = new ArrayList<>();

        for(Invoice invoice : invoices) {
            InvoiceDTO invoiceDTO = modelMapper.map(invoice, InvoiceDTO.class);
            invoiceDTOS.add(invoiceDTO);
        }
        return invoiceDTOS;
    }

    @Override
    public InvoiceDTO findInvoiceById(Long id) {
        Optional<Invoice> invoice =  invoiceRepo.findById(id);
        if (invoice.isPresent()){
            InvoiceDTO invoiceDTO = modelMapper.map(invoice.get(), InvoiceDTO.class);
            return invoiceDTO;
        } else {
            throw new RecordNotFoundException("invoice not found");
        }
    }
}
