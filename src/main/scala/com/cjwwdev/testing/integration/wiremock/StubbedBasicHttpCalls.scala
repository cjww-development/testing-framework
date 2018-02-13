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

package com.cjwwdev.testing.integration.wiremock

import com.github.tomakehurst.wiremock.stubbing.StubMapping
import com.github.tomakehurst.wiremock.client.WireMock._

/**
  * StubbedBasicHttpCalls sets up stubbed
  *   - HEAD
  *   - GET
  *   - POST
  *   - PATCH
  *   - PUT
  *   - DELETE
  * requests
  *
  * @author Chris J W Walker
  */
trait StubbedBasicHttpCalls {

  /**
    * @param url String
    * @param statusCode Int
    * @return StubMapping
    */
  def stubbedHead(url: String, statusCode: Int): StubMapping = {
    stubFor(head(urlMatching(url))
      .willReturn(
        aResponse()
          .withStatus(statusCode)
      )
    )
  }

  /**
    * @param url String
    * @param statusCode Int
    * @param responseBody String
    * @return StubMapping
    */
  def stubbedGet(url: String, statusCode: Int, responseBody: String): StubMapping = {
    stubFor(get(urlMatching(url))
      .willReturn(
        aResponse()
          .withStatus(statusCode)
          .withBody(responseBody)
      )
    )
  }

  /**
    * @param url String
    * @param statusCode Int
    * @param responseBody String
    * @return StubMapping
    */
  def stubbedPost(url: String, statusCode: Int, responseBody: String): StubMapping = {
    stubFor(post(urlMatching(url))
      .willReturn(
        aResponse()
          .withStatus(statusCode)
          .withBase64Body(responseBody)
      )
    )
  }

  /**
    * @param url String
    * @param statusCode Int
    * @param responseBody String
    * @return StubMapping
    */
  def stubbedPut(url: String, statusCode: Int, responseBody: String): StubMapping = {
    stubFor(put(urlMatching(url))
      .willReturn(
        aResponse()
          .withStatus(statusCode)
          .withBody(responseBody)
      )
    )
  }

  /**
    * @param url String
    * @param statusCode Int
    * @param responseBody String
    * @return StubMapping
    */
  def stubbedPatch(url: String, statusCode: Int, responseBody: String): StubMapping = {
    stubFor(patch(urlMatching(url))
      .willReturn(
        aResponse()
          .withStatus(statusCode)
          .withBody(responseBody)
      )
    )
  }

  /**
    * @param url String
    * @param statusCode Int
    * @param responseBody String
    * @return StubMapping
    */
  def stubbedDelete(url: String, statusCode: Int, responseBody: String): StubMapping = {
    stubFor(delete(urlMatching(url))
      .willReturn(
        aResponse()
          .withStatus(statusCode)
          .withBody(responseBody)
      )
    )
  }
}
