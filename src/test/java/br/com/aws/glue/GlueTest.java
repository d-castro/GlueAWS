package br.com.aws.glue;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.glue.GlueClient;

import br.com.aws.glue.StartWorkflow;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GlueTest {

	private static GlueClient glueClient;
	private static String workflowName = "";

	@BeforeAll
	public static void setUp() throws IOException, URISyntaxException {

		Region region = Region.US_EAST_1;
		glueClient = GlueClient.builder().region(region).build();

		try (InputStream input = GlueTest.class.getClassLoader().getResourceAsStream("config.properties")) {

			Properties prop = new Properties();

			if (input == null) {
				System.out.println("Sorry, unable to find config.properties");
				return;
			}

			// load a properties file from class path, inside static method
			prop.load(input);

			// Populate the data members required for all tests
			workflowName = prop.getProperty("workflowName");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	@Order(1)
	public void StartWorkflow() {

		StartWorkflow.startSpecificWorkflow(glueClient, workflowName);
		System.out.println("Test 1 passed");
	}

}