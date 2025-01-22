package com.elan.weather.subscriber.services

import org.mongodb.scala._
import com.elan.weather.subscriber.utils.MongoDBUtil
import com.elan.weather.subscriber.models.{Subscriber, JsonFormats}
import spray.json._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object SubscriberService {
  import JsonFormats._

  val collection: MongoCollection[Document] = MongoDBUtil.collection

  def addSubscriber(subscriber: Subscriber): Future[Unit] = {
    val jsonString = subscriber.toJson.compactPrint // Serialize to JSON string
    val document = Document(jsonString) // Create MongoDB Document from JSON string
    collection.insertOne(document).toFuture().map(_ => ())
  }

  def getSubscribers: Future[Seq[Subscriber]] = {
    collection.find().toFuture().map { documents =>
      documents.map { doc =>
        doc.toJson().parseJson.convertTo[Subscriber] // Deserialize from JSON string
      }
    }.recover {
      case ex: Exception =>
        println(s"Error fetching subscribers: ${ex.getMessage}")
        Seq.empty[Subscriber]
    }
  }
}
