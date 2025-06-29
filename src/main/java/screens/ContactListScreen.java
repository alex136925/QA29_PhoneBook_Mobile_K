package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

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

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/add_contact_btn']")
    AndroidElement plusBtn;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowName']")
    List<AndroidElement>contactNameList;

    @FindBy(xpath = "//*[resource-id='com.sheygam.contactapp:id/rowContainer']")
    List<AndroidElement>contactList;

    @FindBy(id="android:id/button1")
    AndroidElement yesBtn;

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

    public ContactListScreen isAccountOpened(){
        Assert.assertTrue(isActivityTitleDisplayed("Contact list"));
        return this;
    }

    public AddNewContactScreen openContactForm() {
        try {
            plusBtn.click();
        } catch (Exception e) {
            System.out.println("First attempt to click failed, trying again: " + e.getMessage());
            plusBtn.click();
        }
        return new AddNewContactScreen(driver);
    }


    public ContactListScreen isContactAddedByName(String name, String lastName){
        isShouldHave(activityTextView, "Contact list", 5);
        boolean isPresent = false;

        for (AndroidElement element:contactNameList){
            if (element.getText().equals(name + " " + lastName)){
                isPresent = true;
                break;
            }
        }

        Assert.assertTrue(isPresent);
        return this;
    }

    public ContactListScreen deleteFirstContact(){
        AndroidElement first = contactList.get(0);
        Rectangle rectangle = first.getRect();
        int xFrom = rectangle.getX()+ rectangle.getWidth()/8;
        int y = rectangle.getY()+ rectangle.getHeight()/2;
        int xTo = rectangle.getWidth()-xFrom;

        TouchAction<?>touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(xFrom,y))
                .moveTo(PointOption.point(xTo,y))
                .release().perform();



        return this;
    }

    public ContactListScreen isListSizeLessOnOne(){

        int countBefore = contactList.size();

        deleteFirstContact();

        int countAfter = contactList.size();

        Assert.assertEquals(countBefore - countAfter, 1);
        return this;
    }


}
