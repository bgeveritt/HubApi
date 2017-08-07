package com.blackducksoftware.api.example;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.blackducksoftware.integration.hub.api.project.ProjectRequestService;
import com.blackducksoftware.integration.hub.model.view.ProjectView;
import com.blackducksoftware.integration.hub.rest.CredentialsRestConnection;
import com.blackducksoftware.integration.hub.service.HubServicesFactory;
import com.blackducksoftware.integration.log.IntLogger;

public class HubConnectExample {

	public static void main(String[] args) throws Exception {
		// Establish connection with the Hub
        connectUser();
        // Return a list of all projects and output to console
        getProjects();
	}

	// declare variables
	private static URL hubBaseUrl;
	private final static String hubUsername = "username";
	private final static String hubPassword = "password";
	private final static int hubTimeout = 120;
	private final static IntLogger logger = null;

	// Get URL
	public static URL getUrl() throws IOException {
		hubBaseUrl = new URL("https://my.hubserver.com");
		return hubBaseUrl;
	}

	// Connect user
	public static void connectUser() throws Exception {
		getUrl();
		final CredentialsRestConnection testConnection = new CredentialsRestConnection(logger, hubBaseUrl, hubUsername,
				hubPassword, hubTimeout);
		testConnection.connect();
	}

	// Get connection
	public static CredentialsRestConnection getRestConnection() {
		final CredentialsRestConnection restConnection = new CredentialsRestConnection(logger, hubBaseUrl, hubUsername,
				hubPassword, hubTimeout);
		return restConnection;
	}
	
	// Output list of projects
	public static void getProjects() throws Exception {
		final HubServicesFactory hubServicesFactory = new HubServicesFactory(getRestConnection());
		final ProjectRequestService projectRequestService = hubServicesFactory.createProjectRequestService(null);

		final List<ProjectView> projectItems = projectRequestService.getAllProjects();
		System.out.println(String.format("project count: %d", projectItems.size()));
		for (final ProjectView projectItem : projectItems) {
			System.out.println("project: " + projectItem.toString());
		}
	}

}
