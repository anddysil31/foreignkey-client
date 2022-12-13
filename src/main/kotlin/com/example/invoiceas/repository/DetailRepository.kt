package com.example.invoiceas.repository

import com.example.invoiceas.model.Detail
import com.example.invoiceas.model.Invoice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository
interface DetailRepository:JpaRepository<Detail, Long> {
    fun findById(id: Long?): Detail?

    @Query(nativeQuery =true)
    fun sumTotal(@Param ("invoiceId") invoiceId: Long?): Double?
}


