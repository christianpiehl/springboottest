package hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = { "database", "common", "hello" })
public class Application {

	public static void main(String[] args) {
//		startDatabase();
//		testDatabase();
		SpringApplication.run(Application.class, args);
	}

	private static void startDatabase() {
		try {
			//muss hier gestartet werden, damit man Zugriff auf die Embedded Datenbanken dieser JVM bekommt
			Server webServer = Server.createWebServer("-webAllowOthers", "-webPort", "8082").start();
			System.out.println(webServer.getStatus());
//			Server server = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
//			System.out.println(server.getStatus());
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	@SuppressWarnings("unused")
	private static void testDatabase() {
		try {
			Class.forName("org.h2.Driver");
			Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			Statement statement = conn.createStatement();
			ResultSet rset = statement.executeQuery("select * from INFORMATION_SCHEMA.TABLES");
			while (rset.next()) {
				String string = rset.getString(1);
				System.out.println(string);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//		return args -> {
//			System.out.println("Let's inspect the beans provided by Spring Boot:");
//			String[] beanNames = ctx.getBeanDefinitionNames();
//			Arrays.sort(beanNames);
//			for (String beanName : beanNames) {
//				System.out.println(beanName);
//			}
//		};
//	}
}
