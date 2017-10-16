package com.shawandpartners.dogfans.http;

import io.swagger.jaxrs.config.BeanConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author xergio
 * @version 1 - 13.10.2017
 */
@ApplicationPath("api")
public class AppBase extends Application {

    public AppBase() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle("DogFans API");
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("192.81.130.21:8080");
        beanConfig.setBasePath("/dogfans/api");
        beanConfig.setResourcePackage("com.shawandpartners.dogfans.http");
        beanConfig.setScan(true);
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        addRestResourceClasses(resources);
        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);

        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(BreedController.class);
        resources.add(com.shawandpartners.dogfans.http.filter.UtilFilter.class);
    }
}
