package uk.gov.ons.configuration

import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.oauth2.client.OAuth2ClientContext
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client

@Configuration
@EnableOAuth2Client
@EnableWebSecurity
@ComponentScan(basePackageClasses = Array(classOf[KeycloakSecurityComponents]))
class KeycloakConfiguration @Autowired()(config: ServiceConfiguration) extends WebSecurityConfigurerAdapter {

  @throws[Exception]
  override def configure(web: WebSecurity): Unit = {
    web.ignoring.antMatchers("/**")
  }

  @throws[Exception]
  override protected def configure(http: HttpSecurity): Unit = {
    super.configure(http)
  }

  @Bean
  def oAuth2ProtectedResourceDetails () : OAuth2ProtectedResourceDetails = {
    val details : ClientCredentialsResourceDetails = new ClientCredentialsResourceDetails
    details.setClientId(config.clientId)
    details.setClientSecret(config.clientSecret)
    details.setAccessTokenUri(config.accessTokenUri)
    details
  }

  @Bean
  def createRestTemplate(clientContext: OAuth2ClientContext) =
    new OAuth2RestTemplate(oAuth2ProtectedResourceDetails(), clientContext)
}