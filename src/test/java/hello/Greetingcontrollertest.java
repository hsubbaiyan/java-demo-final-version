package hello;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GreetingControllerTest {

    private GreetingController greetingController;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        greetingController = new GreetingController();
    }

    @Test
    void testGreeting() {
        String result = greetingController.greeting("John", model);
        assertEquals("greeting", result);
        verify(model).addAttribute("name", "John");
    }

    @Test
    void testWhoAmI() {
        String result = greetingController.whoAmI(model);
        assertEquals("greeting", result);
        verify(model).addAttribute("name", anyString());
    }
}