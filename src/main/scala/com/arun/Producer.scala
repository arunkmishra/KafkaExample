package com.arun

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

object Producer {

  type TopicName = String

  def main(args: Array[String]): Unit = {
    writeToKafka("hello-sample")
  }

  def setProducerProperties(): Properties = {
    val props: Properties = new Properties()

    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", classOf[CustomSerializer])
    props
  }

  def writeToKafka(topicName: TopicName): Unit = {
    val props = setProducerProperties()

    val producer = new KafkaProducer[String, User](props)
    val userObject = User(1, "arun", "dont try")
    println("Inside Producer Object")

    val user = "user"
    val message = "message"

    for(i <- 1 to 5) {
      val record = new ProducerRecord[String, User](topicName, s"i", User(i, s"$user + $i", s"$message + $i"))
      producer.send(record)
    }

    val record = new ProducerRecord[String, User](topicName, "1", userObject)
    producer.send(record)

    println("producer complete")
    producer.close()

  }

}
