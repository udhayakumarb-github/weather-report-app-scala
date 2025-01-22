package com.elan.weather.subscriber.utils

import org.mongodb.scala.*

object MongoDBUtil {
  val mongoClient: MongoClient = MongoClient("mongodb://localhost:27017")
  val database: MongoDatabase = mongoClient.getDatabase("weather_subscribers")
  val collection: MongoCollection[Document] = database.getCollection("subscribers")
}
