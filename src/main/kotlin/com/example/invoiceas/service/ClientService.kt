package com.example.invoiceas.service

import com.example.invoiceas.controller.ClientController
import com.example.invoiceas.model.Client
import com.example.invoiceas.repository.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class ClientService {
    @Autowired
    lateinit var clientRepository: ClientRepository

    fun list ():List<Client>{
        return clientRepository.findAll()
    }

    fun save (client: Client):Client{
        return clientRepository.save(client)
    }

    fun update(client: Client):Client{
        try {
            clientRepository.findById(client.id)
                ?: throw Exception("El id ${client.id} en cliente no existe")
            return clientRepository.save(client)
        }
        catch(ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateName(client: Client):Client{
        try{
            val response = clientRepository.findById(client.id)
                ?:throw Exception("El ${client.id} en cliente no existe")
            return clientRepository.save(client)
            response.apply{
                fullname = client.fullname
            }
            return clientRepository.save(response)
        }
            catch (ex:Exception){
            throw  ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)

        }
    }
}


