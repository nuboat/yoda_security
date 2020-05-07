/*
 * Copyright (C) 2020 Be ID Corporation Co., Ltd. <https://www.beid.io>
 */

package yoda.security.modules.metric

import java.net.{DatagramPacket, DatagramSocket, InetAddress}

import com.typesafe.scalalogging.LazyLogging
import javax.inject.Singleton
import yoda.commons.Conf
import yoda.security.mvc.compoments.Metric

@Singleton
private[modules] class GraphiteMetric extends Metric with LazyLogging {

  private val host = Conf("yoda.security.graphite.host", null)
  private val port = Conf.int("yoda.security.graphite.host", 2003)
  private val sock = new DatagramSocket()
  private val addr = if(host != null) InetAddress.getByName(host) else null

  override def info(id: String, metric: String, execution: Int): Unit = {
    logger.info(s"$id $metric $execution")

    if (host != null) {
      val message = s"$metric \n".getBytes
      sock.send(new DatagramPacket(message, message.length, addr, port))
    }
  }

}
