/*
 * Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 */

package yoda.security.modules.json

import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.databind.{DeserializationFeature, PropertyNamingStrategies}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.typesafe.scalalogging.LazyLogging
import yoda.security.definitions.Ref
import yoda.security.mvc.compoments.Json

/**
  * @author Peerapat A on Mar 26, 2019
  */
class JacksonImpl extends Json
  with LazyLogging {

  private val mapper = JsonMapper.builder()
    .addModule(DefaultScalaModule)
    .serializationInclusion(Include.NON_NULL)
    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
    .build

  override def toJson(obj: AnyRef): String = mapper.writeValueAsString(obj)

  override def to[T](body: String, ref: Ref[T]): T = {
    mapper.readValue(body, ref)
  }

  override def toOption[T](body: String, ref: Ref[T]): Option[T] = try {
    Option(mapper.readValue(body, ref))
  } catch {
    case t: Throwable => None
  }

  override def prettyStr(o: AnyRef): String = mapper
    .writerWithDefaultPrettyPrinter
    .writeValueAsString(o)

}
