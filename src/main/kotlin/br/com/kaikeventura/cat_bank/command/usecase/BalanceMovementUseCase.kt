package br.com.kaikeventura.cat_bank.command.usecase

import br.com.kaikeventura.cat_bank.command.model.BalanceMovement
import br.com.kaikeventura.cat_bank.command.model.BalanceMovementRequest
import br.com.kaikeventura.cat_bank.command.model.INITIAL_BALANCE
import br.com.kaikeventura.cat_bank.command.model.MovementAction
import br.com.kaikeventura.cat_bank.command.service.MovementService
import br.com.kaikeventura.cat_bank.command.service.BankAccountService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.time.LocalDateTime.now
import java.util.UUID.randomUUID

@Component
class BalanceMovementUseCase(
    private val bankAccountService: BankAccountService,
    private val movementService: MovementService
) {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun handleMovement(request: BalanceMovementRequest) {
        when (request.movementAction) {
            MovementAction.INITIAL -> handleInitialBalance(request)
            MovementAction.DEPOSIT -> handleDeposit(request)
            MovementAction.WITHDRAW -> handleWithdraw(request)
        }
    }

    private fun handleInitialBalance(request: BalanceMovementRequest) {
        bankAccountService.getBankAccount(request.bankAccountId)?.let { bankAccount ->
            val balanceMovement = BalanceMovement(
                id = randomUUID(),
                action = request.movementAction,
                amount = INITIAL_BALANCE,
                bankAccount = bankAccount,
                createdAt = now()
            )

            runCatching {
                logger.info("Starting ${request.movementAction} balance movement for bank account ${request.bankAccountId}")
                movementService(balanceMovement)
                bankAccountService.updateBalance(bankAccount, request.operationAmount())
            }.onSuccess {
                logger.info("Finished ${request.movementAction} balance movement for bank account ${request.bankAccountId}")
            }.onFailure {
                logger.error("Error when trying ${request.movementAction} balance movement for bank account ${request.bankAccountId}", it)
                throw it
            }
        } ?: throw RuntimeException("Bank account ${request.bankAccountId} not found")
    }

    private fun handleDeposit(request: BalanceMovementRequest) {
        bankAccountService.getBankAccount(request.bankAccountId)?.let { bankAccount ->
            val balanceMovement = BalanceMovement(
                id = randomUUID(),
                action = request.movementAction,
                amount = request.amount,
                bankAccount = bankAccount,
                createdAt = now()
            )

            runCatching {
                logger.info("Starting ${request.movementAction} balance movement for bank account ${request.bankAccountId}")
                movementService(balanceMovement)
                bankAccountService.updateBalance(bankAccount, request.operationAmount())
            }.onSuccess {
                logger.info("Finished ${request.movementAction} balance movement for bank account ${request.bankAccountId}")
            }.onFailure {
                logger.error("Error when trying ${request.movementAction} balance movement for bank account ${request.bankAccountId}", it)
                throw it
            }
        } ?: throw RuntimeException("Bank account ${request.bankAccountId} not found")
    }

    private fun handleWithdraw(request: BalanceMovementRequest) {
        bankAccountService.getBankAccount(request.bankAccountId)?.let { bankAccount ->
            validateAvailableBalance(request)

            val balanceMovement = BalanceMovement(
                id = randomUUID(),
                action = request.movementAction,
                amount = request.amount,
                bankAccount = bankAccount,
                createdAt = now()
            )

            runCatching {
                logger.info("Starting ${request.movementAction} balance movement for bank account ${request.bankAccountId}")
                movementService(balanceMovement)
                bankAccountService.updateBalance(bankAccount, request.operationAmount())
            }.onSuccess {
                logger.info("Finished ${request.movementAction} balance movement for bank account ${request.bankAccountId}")
            }.onFailure {
                logger.error("Error when trying ${request.movementAction} balance movement for bank account ${request.bankAccountId}", it)
                throw it
            }
        } ?: throw RuntimeException("Bank account ${request.bankAccountId} not found")
    }

    private fun validateAvailableBalance(request: BalanceMovementRequest) {
        bankAccountService.getCurrentBalance(request.bankAccountId).also {
            if (request.amount > it)
                throw RuntimeException("Insufficient balance for withdraw ${request.amount} for bank account ${request.bankAccountId}")
        }
    }
}
