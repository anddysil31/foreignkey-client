package com.example.invoiceas.service

import com.example.invoiceas.model.Detail
import com.example.invoiceas.model.Invoice
import com.example.invoiceas.model.Product
import com.example.invoiceas.repository.DetailRepository
import com.example.invoiceas.repository.InvoiceRepository
import com.example.invoiceas.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class DetailService {
    @Autowired
    lateinit var detailRepository: DetailRepository
    @Autowired
    lateinit var invoiceRepository: InvoiceRepository
    @Autowired
    lateinit var productRepository:ProductRepository


    fun list ():List<Detail>{
        return detailRepository.findAll()
    }




    fun save (detail: Detail):Detail{
       try{
           invoiceRepository.findById(detail.invoiceId)
               ?:throw Exception("El id ${detail.invoiceId} de factura no existe")
           val response = detailRepository.save(detail)
           productRepository.findById(detail.productId)
               ?:throw Exception("El id ${detail.productId} de detalle no existe")
           val actualizeStockProduct = productRepository.findById(response.productId)!!
           actualizeStockProduct?.apply {
               stock = stock?.minus(detail.quantity!!)
           }
           productRepository.save(actualizeStockProduct!!)
           calculateAndUpdateTotal(response)
           return response
       }catch (ex:Exception){
           throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
       }

    }

    fun update(detail: Detail):Detail{
        try {
            detailRepository.findById(detail.id)
                ?: throw Exception("El id ${detail.id} en detalle no existe")
            return detailRepository.save(detail)
        }
        catch(ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateTotal(detail: Detail):Detail{
        try{
            val response = detailRepository.findById(detail.id)
                ?:throw Exception("El ${detail.id} en detalle no existe")
            return detailRepository.save(detail)
            response.apply{
                quantity = detail.quantity
            }
            return detailRepository.save(response)
        }
            catch (ex:Exception) {
                throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)

            }
    }

    fun calculateAndUpdateTotal (detail: Detail){
        val totalCalculated = detailRepository.sumTotal(detail.invoiceId)
        val invoiceResponse = invoiceRepository.findById(detail.invoiceId)
        invoiceResponse?.apply {
            total=totalCalculated
        }
        invoiceRepository.save(invoiceResponse!!)
    }
}





