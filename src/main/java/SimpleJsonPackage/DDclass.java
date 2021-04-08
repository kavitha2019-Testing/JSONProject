package SimpleJsonPackage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

public class DDclass {
    public WebDriver driver;
    @BeforeMethod
    public void browser(){
        System.setProperty("webdriver.chrome.driver","C:/Users/kavitha/IdeaProjects/JSONProject/Drivers/chromedriver_win32/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        options.addArguments("enable-automation");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-browser-side-navigation");
        options.addArguments("--disable-gpu");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }
    @Test(dataProvider= "pass")
    public void testdata(String passdata) throws InterruptedException {
        driver.get("https://example.testproject.io/web/");
        Thread.sleep(3000);
        System.out.println("test project title is:"+ driver.getTitle());
        String pdata[] =  passdata.split(",");
        driver.findElement(By.xpath("//input[@id='name']")).sendKeys(pdata[0]);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(pdata[1]);
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("#login")).click();
    }
    @DataProvider(name="pass")
      public String[] dpdata() throws IOException, ParseException {
        JSONParser jsonparser = new JSONParser();
        FileReader file = new FileReader(".\\JsonFolder\\DDJsonFile");
        Object obj = jsonparser.parse(file);
        JSONObject loginobj =(JSONObject)obj;
        JSONArray array= (JSONArray) loginobj.get("userlogin");
        String arr[] = new String[array.size()];
        for(int i = 0;i<array.size();i++){
            JSONObject loginobject = (JSONObject) array.get(i);
            String uname = (String) loginobject.get("uername");
            String pword = (String) loginobject.get("password");
             arr[i] = uname+","+pword;
        }
        return arr;
    }
}
