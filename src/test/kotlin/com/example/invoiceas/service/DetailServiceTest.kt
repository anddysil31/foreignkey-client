package com.example.invoiceas.service

import com.example.invoiceas.model.Detail
import com.example.invoiceas.model.Invoice
import com.example.invoiceas.model.Product
import com.example.invoiceas.repository.DetailRepository
import com.example.invoiceas.repository.InvoiceRepository
import com.example.invoiceas.repository.ProductRepository
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class DetailServiceTest {
    @InjectMocks
    lateinit var detailService: DetailService
    @Mock
    lateinit var detailRepository: DetailRepository
    @Mock
    lateinit var invoiceRepository: InvoiceRepository
    @Mock
    lateinit var productRepository: ProductRepository

    val secondjsonString = File("./src/test/resources/invoice.json").readText(Charsets.UTF_8)
    val invoiceMock:Invoice = Gson().fromJson(secondjsonString, Invoice::class.java)

    val thirdjsonString = File("./src/test/resources/product.json").readText(Charsets.UTF_8)
    val productMock:Product = Gson().fromJson(thirdjsonString, Product::class.java)

    val firstjsonString = File("./src/test/resources/detail.json").readText(Charsets.UTF_8)
    val detailMock: Detail = Gson().fromJson(firstjsonString, Detail::class.java)
    @Test
    fun saveInvoiceWhenIsCorrect(){
        Mockito.`when`(productRepository.findById(detailMock.productId)).thenReturn(productMock)
        Mockito.`when`(invoiceRepository.findById(detailMock.invoiceId)).thenReturn(invoiceMock)
        Mockito.`when`(detailRepository.save(Mockito.any(Detail::class.java))).thenReturn(detailMock)
        val secondResponse = detailService.save(detailMock)
        Assertions.assertEquals(secondResponse.id, detailMock.id)



    }


}