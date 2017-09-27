// Copyright (C) 2016-2017 the original author or authors.
// See the LICENCE.txt file distributed with this work for additional
// information regarding copyright ownership.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.cjwwdev.integrationtest

import com.cjwwdev.test.data.TestDataGenerator
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock._
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig
import com.github.tomakehurst.wiremock.stubbing.StubMapping
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach}
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneServerPerSuite
import play.api.Application
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.ws.{WSClient, WSRequest}

trait CJWWIntegrationSpec extends PlaySpec with GuiceOneServerPerSuite with BeforeAndAfterEach with BeforeAndAfterAll with TestDataGenerator {

  private val wireMockPort = 11111
  private val wireMockHost = "localhost"
  val wireMockUrl  = s"http://$wireMockHost:$wireMockPort"

  val additionConfiguration = Map(
    "microservice.external-services.auth-microservice.domain" -> s"$wireMockUrl/auth"
  )

  override implicit lazy val app: Application = new GuiceApplicationBuilder()
    .configure(additionConfiguration)
    .build()

  private val currentApplication = app.configuration.underlying.getString("appName")
  private val appUrl = app.configuration.underlying.getString(s"microservice.external-services.$currentApplication.domain")

  private val wmConfig = wireMockConfig().port(wireMockPort)
  private val wmServer = new WireMockServer(wmConfig)

  lazy val ws = app.injector.instanceOf(classOf[WSClient])

  def client(url: String): WSRequest = ws.url(s"$appUrl$url").withHeaders(
    "appId"     -> "abda73f4-9d52-4bb8-b20d-b5fffd0cc130",
    "contextId" -> generateTestSystemId(CONTEXT)
  )

  def wmGet(url: String, statusCode: Int, responseBody: String): StubMapping = {
    stubFor(get(urlMatching(url))
      .willReturn(
        aResponse()
          .withStatus(statusCode)
          .withBody(responseBody)
      )
    )
  }

  def startWmServer(): Unit = {
    wmServer.start()
    WireMock.configureFor(wireMockHost, wireMockPort)
  }

  def stopWmServer(): Unit = {
    wmServer.stop()
  }

  def resetWm(): Unit = {
    WireMock.reset()
  }

  override def beforeEach(): Unit = {
    resetWm()
  }

  override def beforeAll(): Unit = {
    startWmServer()
  }

  override def afterAll(): Unit = {
    stopWmServer()
  }
}
