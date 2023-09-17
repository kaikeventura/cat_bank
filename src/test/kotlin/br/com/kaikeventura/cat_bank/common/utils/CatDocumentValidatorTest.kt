package br.com.kaikeventura.cat_bank.common.utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.lang.IllegalArgumentException

class CatDocumentValidatorTest {

    @Test
    fun `it should validate a valid cat document`() {
        val catDocument = "meow73525543k"
        CatDocumentValidator(catDocument).validate()
    }

    @Test
    fun `it should validate a invalid size cat document`() {
        val catDocument = "meow73525543sk"
        val exception = assertThrows(IllegalArgumentException::class.java) {
            CatDocumentValidator(catDocument).validate()
        }
        assertEquals("The size is not valid for cat document meow73525543sk", exception.message)
    }

    @Test
    fun `it should validate a invalid prefix cat document`() {
        val catDocument = "auau73525543k"
        val exception = assertThrows(IllegalArgumentException::class.java) {
            CatDocumentValidator(catDocument).validate()
        }
        assertEquals("The prefix auau is not valid for cat document auau73525543k", exception.message)
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "meow00000000k",
            "meow11111111k",
            "meow22222222k",
            "meow33333333k",
            "meow44444444k",
            "meow55555555k",
            "meow66666666k",
            "meow77777777k",
            "meow88888888k",
            "meow99999999k"
        ]
    )
    fun `it should validate a invalid numbers cat document`(catDocument: String) {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            CatDocumentValidator(catDocument).validate()
        }
        assertEquals("The numbers ${catDocument.substring(4, 12)} can not be equals for cat document $catDocument", exception.message)
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "meow10000000p",
            "meow01111111u",
            "meow32222222j"
        ]
    )
    fun `it should validate a invalid kitten letter cat document`(catDocument: String) {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            CatDocumentValidator(catDocument).validate()
        }
        assertEquals("The kitten letter ${catDocument.substring(12)} is not valid for cat document $catDocument", exception.message)
    }
}
