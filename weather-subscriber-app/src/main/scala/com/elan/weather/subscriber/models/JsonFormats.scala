package com.elan.weather.subscriber.models

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.marshalling.Marshaller
import akka.http.scaladsl.model.{HttpEntity, MessageEntity}
import akka.http.scaladsl.unmarshalling.Unmarshaller
import spray.json.DefaultJsonProtocol._
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

object JsonFormats extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val weatherCriteriaFormat: RootJsonFormat[WeatherCriteria] = jsonFormat3(WeatherCriteria)
  implicit val subscriberFormat: RootJsonFormat[Subscriber] = jsonFormat6(Subscriber)

  implicit val subscriberUnmarshaller: Unmarshaller[HttpEntity, Subscriber] = sprayJsonUnmarshaller[Subscriber]
  implicit val subscriberMarshaller: Marshaller[Subscriber, MessageEntity] = sprayJsonMarshaller[Subscriber]
  implicit val subscribersMarshaller: Marshaller[Seq[Subscriber], MessageEntity] = sprayJsonMarshaller[Seq[Subscriber]]
}
