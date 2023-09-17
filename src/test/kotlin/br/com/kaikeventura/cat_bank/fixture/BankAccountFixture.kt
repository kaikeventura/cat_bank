package br.com.kaikeventura.cat_bank.fixture

import br.com.kaikeventura.cat_bank.command.dto.request.BankAccountRequest
import br.com.kaikeventura.cat_bank.command.model.AccountType
import br.com.kaikeventura.cat_bank.command.model.CatBreed
import java.time.LocalDate

fun aBankAccountRequest(
    name: String = "Oracio",
    dateOfBirth: LocalDate = LocalDate.parse("2020-10-10"),
    catBreed: CatBreed = CatBreed.BIRMA_CAT_BREED,
    catDocument: String = "meow10925342k",
    accountTypes: Set<AccountType> = setOf(AccountType.CHECKING_ACCOUNT),
    initialBalance: Long = 0L
) = BankAccountRequest(
    name = name,
    dateOfBirth = dateOfBirth,
    catBreed = catBreed,
    catDocument = catDocument,
    accountTypes = accountTypes,
    initialBalance = initialBalance
)
