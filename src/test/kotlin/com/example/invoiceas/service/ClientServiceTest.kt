package com.example.invoiceas.service

import com.example.invoiceas.model.Client
import com.example.invoiceas.repository.ClientRepository
import com.google.gson.Gson

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.io.File


@SpringBootTest
class ClientServiceTest {
    @InjectMocks
    lateinit var clientService: ClientService

    @Mock
    lateinit var clientRepository: ClientRepository

    val jsonString = File("./src/test/resources/client.json").readText(Charsets.UTF_8)
    val clientMock:Client = Gson().fromJson(jsonString, Client::class.java)

    @Test
    fun saveClient(){
        //PAra actualizar
        /// LLAVES  FORENEAS
        //Mockito.`when`(productRepository.findById(productMock.id)).thenReturn(productMock)
        Mockito.`when`(clientRepository.save(Mockito.any(Client::class.java))).thenReturn(clientMock)
        val response = clientService.save(clientMock)
        Assertions.assertEquals(response?.id, clientMock.id)
    }

    @Test
    fun saveClientFailed(){
        Assertions.assertThrows(Exception::class.java) {
            clientMock.apply { fullname=" "}
            Mockito.`when`(clientRepository.save(Mockito.any(Client::class.java))).thenReturn(clientMock)
            clientService.save(clientMock)
        }
    }
}