package dk.cphbusiness.rest;

import dk.cphbusiness.controllers.PersonController;
import dk.cphbusiness.data.HibernateConfig;
import dk.cphbusiness.security.SecurityController;
import dk.cphbusiness.security.SecurityRoutes;
import io.javalin.apibuilder.EndpointGroup;

import static dk.cphbusiness.security.SecurityRoutes.Role;
import static io.javalin.apibuilder.ApiBuilder.*;

public class P07SecurityDemo {
    // 1. Hashing of passwords in security.User
    // 2. Login and register in SecurityController
    // 3. Authenticate in SecurityController
    // 4. Authorize in SecurityController
    // 5. SecurityRoutes (auth and protected)
    // 6. SecurityTest with Login and token send to protected
    public static void main(String[] args) {
        PersonController personController = new PersonController();
        ApplicationConfig
                .getInstance()
                .initiateServer()
                .setRoutes(SecurityRoutes.getSecurityRoutes())
                .setRoutes(SecurityRoutes.getSecuredRoutes())
                .setRoutes(getPersonRessource())
                .setCORS()
                .startServer(7007)
                .checkSecurityRoles()
                .setGeneralExceptionHandling()
                .setErrorHandling()
                .setApiExceptionHandling();
    }

    private static EndpointGroup getPersonRessource() {
        PersonController personController = new PersonController();
        SecurityController securityController = SecurityController.getInstance();
        return () -> {
            path("/person", () -> {
                before(securityController.authenticate());
                get("/", personController.getAll(), Role.USER);
                get("/{id}", personController.getById(), Role.USER);
            });
        };
    }
}