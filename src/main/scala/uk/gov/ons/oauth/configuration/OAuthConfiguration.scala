package uk.gov.ons.oauth.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.security.config.annotation.web.builders.{HttpSecurity, WebSecurity}
import org.springframework.security.config.annotation.web.configuration.{EnableWebSecurity, WebSecurityConfigurerAdapter}
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails
import org.springframework.security.oauth2.client.{OAuth2ClientContext, OAuth2RestTemplate}
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client

import scala.collection.JavaConverters

@Configuration
@EnableOAuth2Client
@EnableWebSecurity
class OAuthConfiguration (@Autowired val config: OAuthConfigurationProperties) extends WebSecurityConfigurerAdapter {

  @throws[Exception]
  override def configure(web: WebSecurity): Unit = {
    web.ignoring.antMatchers("/**")
  }

  @throws[Exception]
  override protected def configure(http: HttpSecurity): Unit = {
    super.configure(http)
  }

  @Bean
  def oAuth2ProtectedResourceDetails(): OAuth2ProtectedResourceDetails = {
    val details: ClientCredentialsResourceDetails =
      new ClientCredentialsResourceDetails
    details.setClientId(config.clientId)
    details.setClientSecret(config.clientSecret)
    details.setAccessTokenUri(config.accessTokenUri)

    val l: Array[String] = config.scope
      .split(",")
      .map(_.trim)

    val m = JavaConverters.seqAsJavaList(l)

    details.setScope(m)
    details
  }

  @Bean
  def createRestTemplate(clientContext: OAuth2ClientContext) =
    new OAuth2RestTemplate(oAuth2ProtectedResourceDetails(), clientContext)
}
