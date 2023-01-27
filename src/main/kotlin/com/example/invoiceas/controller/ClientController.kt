package com.example.invoiceas.controller

import com.example.invoiceas.model.Client
import com.example.invoiceas.service.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import javax.validation.Valid


@RestController
@RequestMapping("/client")
class ClientController {

    @Autowired
    lateinit var clientService: ClientService

//    @GetMapping
//    fun list():List<Client>{
//        return clientService.list()
//    }
    @GetMapping
    fun list (client: Client, pageable: Pageable):ResponseEntity<*>{
        val response= clientService.list( pageable, client)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun listById(@PathVariable("id") id:Long):ResponseEntity<*>{
    return ResponseEntity(clientService.findClientById(id), HttpStatus.ACCEPTED)
    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return clientService.delete(id)
    }
    @PostMapping
    fun save(@RequestBody @Valid client: Client):ResponseEntity<Client>?{
        return ResponseEntity(clientService.save(client), HttpStatus.ACCEPTED)

    }

    @PutMapping
    fun update(@RequestBody client:Client): ResponseEntity<Client> {
        return ResponseEntity(clientService.update(client), HttpStatus.ACCEPTED)
    }

    @PatchMapping
    fun updateName(@RequestBody client:Client): ResponseEntity<Client> {
        return ResponseEntity(clientService.updateName(client), HttpStatus.ACCEPTED)
    }

}
