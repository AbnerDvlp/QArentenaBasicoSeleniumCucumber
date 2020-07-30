package aula;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.es.Dado;
import cucumber.api.java.it.Quando;
import cucumber.api.java.pt.Entao;

public class Consulta {

	String url;
	WebDriver driver;

	@Before
	public void iniciar() {
		url = "https://www.iterasys.com.br";
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Convidado\\eclipse-workspace\\Aula\\src\\drivers\\chrome\\84\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MILLISECONDS);
	}

	@After
	void finalizar() {
		driver.quit();
	}

	@Dado("^que o cliente acessa o site$")
	public void que_o_cliente_acessa_o_site() throws Throwable {
		driver.get(url);
	}

	@Quando("^realizo uma consulta por \"([^\"]*)\"$")
	public void realizo_uma_consulta_por(String termo) throws Throwable {
		driver.findElement(By.id("searchtext")).clear();
		driver.findElement(By.id("searchtext")).sendKeys(termo);
		driver.findElement(By.id("searchtext")).sendKeys(Keys.ENTER);
	}

	@Entao("^exibe um conjunto de cursos$")
	public void exibe_um_conjunto_de_cursos() throws Throwable {
		//Não esta validando a lista apenas o titulo da aba
		assertEquals("Iterasys - Cursos de Teste e QA",driver.getTitle());
	}

	@Quando("^clico no curso Inicio Rapido em Teste de Software$")
	public void clico_no_curso_Inicio_Rapido_em_Teste_de_Software() throws Throwable {
		driver.findElement(By.cssSelector("spam.mais")).click();
	}

	@Entao("^valido que a duracao do curso sera de \"([^\"]*)\" horas$")
	public void valido_que_a_duracao_do_curso_sera_de_horas(String arg1) throws Throwable {
		//assertEquals("60 horas de duracao",driver.findElement(By.cssSelector("li.carga-horaria")).getText());
		assertEquals("060 horas",driver.findElement(By.cssSelector("span.subtitulo")).getText());
	}

}
