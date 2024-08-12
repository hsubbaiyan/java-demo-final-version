package hello;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

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
        String name = "John";
        String expectedViewName = "greeting";

        String actualViewName = greetingController.greeting(name, model);

        assertEquals(expectedViewName, actualViewName);
        verify(model).addAttribute("name", name);
    }

    @Test
    void testWhoAmI() {
        String expectedViewName = "greeting";

        String actualViewName = greetingController.whoAmI(model);

        assertEquals(expectedViewName, actualViewName);
        verify(model).addAttribute(eq("name"), anyString());
    }
}