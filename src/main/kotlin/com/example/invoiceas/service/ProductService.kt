package com.example.invoiceas.service

import com.example.invoiceas.model.Invoice
import com.example.invoiceas.model.Product
import com.example.invoiceas.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class ProductService {
    @Autowired
    lateinit var productRepository: ProductRepository



    fun list ():List<Product>{
        return productRepository.findAll()
    }

    fun save (product: Product):Product{
       return productRepository.save(product)
    }

    fun update(product: Product):Product{
        try {
            productRepository.findById(product.id)
                ?: throw Exception("El id ${product.id} en producto no existe")
            return productRepository.save(product)
        }
        catch(ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }
    fun findStockMinium(stock:Long):List<Product>?{
        return productRepository.findStockMin(stock)
    }


    fun updateTotal(product: Product):Product{
        try{
            val response = productRepository.findById(product.id)
                ?:throw Exception("El ${product.id} en producto no existe")
            return productRepository.save(product)
            response.apply{
                stock = product.stock
                price = product.price
            }
            return productRepository.save(response)
        }
            catch (ex:Exception){
            throw  ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)

        }
    }


}


