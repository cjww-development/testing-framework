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

package com.cjwwdev.testing.integration.application

import org.scalatest.TestSuite
import org.scalatestplus.play.guice.GuiceOneServerPerSuite
import play.api.Application
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.ws.WSClient

/**
  * IntegrationApplication builds an application and provides access to an injected
  * WSClient.
  *
  * override currentAppBaseUrl with the main uri for your application
  *
  * override appConfig to provide config key value pairs which are transformed into -D arguments
  * at build time.
  *
  * @author Chris J W Walker
  */
trait IntegrationApplication extends GuiceOneServerPerSuite {
  self: TestSuite =>

  val currentAppBaseUrl: String

  lazy val ws = app.injector.instanceOf(classOf[WSClient])

  lazy val testAppUrl = s"http://locahost:$port/$currentAppBaseUrl"

  val appConfig: Map[String, Any]

  override lazy val app: Application = new GuiceApplicationBuilder()
    .configure(appConfig)
    .build()
}
