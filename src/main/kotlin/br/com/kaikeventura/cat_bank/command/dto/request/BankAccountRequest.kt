package br.com.kaikeventura.cat_bank.command.dto.request

import br.com.kaikeventura.cat_bank.command.model.AccountType
import br.com.kaikeventura.cat_bank.command.model.CatBreed
import br.com.kaikeventura.cat_bank.common.utils.CatDocumentValidator
import java.time.LocalDate

data class BankAccountRequest(
    val name: String,
    val dateOfBirth: LocalDate,
    val catBreed: CatBreed,
    val catDocument: String,
    val accountTypes: Set<AccountType>
) {
    init {
        validate()
    }

    private fun validate() {
        CatDocumentValidator(catDocument).validate()
    }
}
