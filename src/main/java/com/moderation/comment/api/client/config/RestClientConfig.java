package com.moderation.comment.api.client.config;

import com.moderation.comment.api.client.ModerateCommentClient;
import com.moderation.comment.api.client.RestClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {

  @Bean
  public ModerateCommentClient commentClient(RestClientFactory factory) {
    RestClient restClient = factory.moderateCommentRestClient();
    RestClientAdapter adapter = RestClientAdapter.create(restClient);
    HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
    return proxyFactory.createClient(ModerateCommentClient.class);
  }
}
