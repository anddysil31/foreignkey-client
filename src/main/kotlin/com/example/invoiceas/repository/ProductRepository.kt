package com.example.invoiceas.repository

import com.example.invoiceas.model.Product
import com.example.invoiceas.model.Invoice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface ProductRepository:JpaRepository<Product, Long> {
    fun findById(id: Long?): Product?
}


