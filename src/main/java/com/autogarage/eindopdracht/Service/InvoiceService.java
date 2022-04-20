package com.autogarage.eindopdracht.Service;

import com.autogarage.eindopdracht.DTO.InvoiceDTO;

import java.util.List;

public interface InvoiceService {
    InvoiceDTO createInvoice(Long maintenanceId);
    List<InvoiceDTO> findAllInvoices();
    InvoiceDTO findInvoiceById (Long id);
}
