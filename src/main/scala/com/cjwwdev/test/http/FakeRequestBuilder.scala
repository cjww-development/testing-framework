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
package com.cjwwdev.test.http

import com.cjwwdev.test.data.TestDataGenerator
import play.api.test.FakeRequest
import play.api.test.Helpers.{CONTENT_TYPE, TEXT}

trait FakeRequestBuilder {
  this: TestDataGenerator =>

  private val APP_ID = "appId"

  def buildStandardFakeRequest(appId: String): FakeRequest[_] = {
    FakeRequest()
      .withHeaders(
        APP_ID        -> appId,
        CONTENT_TYPE  -> TEXT
      )
  }

  def buildRequestWithSession(appId: String): FakeRequest[_] = {
    FakeRequest()
      .withHeaders(
        APP_ID        -> appId,
        CONTENT_TYPE  -> TEXT
      ).withSession(
        "cookieId"  -> generateTestSystemId(SESSION),
        "contextId" -> generateTestSystemId(CONTEXT),
        "firstName" -> "firstName",
        "lastName"  -> "lastName"
      )
  }
}
