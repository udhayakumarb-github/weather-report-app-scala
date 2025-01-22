package com.elan.weather.subscriber

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import com.elan.weather.subscriber.routes.SubscriberRoute
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext

object WeatherSubscriberApp extends App {
  private val logger = LoggerFactory.getLogger(this.getClass)

  implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "WeatherSubscriberSystem")
  implicit val ec: ExecutionContext = system.executionContext

  val bindingFuture = Http().newServerAt("localhost", 8080).bind(SubscriberRoute.routes(ec))

  bindingFuture.onComplete {
    case scala.util.Success(binding) =>
      val address = binding.localAddress
      logger.info(s"Weather Subscriber App running at http://${address.getHostString}:${address.getPort}/")
    case scala.util.Failure(exception) =>
      logger.error("Failed to bind HTTP endpoint, terminating system", exception)
      system.terminate()
  }
}
