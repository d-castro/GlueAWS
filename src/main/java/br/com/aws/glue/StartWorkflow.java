package br.com.aws.glue;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.glue.GlueClient;
import software.amazon.awssdk.services.glue.model.GlueException;
import software.amazon.awssdk.services.glue.model.StartWorkflowRunRequest;

public class StartWorkflow {

	public static void main(String[] args) {
		
		String workflowName = "Teste";
		Region region = Region.US_EAST_1;
		GlueClient glueClient = GlueClient.builder().region(region).build();

		startSpecificWorkflow(glueClient, workflowName);
		glueClient.close();	
		
	}

	public static void startSpecificWorkflow(GlueClient glueClient, String workflowName) {

		try {
			StartWorkflowRunRequest workflowRequest = StartWorkflowRunRequest.builder().name(workflowName).build();

			glueClient.startWorkflowRun(workflowRequest);

		} catch (GlueException e) {
			System.err.println(e.awsErrorDetails().errorMessage());
			System.exit(1);
		}
	}
}
