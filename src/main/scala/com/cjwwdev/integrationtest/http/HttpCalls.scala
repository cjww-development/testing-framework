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

package com.cjwwdev.integrationtest.http

import org.scalatestplus.play.guice.GuiceOneServerPerSuite
import play.api.libs.ws.{WSClient, WSRequest}

trait HttpCalls {
  self: GuiceOneServerPerSuite =>

  lazy val ws = app.injector.instanceOf(classOf[WSClient])

  val baseUrl: String

  def client(url: String): WSRequest = ws.url(url)
}