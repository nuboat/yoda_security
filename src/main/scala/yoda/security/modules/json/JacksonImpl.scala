/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.modules.json

import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.databind.{DeserializationFeature, PropertyNamingStrategies}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.typesafe.scalalogging.LazyLogging
import yoda.security.mvc.KnownException
import yoda.security.mvc.compoments.Json

import scala.reflect.runtime.universe.typeOf

/**
  * @author Peerapat A on Mar 26, 2019
  */
private[modules] class JacksonImpl extends Json
  with LazyLogging {

  private val mapper = JsonMapper.builder()
    .addModule(DefaultScalaModule)
    .serializationInclusion(Include.NON_NULL)
    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
    .build()

  override def toJson(obj: AnyRef): String = mapper.writeValueAsString(obj)

  override def toOption[T: Manifest](body: String): Option[T] = try {
    Option(mapper.readValue(body, new TypeReference[T]{}))
  } catch {
    case t: Throwable => throw KnownException("-1"
      , "Invalid Json String"
      , s"$body can not transfer to ${typeOf[T].typeSymbol.name}")
  }

}
