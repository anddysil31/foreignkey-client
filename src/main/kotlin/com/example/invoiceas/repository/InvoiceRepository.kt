package com.example.invoiceas.repository

import com.example.invoiceas.model.Client
import com.example.invoiceas.model.Invoice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository
interface InvoiceRepository:JpaRepository<Invoice, Long> {
    fun findById(id: Long?): Invoice?

    @Query(nativeQuery = true)
    fun findTotalMoreThan(@Param("total") total: Double?):List<Invoice>?

    @Modifying
    fun updateFromDetails(@Param("invoiceId") invoiceId: Long?): Double?
}


