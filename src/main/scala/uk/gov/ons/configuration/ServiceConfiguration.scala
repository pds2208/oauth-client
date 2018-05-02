package uk.gov.ons.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

import scala.beans.BeanProperty

@Component
@ConfigurationProperties("security.oauth2.client")
class ServiceConfiguration {

  @BeanProperty var clientId : String = _
  @BeanProperty var clientSecret : String = _
  @BeanProperty var accessTokenUri : String = _
  @BeanProperty var userAuthorizationUri : String = _
}
