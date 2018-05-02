package uk.gov.ons.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, ResponseBody, RestController}

@RestController
class MessageController {

  @Bean
  var template: OAuth2RestTemplate = _

  @Value("${secured.service.url}")
  var endpoint: String = _

  @RequestMapping(path = Array("/message"),
    method = Array(RequestMethod.GET),
    produces = Array(MediaType.TEXT_PLAIN_VALUE))
  @ResponseBody
  def getMessageFromSecuredService: String = {
    val entity = template.getForEntity(endpoint, classOf[String])
    entity.getBody
  }

}
