package com.autogarage.eindopdracht.Controllers;

import com.autogarage.eindopdracht.Service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    // GET REQUESTS
    @GetMapping("/invoices")
    ResponseEntity<Object> getAllInvoices () {
        return new ResponseEntity<>(invoiceService.findAllInvoices(), HttpStatus.OK);
    }
    @GetMapping("/invoices/{id}")
    ResponseEntity<Object> getInvoiceById (@PathVariable Long id) {
        return new ResponseEntity<>(invoiceService.findInvoiceById(id), HttpStatus.OK);
    }

}
