package uk.gov.ons.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.security.config.annotation.web.builders.{HttpSecurity, WebSecurity}
import org.springframework.security.config.annotation.web.configuration.{EnableWebSecurity, WebSecurityConfigurerAdapter}
import org.springframework.security.oauth2.client.{OAuth2ClientContext, OAuth2RestTemplate}
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client

@Configuration
@EnableOAuth2Client
@EnableWebSecurity
class OAuthConfiguration extends WebSecurityConfigurerAdapter {

  @Value("${security.oauth2.client.clientId}")
   var clientId : String = _

  @Value("${security.oauth2.client.clientSecret}")
   var clientSecret : String = _

  @Value("${security.oauth2.client.accessTokenUri}")
   var accessTokenUri : String = _

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
    details.setClientId(clientId)
    details.setClientSecret(clientSecret)
    details.setAccessTokenUri(accessTokenUri)
    details
  }

  @Bean
  def createRestTemplate(clientContext: OAuth2ClientContext) =
    new OAuth2RestTemplate(oAuth2ProtectedResourceDetails(), clientContext)
}