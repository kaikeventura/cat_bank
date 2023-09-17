package br.com.kaikeventura.cat_bank.common.utils

import java.lang.IllegalArgumentException

class CatDocumentValidator(
    private val catDocument: String
) {
    fun validate() {
        sizeValidation()
        prefixValidation()
        numbersValidation()
        kittenLetterValidation()
    }

    private fun sizeValidation() {
        if (catDocument.length != 12) {
            throw IllegalArgumentException("The size is not valid for cat document $catDocument")
        }
    }

    private fun prefixValidation() {
        catDocument.substring(0, 3).also {
            when (it) {
                MeowPrefix.MEOW.name -> return
                MeowPrefix.MWON.name -> return
                MeowPrefix.MEUO.name -> return
                MeowPrefix.MIAU.name -> return
                MeowPrefix.MIMI.name -> return
                else -> IllegalArgumentException("The prefix $it is not valid for cat document $catDocument")
            }
        }
    }

    private fun numbersValidation() {
        catDocument.substring(4, 11).also {
            val firstNumber = it[0]
            for (number in 1 until it.length) {
                if (it[number] != firstNumber) return
            }
            throw IllegalArgumentException("The numbers $it can not be equals for cat document $catDocument")
        }
    }

    private fun kittenLetterValidation() {
        catDocument.substring(12).also {
            when (it) {
                KittenLetter.K.name -> return
                KittenLetter.I.name -> return
                KittenLetter.T.name -> return
                KittenLetter.E.name -> return
                KittenLetter.N.name -> return
                else -> IllegalArgumentException("The kitten letter $it is not valid for cat document $catDocument")
            }
        }
    }
}

enum class MeowPrefix {
    MEOW, MWON, MEUO, MIAU, MIMI
}

enum class KittenLetter {
    K, I, T, E, N
}
