package com.arun

import java.util
import java.time.Duration
import java.util.{Properties, UUID}
import java.util.regex.Pattern

import scala.collection.JavaConverters._
import org.apache.kafka.clients.consumer.{ConsumerRecord, KafkaConsumer}


object Consumer {

  def main(args: Array[String]): Unit = {
    consumeFromKafka("hello-sample")
  }

  def consumeFromKafka(topic: String): Unit = {
    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", classOf[CustomDeserializer])
    // import java.util.UUID
     props.put("group.id", UUID.randomUUID.toString)
    props.put("auto.offset.reset", "latest")
    println("inside consumer")
    val consumer: KafkaConsumer[String, User] = new KafkaConsumer[String, User](props)
    val pattern: Pattern = Pattern.compile(topic)

    consumer.subscribe(util.Arrays.asList(topic))

    println("inside subscriber")
    while (true) {
      print("enter")
      val dur: Duration = Duration.ofSeconds(10)
      val record: Iterable[ConsumerRecord[String, User]] = consumer.poll(dur).asScala.seq
      println("true")
      record.
      for(v <- record)
        println(v.value())
    }
  }

}
