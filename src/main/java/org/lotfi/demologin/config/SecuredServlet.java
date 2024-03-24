package org.lotfi.demologin.config;

import jakarta.annotation.security.DeclareRoles;
import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage="/faces/login.xhtml",
                errorPage=""
        )
)

@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "java:global/DataSourceName",
        callerQuery = "select password from userentity where email = ?",
        groupsQuery = "select g.NAME from USER_GROUPS ug, USERENTITY u, GROUPS g where ug.USER_ID = u.USERID and g.GROUP_ID= ug.GROUP_ID and u.EMAIL=?",
        hashAlgorithm = Pbkdf2PasswordHash.class,
        hashAlgorithmParameters = {
                "Pbkdf2PasswordHash.Iterations=3072",
                "Pbkdf2PasswordHash.Algorithm=PBKDF2WithHmacSHA512",
                "Pbkdf2PasswordHash.SaltSizeBytes=64"
        }
)
@DeclareRoles({"user", "admin"})
@WebServlet("/securedServlet")
@ServletSecurity(
        @HttpConstraint(rolesAllowed = {"admin"}))
public class SecuredServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().write("Congratulations, login successful.");
    }
}
