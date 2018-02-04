/*
 *  Copyright 2018 CJWW Development
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.cjwwdev.integrationtest.wiremock

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock._
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig
import com.github.tomakehurst.wiremock.stubbing.StubMapping
import org.scalatestplus.play.guice.GuiceOneServerPerSuite

trait WireMockUtilities {
  self: GuiceOneServerPerSuite =>

  val wireMockPort = 11111
  val wireMockHost = "localhost"
  val wireMockUrl  = s"http://$wireMockHost:$wireMockPort"

  val currentAppUrl: String

  val appUrl = s"http://localhost:$port/$currentAppUrl"

  val wmConfig = wireMockConfig().port(wireMockPort)
  val wmServer = new WireMockServer(wmConfig)

  def startWmServer(): Unit = {
    wmServer.start()
    WireMock.configureFor(wireMockHost, wireMockPort)
  }

  def stopWmServer(): Unit = {
    wmServer.stop()
  }

  def resetWmServer(): Unit = {
    wmServer.resetAll()
    WireMock.reset()
  }

  def stubbedHead(url: String, statusCode: Int): StubMapping = {
    stubFor(
      head(urlMatching(url))
        .willReturn(
          aResponse()
            .withStatus(statusCode)
        )
    )
  }

  def stubbedGet(url: String, statusCode: Int, responseBody: String): StubMapping = {
    stubFor(
      get(urlMatching(url))
        .willReturn(
          aResponse()
            .withStatus(statusCode)
            .withBody(responseBody)
        )
    )
  }

  def stubbedPost(url: String, statusCode: Int, responseBody: String): StubMapping = {
    stubFor(
      post(urlMatching(url))
        .willReturn(
          aResponse()
            .withStatus(statusCode)
            .withBase64Body(responseBody)
        )
    )
  }

  def stubbedPut(url: String, statusCode: Int, responseBody: String): StubMapping = {
    stubFor(
      put(urlMatching(url))
        .willReturn(
          aResponse()
            .withStatus(statusCode)
            .withBody(responseBody)
        )
    )
  }

  def stubbedDelete(url: String, statusCode: Int, responseBody: String): StubMapping = {
    stubFor(
      delete(urlMatching(url))
        .willReturn(
          aResponse()
            .withStatus(statusCode)
            .withBody(responseBody)
        )
    )
  }
}
