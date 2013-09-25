package edu.sjsu.cmpe.library;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import edu.sjsu.cmpe.library.api.resources.RootResource;

import edu.sjsu.cmpe.library.config.LibraryServiceConfiguration;

import edu.sjsu.cmpe.library.api.resources.Resources;

public class LibraryService extends Service<LibraryServiceConfiguration> {

    public static void main(String[] args) throws Exception {
	new LibraryService().run(args);
    }

    @Override
    public void initialize(Bootstrap<LibraryServiceConfiguration> bootstrap) {
	bootstrap.setName("library-service");
    }

    @Override
    public void run(LibraryServiceConfiguration configuration,
	    Environment environment) throws Exception {
	
	environment.addResource(RootResource.class);
    environment.addResource(Resources.class);
    }
}
