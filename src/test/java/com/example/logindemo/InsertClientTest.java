package com.example.logindemo;

import com.example.logindemo.security.Repository.ClientRepository;
import com.example.logindemo.security.Repository.JpaRegisteredClientRepository;
import com.example.logindemo.security.models.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.test.annotation.Commit;

import java.util.UUID;

@SpringBootTest
public class InsertClientTest {
@Autowired
private BCryptPasswordEncoder bCryptPasswordEncoder;
@Autowired
private JpaRegisteredClientRepository clientRepository;


//@Test
//@Commit
//public void insertNewClientinDB(){
//    RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
//            .clientId("postman")
//            .clientSecret(bCryptPasswordEncoder.encode("postmanpassword"))
//            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//            .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//            .redirectUri("https://oauth.pstmn.io/v1/callback")
//            .postLogoutRedirectUri("http://127.0.0.1:8090/")
//            .scope(OidcScopes.OPENID)
//            .scope(OidcScopes.PROFILE)
//            .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
//            .build();
//
//        clientRepository.save(oidcClient);
//}
}
