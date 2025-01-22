package com.elan.weather.subscriber.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.StatusCodes
import com.elan.weather.subscriber.models.{Subscriber, JsonFormats}
import com.elan.weather.subscriber.services.SubscriberService
import spray.json._

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

object SubscriberRoute {
  import JsonFormats._

  def routes(implicit ec: ExecutionContext): Route = pathPrefix("subscribers") {
    pathEndOrSingleSlash {
      post {
        entity(as[Subscriber]) { subscriber =>
          onComplete(SubscriberService.addSubscriber(subscriber)) {
            case Success(_) => complete(StatusCodes.Created, "Subscriber added")
            case Failure(ex) => complete(StatusCodes.InternalServerError, s"An error occurred: ${ex.getMessage}")
          }
        }
      } ~
        get {
          onComplete(SubscriberService.getSubscribers) {
            case Success(subscribers) => complete(subscribers)
            case Failure(ex) => complete(StatusCodes.InternalServerError, s"An error occurred: ${ex.getMessage}")
          }
        }
    }
  }
}
