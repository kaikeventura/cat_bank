package br.com.kaikeventura.cat_bank.command.service

import br.com.kaikeventura.cat_bank.command.model.BankAccount
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BankAccountService {

    fun getBankAccount(bankAccountId: UUID): BankAccount? {
        TODO()
    }

    fun updateBalance(it: BankAccount, movementAmount: Long) {
        TODO()
    }

    fun getCurrentBalance(bankAccountId: UUID): Long = 0L
}
