/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.modules.json

import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper, PropertyNamingStrategy}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.typesafe.scalalogging.LazyLogging
import yoda.security.mvc.KnownException
import yoda.security.mvc.compoments.BJson

import scala.reflect.runtime.universe.typeOf

/**
 * @author Peerapat A on Mar 26, 2019
 */
private[modules] class JacksonImpl extends BJson
  with LazyLogging {

  private val mapper = new ObjectMapper with ScalaObjectMapper

  mapper.registerModule(DefaultScalaModule)
  mapper.setSerializationInclusion(Include.NON_NULL)
  mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
  mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)

  override def toJson(obj: AnyRef): String = mapper.writeValueAsString(obj)

  override def toOption[T: Manifest](body: String): Option[T] = try {
    Option(mapper.readValue[T](body))
  } catch {
    case t: Throwable => throw KnownException("-1"
      , "Invalid Json String"
      , s"$body can not transfer to ${typeOf[T].typeSymbol.name}")
  }

}
