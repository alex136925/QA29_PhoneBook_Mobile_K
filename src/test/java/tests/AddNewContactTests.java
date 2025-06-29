package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class AddNewContactTests extends AppiumConfig {

    @BeforeClass
    public  void preCondition(){
    new AuthenticationScreen(driver)
            .fillLoginRegistrationForm(Auth.builder().email("margo12@gmail.com").password("Mmar123456$").build())
            .submitLogin();
    }


    @Test
    public void createNewContactSuccess(){
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Simon")
                .lastName("Wow" + i)
                .email("wow" + i + "@gmail.com")
                .phone("6789456" + i)
                .address("Chicago, IL, USA")
                .description("The best")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(), contact.getLastName());
    }

    @Test
    public void createNewContactSuccessReq(){
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Simon")
                .lastName("Wow" + i)
                .email("wow" + i + "@gmail.com")
                .phone("6789456" + i)
                .address("Chicago, IL, USA")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(), contact.getLastName());

    }

    @Test
    public void createContactWithEmptyName(){
        Contact contact = Contact.builder()
                .name("")
                .lastName("Wow")
                .email("wow" + "@gmail.com")
                .phone("6789456")
                .address("Chicago, IL, USA")
                .description("The best")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorMessageHasText("must");

    }


    @Test
    public void createNewContactWithEmptyLastName(){
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Simon")
                .lastName(" ")
                .email("wow" + i + "@gmail.com")
                .phone("6789456" + i)
                .address("Chicago, IL, USA")
                .description("The best")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorMessageHasText("must not");
    }

    @Test
    public void createNewContactWithEmptyEmail(){
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Simon")
                .lastName("Wow" + i)
                .email(" ")
                .phone("6789456" + i)
                .address("Chicago, IL, USA")
                .description("The best")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorMessageHasText("must");
    }

    @Test
    public void createNewContactWithEmptyPhone(){
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Simon")
                .lastName("Wow" + i)
                .email("wow" + i + "@gmail.com")
                .phone(" ")
                .address("Chicago, IL, USA")
                .description("The best")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorMessageHasText("must");
    }

    @Test
    public void createNewContactWithEmptyAddress(){
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Simon")
                .lastName("Wow" + i)
                .email("wow" + i + "@gmail.com")
                .phone("6789456" + i)
                .address(" ")
                .description("The best")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorMessageHasText("must");
    }

    @Test
    public void createNewContactWithWrongEmail(){
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Simon")
                .lastName("Wow" + i)
                .email("wow" + i + "gmail.com")
                .phone("6789456" + i)
                .address("Chicago, IL, USA")
                .description("The best")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorMessageHasText("must be a well-formed");
    }

    @Test
    public void createNewContactWithWrongPhone(){
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Simon")
                .lastName("Wow" + i)
                .email("wow" + i + "@gmail.com")
                .phone("6786123456790" + i)
                .address("Chicago, IL, USA")
                .description("The best")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorMessageHasText("must contain");
    }


    @AfterClass
    public void postCondition(){

    }

}
