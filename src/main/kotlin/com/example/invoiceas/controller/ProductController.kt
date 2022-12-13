package com.example.invoiceas.controller


import com.example.invoiceas.model.Product
import com.example.invoiceas.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/product")
class ProductController {

    @Autowired
    lateinit var productService: ProductService

    @GetMapping
    fun list():List<Product>{
        return productService.list()
    }

    @PostMapping
    fun save(@RequestBody product: Product): Product?{
        return productService.save(product)

    }
    @GetMapping("/products/{stock}")
    fun listStocks(@PathVariable("stock") stock:Long):ResponseEntity<*>{
        return ResponseEntity(productService.findStockMinium(stock), HttpStatus.ACCEPTED)
    }

    @PutMapping
    fun update(@RequestBody product: Product): ResponseEntity<Product> {
        return ResponseEntity(productService.update(product), HttpStatus.ACCEPTED)
    }

    @PatchMapping
    fun updateTotal(@RequestBody product: Product): ResponseEntity<Product> {
        return ResponseEntity(productService.updateTotal(product), HttpStatus.ACCEPTED)
    }

}
