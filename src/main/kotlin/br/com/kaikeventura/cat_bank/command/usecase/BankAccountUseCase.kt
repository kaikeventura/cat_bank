package br.com.kaikeventura.cat_bank.command.usecase

import br.com.kaikeventura.cat_bank.command.model.BankAccount
import org.springframework.stereotype.Component

@Component
class BankAccountUseCase {

    fun createBankAccount(bankAccount: BankAccount) {
        TODO("Veririfcar se já existe esse cliente")
        TODO("Gerar um numero de conta")
        TODO("Salvar")
        TODO("Gerar comando de saldo via event sourcing")
    }
}
