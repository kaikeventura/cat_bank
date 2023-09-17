package br.com.kaikeventura.cat_bank.command.dto.request

import br.com.kaikeventura.cat_bank.command.model.AccountType
import br.com.kaikeventura.cat_bank.command.model.CatBreed
import br.com.kaikeventura.cat_bank.common.utils.CatDocumentValidator
import java.lang.IllegalArgumentException
import java.time.LocalDate

data class BankAccountRequest(
    val name: String,
    val dateOfBirth: LocalDate,
    val catBreed: CatBreed,
    val catDocument: String,
    val accountTypes: List<AccountType>,
    val initialBalance: Long
) {
    init {
        validate()
    }

    private fun validate() {
        CatDocumentValidator(catDocument).validate()
        balanceValidation()
    }

    private fun balanceValidation() {
        if (initialBalance <= 0) {
            throw IllegalArgumentException("The initial balance must more than zero")
        }
    }
}
