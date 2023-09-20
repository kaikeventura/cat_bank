package br.com.kaikeventura.cat_bank.command.model

import java.time.LocalDateTime
import java.util.UUID

data class BalanceMovement(
    val id: UUID,
    val action: MovementAction,
    val amount: Long,
    val bankAccount: BankAccount,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime? = null
)
