package br.com.kaikeventura.cat_bank.common.utils

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
        if (catDocument.length != 13) {
            throw IllegalArgumentException("The size is not valid for cat document $catDocument")
        }
    }

    private fun prefixValidation() {
        catDocument.substring(0, 4).also {
            when (it) {
                MeowPrefix.MEOW.name.lowercase() -> return
                MeowPrefix.MWON.name.lowercase() -> return
                MeowPrefix.MEUO.name.lowercase() -> return
                MeowPrefix.MIAU.name.lowercase() -> return
                MeowPrefix.MIMI.name.lowercase() -> return
                else -> throw IllegalArgumentException("The prefix $it is not valid for cat document $catDocument")
            }
        }
    }

    private fun numbersValidation() {
        catDocument.substring(4, 12).also {
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
                KittenLetter.K.name.lowercase() -> return
                KittenLetter.I.name.lowercase() -> return
                KittenLetter.T.name.lowercase() -> return
                KittenLetter.E.name.lowercase() -> return
                KittenLetter.N.name.lowercase() -> return
                else -> throw IllegalArgumentException("The kitten letter $it is not valid for cat document $catDocument")
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
