import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class Test {

    private static WebDriver driver;

    public static void  main(String[] args){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        // Utilizar esperas implícitas para manejar elementos dinámicos en general.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Utilizar esperas explícitas para esperar un elemento específico.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Configurar el WebDriver y navegar a la página principal.
        driver.get("https://the-internet.herokuapp.com/");

        // Botones: Agregar un botón y verificar la aparición del botón "Delete".
        WebElement addremovepath = driver.findElement(By.cssSelector("#content > ul > li:nth-child(2) > a"));
        addremovepath.click();
        WebElement btnAdd = driver.findElement(By.xpath("//*[@id='content']/div/button"));
        btnAdd.click();;
        WebElement btnRemove = driver.findElement(By.className("added-manually"));
        if (btnRemove.isDisplayed()){
            System.out.println("El boton delete aparecio");
        }else {
            System.out.println("No aprecio el boton delete");
        }

        // Campos de Texto: Escribir un valor en el campo de texto y verificarlo.
        driver.navigate().to("https://the-internet.herokuapp.com/inputs");
        WebElement input = driver.findElement(By.tagName("input"));
        String value  = "99999";
        input.sendKeys(value);
        if (input.getAttribute("value").equals(value)){
            System.out.println("Input Correcto");
        }else {
            System.out.println("Input incorrecto");
        }

        // heckboxes: Seleccionar un checkbox y verificar su estado.
        driver.navigate().to("https://the-internet.herokuapp.com/checkboxes");
        WebElement checkBox1 = driver.findElement(By.xpath("//*[@id='checkboxes']/input[1]"));
        boolean isChecked = checkBox1.isSelected();
        if (!isChecked){
            checkBox1.click();
            System.out.println("Checkbox cambio de estado");
        }

        // Dropdowns: Seleccionar una opción del dropdown y verificar la opción seleccionada.
        driver.navigate().to("https://the-internet.herokuapp.com/dropdown");
        Select dropdowns = new Select(driver.findElement(By.id("dropdown")));
        dropdowns.selectByValue("2");
        WebElement option = dropdowns.getFirstSelectedOption();
        if (option.getText().equals("Option 2")){
            System.out.println("Option 2");
        }

        // Alertas: Activar y aceptar una alerta.
        driver.navigate().to("https://the-internet.herokuapp.com/javascript_alerts");
        WebElement btnAlert = driver.findElement(By.xpath("//*[@id=\"content\"]/div/ul/li[1]/button"));
        btnAlert.click();
        Alert alert = driver.switchTo().alert();
        String alertMessage = alert.getText();
        alert.accept();
        System.out.println(alertMessage);

        // Tablas: Extraer y verificar datos de una celda en la tabla.
        driver.navigate().to("https://the-internet.herokuapp.com/tables");
        WebElement emailCell = driver.findElement(By.xpath("//*[@id=\"table2\"]/tbody/tr[1]/td[3]"));
        boolean isEmail = emailCell.getText().contains("mail");
        if (isEmail) {
            System.out.println("Email: " + emailCell.getText());
        } else  {
            System.out.println("No se pudo recuperar el email");
        }

        // Carga de Archivos: Subir un archivo y verificar el mensaje de éxito.
        driver.navigate().to("https://the-internet.herokuapp.com/upload");
        WebElement inputUpload = driver.findElement(By.id("file-upload"));
        String filePath = "C:\\Users\\ferne\\IdeaProjects\\SeleniumInteractions\\.gitignore";
        inputUpload.sendKeys(filePath);
        WebElement btnUpload = driver.findElement(By.id("file-submit"));
        btnUpload.click();
        WebElement uploadMessage = driver.findElement(By.tagName("h3"));
        System.out.println(uploadMessage.getText());

        // Dynamically Loaded.
        driver.navigate().to("https://the-internet.herokuapp.com/dynamic_loading/2");
        WebElement btnLoader = driver.findElement(By.tagName("button"));
        btnLoader.click();
        WebElement finishMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
        if (finishMessage.getText().toLowerCase().contains("hello")) {
            System.out.println("Resultado de la espera: " + finishMessage.getText());
        } else {
            System.out.println("Resultado de la espera no visible");
        }

        driver.close();
    }

}
