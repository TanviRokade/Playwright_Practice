import Pages.TestLogin;
import Pages.TestLogin;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class TestLoginFunctionality {
    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;
    TestLogin testLogin;

    @BeforeEach
    public void setUp() {

        playwright = Playwright.create();

        System.out.println("Starting Chrome...");

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setChannel("chrome")
        );

        System.out.println("Chrome started!");

        context = browser.newContext();
        page = context.newPage();

        testLogin = new TestLogin(page);
    }

    @Test
    public void ValidateTestLogin(){
        page.navigate("https://www.saucedemo.com");
        testLogin.performLogin();
        System.out.println("login Success!!");
        assertThat(page).hasURL("https://www.saucedemo.com/inventory.html");
    }
    @AfterEach
    public void tearDown(){
        page.close();
    }
}
