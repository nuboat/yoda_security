/*
 * Copyright (C) 2020 Be ID Corporation Co., Ltd. <https://www.beid.io>
 */

package yoda.security.modules.metric

import java.net.{DatagramPacket, DatagramSocket, InetAddress}

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging
import javax.inject.Singleton
import play.libs.Scala
import yoda.security.mvc.compoments.BMetric

@Singleton
private[modules] class GraphiteMetric extends BMetric with LazyLogging {

  private val conf = ConfigFactory.load()
  private val host = Scala.Option(conf.getString("yoda.security.graphite.host"))
  private val port = Scala.Option(conf.getInt("yoda.security.graphite.port"))
  private val sock = new DatagramSocket()
  private val addr = InetAddress.getByName(host.orNull);

  override def info(id: String, metric: String, execution: Int): Unit = {
    logger.info(s"$id $metric $execution")

    if (host.isDefined) {
      val message = s"$metric \n".getBytes
      sock.send(new DatagramPacket(message, message.length, addr, port.getOrElse(2003)))
    }
  }

}
