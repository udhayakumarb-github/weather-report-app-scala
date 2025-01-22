package com.elan.weather.subscriber.models

case class Subscriber(
                       firstName: String,
                       lastName: String,
                       email: String,
                       city: String,
                       country: String,
                       weatherCriteria: WeatherCriteria
                     )

case class WeatherCriteria(
                            minTemperature: Option[Double],
                            maxTemperature: Option[Double],
                            conditions: List[String]
                          )


