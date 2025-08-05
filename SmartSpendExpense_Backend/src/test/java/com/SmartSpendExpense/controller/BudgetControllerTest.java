package com.SmartSpendExpense.controller;

import com.SmartSpendExpense.model.User;
import com.SmartSpendExpense.repository.BudgetRepository;
import com.SmartSpendExpense.repository.UserRepository;
import com.SmartSpendExpense.service.BudgetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BudgetControllerTest {

    private MockMvc mockMvc;
    private BudgetService budgetService;
    private BudgetRepository budgetRepository;
    private UserRepository userRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String userId = "user123";
    private final String userEmail = "test@example.com";

    @BeforeEach
    void setUp() {
        budgetService = Mockito.mock(BudgetService.class);
        budgetRepository = Mockito.mock(BudgetRepository.class);
        userRepository = Mockito.mock(UserRepository.class);

        BudgetController controller = new BudgetController(budgetService, budgetRepository, userRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        // Mock authentication
        User mockUser = new User();
        mockUser.setId(userId);
        mockUser.setEmail(userEmail);
        when(userRepository.findByEmail(userEmail)).thenReturn(Optional.of(mockUser));

        // Mock Spring Security Context
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(
                new UsernamePasswordAuthenticationToken(userEmail, null, List.of())
        );
        SecurityContextHolder.setContext(securityContext);
    }

//    @Test
//    void setBudgetTest() throws Exception {
//        BudgetRequestDTO request = new BudgetRequestDTO();
//        request.setLimitAmount(BigDecimal.valueOf(1000.0));
//
//        BudgetResponseDTO response = new BudgetResponseDTO();
//        response.setLimitAmount(BigDecimal.valueOf(1000.0));
//
//        when(budgetService.getBudgets(anyString())).
//                thenReturn(List.of(request, response));
//
//        mockMvc.perform(post("/api/budgets/set")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.amount").value(1000.0));
//    }

//    @Test
//    void getBudgetsTest() throws Exception {
//        BudgetResponseDTO budget1 = new BudgetResponseDTO();
//        budget1.setLimitAmount(BigDecimal.valueOf(500.0));
//
//        BudgetResponseDTO budget2 = new BudgetResponseDTO();
//        budget2.setLimitAmount(BigDecimal.valueOf(1500.0));
//
//        when(budgetService.getBudgets(userId)).thenReturn(List.of(budget1, budget2));
//
//        mockMvc.perform(get("/api/budgets/all"))
//
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].amount").value(500.0))
//                .andExpect(jsonPath("$[1].amount").value(1500.0));
//    }

    @Test
    void deleteBudgetTest() throws Exception {
        Mockito.doNothing().when(budgetRepository).deleteById("budget123");

        mockMvc.perform(delete("/api/budgets/delete/{id}", "budget123")
                        .with(user(userEmail).roles("USER")))
                .andExpect(status().isNoContent());
    }
}
