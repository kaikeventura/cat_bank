package br.com.kaikeventura.cat_bank.command.controller

import br.com.kaikeventura.cat_bank.command.dto.request.BankAccountRequest
import br.com.kaikeventura.cat_bank.fixture.aBankAccountRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(BankAccountController::class)
class BankAccountControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `it should validate a valid bank account request dto`() {
        val bankAccountRequest = aBankAccountRequest(initialBalance = 10_00L)
        createBankAccountRequest(bankAccountRequest)
            .andExpect(MockMvcResultMatchers.status().isCreated)
    }

    private fun createBankAccountRequest(request: BankAccountRequest) =
        mockMvc.perform(
            MockMvcRequestBuilders.post("/v1/bank-account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(request))
        )
}
