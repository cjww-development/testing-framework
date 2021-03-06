/*
 * Copyright 2019 CJWW Development
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cjwwdev.testing.integration.wiremock

import java.net.ServerSocket

import org.slf4j.LoggerFactory
import play.api.Logger

import scala.annotation.tailrec

object Ports {
  private val logger = LoggerFactory.getLogger(getClass)

  val rnd       = new scala.util.Random
  val range     = 8000 to 39999
  val usedPorts = List[Int]()

  @tailrec
  def getRandomPort: Int = range(rnd.nextInt(range.length)) match {
    case 8080 => getRandomPort
    case 8090 => getRandomPort
    case port => if(available(port)) {
      logger.debug(s"Taking port: $port")
      usedPorts :+ port
      port
    } else {
      logger.debug(s"Port $port is in use, trying another")
      getRandomPort
    }
  }

  private def available(p: Int): Boolean = {
    var socket: ServerSocket = null
    try {
      if (!usedPorts.contains(p)) {
        socket = new ServerSocket(p)
        socket.setReuseAddress(true)
        true
      } else {
        false
      }
    } catch {
      case t: Throwable => false
    } finally {
      if (socket != null) socket.close()
    }
  }
}