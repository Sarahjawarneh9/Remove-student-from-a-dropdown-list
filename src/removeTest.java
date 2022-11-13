import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class removeTest {

	public static void main(String[] args) throws IOException {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Desktop\\New folder\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://127.0.0.1:5500/index.html");

		List<WebElement> studentsName = driver.findElements(By.tagName("option"));

		int totalNumberOfStudents = studentsName.size();

		System.out.println("the orginal number is : " + totalNumberOfStudents );

		for (int i = 0; i < studentsName.size(); i++) {

			if (i % 2 != 0) {
				studentsName.get(i).click();
				driver.findElement(By.xpath("//*[@id=\"remove\"]")).click();
			}

		}

		List<WebElement> thestudentsAfterRemove = driver.findElements(By.tagName("option"));
		int ActualNumber = thestudentsAfterRemove.size();

		System.out.println("the new actual number after remove is : " + ActualNumber);

		int expectedIStudents = totalNumberOfStudents - 8;

		System.out.println("the number of students i have expected" + expectedIStudents );
		Assert.assertEquals(ActualNumber, expectedIStudents);

		Date currentDate = new Date();

		String TheAcutalDate = currentDate.toString().replace(":", "-");

		TakesScreenshot src = ((TakesScreenshot) driver);

		File SrcFile = src.getScreenshotAs((OutputType.FILE));

		File Dest = new File(".//mypictures/" + TheAcutalDate + ".png");

		FileUtils.copyFile(SrcFile, Dest);
	}

}
