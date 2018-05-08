package uk.gov.ons

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

import uk.gov.ons.configuration.OAuthConfiguration
import uk.gov.ons.controller.MessageController

object OAuthClient {
  def main(args: Array[String]) : Unit = {
    SpringApplication.run(classOf[OAuthClient], args :_ *)
  }
}

@SpringBootApplication
@ComponentScan(basePackageClasses = Array(
  classOf[MessageController],
  classOf[OAuthConfiguration])
)
class OAuthClient {}

