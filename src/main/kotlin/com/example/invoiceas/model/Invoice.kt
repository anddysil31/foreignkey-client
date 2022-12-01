package com.example.invoiceas.model

import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table(name="invoice")
class Invoice {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    @NotBlank(message = "Este campo no debe estar vacio")
    var code :String? = null
    @Column(name="create_at")
    var createAt:Date? = null
    @NotNull(message = "Este campo no debe ser nulo")
    var total:Double? = null
    @Column(name="client_id")
    var clientId:Long? = null


}


