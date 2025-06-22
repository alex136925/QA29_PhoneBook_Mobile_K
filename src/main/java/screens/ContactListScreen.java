package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class ContactListScreen extends BaseScreen{
    public ContactListScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath="//*[@content-desc='More options']")
    AndroidElement menuOptions;

    @FindBy(xpath = "//*[@text = 'Logout']")
    AndroidElement logoutBtn;



    @FindBy (xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    AndroidElement activityTextView;

    public  boolean isActivityTitleDisplayed(String text){
       // return activityTextView.getText().contains("Contact list");
        return isShouldHave(activityTextView, text, 8);
    }

    public AuthenticationScreen logOut(){
        if (activityTextView.getText().equals("Contact list")) {
            menuOptions.click();
            logoutBtn.click();
        }
        return  new AuthenticationScreen(driver);
    }
}
