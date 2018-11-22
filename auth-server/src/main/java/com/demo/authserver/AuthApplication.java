package com.demo.authserver;

import com.demo.authserver.infrastructure.entities.*;
import com.google.common.collect.Sets;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

@EnableEurekaClient
@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AuthApplication.class, args);
        
		MongoTemplate mongoTemplate = (MongoTemplate) context.getBean(MongoTemplate.class);
        mongoTemplate.dropCollection(MongoUser.class);
        mongoTemplate.dropCollection(MongoClientDetails.class);
        mongoTemplate.dropCollection(MongoApproval.class);
        mongoTemplate.dropCollection(MongoAuthorizationCode.class);
        mongoTemplate.dropCollection(MongoAccessToken.class);
        mongoTemplate.dropCollection(MongoRefreshToken.class);

        MongoUser mongoUser = new MongoUser();
        mongoUser.setUsername("user");
        mongoUser.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("user"));
        mongoUser.setRoles(Sets.newHashSet("USER"));
        mongoTemplate.save(mongoUser);

        MongoClientDetails clientDetails = new MongoClientDetails();
        clientDetails.setClientId("web-client");
        clientDetails.setClientSecret("{noop}secret");//"web-client-secret");
        clientDetails.setSecretRequired(true);
        clientDetails.setResourceIds(Sets.newHashSet("project-man"));
        clientDetails.setScope(Sets.newHashSet("call-services"));
        clientDetails.setAuthorizedGrantTypes(Sets.newHashSet("authorization_code", "refresh_token"));
        clientDetails.setRegisteredRedirectUri(Sets.newHashSet("http://localhost:8080"));
        clientDetails.setAuthorities(AuthorityUtils.createAuthorityList("ROLE_USER"));
        clientDetails.setAccessTokenValiditySeconds(60);
        clientDetails.setRefreshTokenValiditySeconds(14400);
        clientDetails.setAutoApprove(false);
        mongoTemplate.save(clientDetails);
	}
}