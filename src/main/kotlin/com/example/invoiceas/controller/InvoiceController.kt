package com.example.invoiceas.controller


import com.example.invoiceas.model.Invoice
import com.example.invoiceas.service.InvoiceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/invoice")
class InvoiceController {

    @Autowired
    lateinit var invoiceService: InvoiceService

    @GetMapping
    fun list():List<Invoice>{
        return invoiceService.list()
    }

    @PostMapping
    fun save(@RequestBody invoice: Invoice): Invoice?{
        return invoiceService.save(invoice)

    }

    @PutMapping
    fun update(@RequestBody invoice: Invoice): ResponseEntity<Invoice> {
        return ResponseEntity(invoiceService.update(invoice), HttpStatus.ACCEPTED)
    }

    @PatchMapping
    fun updateTotal(@RequestBody invoice: Invoice): ResponseEntity<Invoice> {
        return ResponseEntity(invoiceService.updateTotal(invoice), HttpStatus.ACCEPTED)
    }

}
