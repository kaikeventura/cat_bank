package br.com.kaikeventura.cat_bank.command.model

import java.util.UUID

data class BalanceMovementRequest(
    val amount: Long,
    val movementAction: MovementAction,
    val bankAccountId: UUID
) {
    init {
        if (amount <= 0) throw IllegalArgumentException("Movement amount must more than zero")
    }

    fun operationAmount() =
        when (movementAction) {
            MovementAction.INITIAL -> +amount
            MovementAction.DEPOSIT -> +amount
            MovementAction.WITHDRAW -> -amount
        }
}

enum class MovementAction {
    INITIAL,
    DEPOSIT,
    WITHDRAW
}

const val INITIAL_BALANCE: Long = 0L
