package uk.gov.ons

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

import uk.gov.ons.configuration.KeycloakConfiguration
import uk.gov.ons.controller.MessageController


object OauthClient {
  def main(args: Array[String]) : Unit = {
    SpringApplication.run(classOf[OauthClient], args :_ *)
  }
}

@SpringBootApplication
@ComponentScan(basePackageClasses = Array(
  classOf[MessageController],
  classOf[KeycloakConfiguration])
)
class OauthClient {}

