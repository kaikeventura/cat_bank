package br.com.kaikeventura.cat_bank.command.controller

import br.com.kaikeventura.cat_bank.command.dto.request.BankAccountRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/bank-account")
class BankAccountController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createBankAccount(
        @RequestBody bankAccountRequest: BankAccountRequest
    ) {
        return
    }
}
