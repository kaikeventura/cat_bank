package br.com.kaikeventura.cat_bank.command.model

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

data class BankAccount(
    val id: UUID,
    val name: String,
    val dateOfBirth: LocalDate,
    val catBreed: CatBreed,
    val catDocument: String,
    val accountTypes: Set<AccountType>,
    val balance: Long,
    val status: Status,
    val accountNumber: Long,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val modifiedAt: LocalDateTime?
)

enum class Status {
    ENABLED,
    DISABLED,
    BLOCKED,
    CANCELED
}
