package com.example.invoiceas.controller


import com.example.invoiceas.model.Detail
import com.example.invoiceas.service.DetailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/detail")
class DetailController {

    @Autowired
    lateinit var detailService: DetailService

    @GetMapping
    fun list():List<Detail>{
        return detailService.list()
    }

    @PostMapping
    fun save(@RequestBody detail: Detail): Detail?{
        return detailService.save(detail)

    }

    @PutMapping
    fun update(@RequestBody detail: Detail): ResponseEntity<Detail> {
        return ResponseEntity(detailService.update(detail), HttpStatus.ACCEPTED)
    }

    @PatchMapping
    fun updateTotal(@RequestBody detail: Detail): ResponseEntity<Detail> {
        return ResponseEntity(detailService.updateTotal(detail), HttpStatus.ACCEPTED)
    }

}
