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

package com.cjwwdev.testing.integration

import com.cjwwdev.testing.common.{FutureHelpers, JsonValidation}
import com.cjwwdev.testing.integration.http.ResponseHelpers
import com.cjwwdev.testing.integration.wiremock.{StubbedBasicHttpCalls, WireMockSetup}
import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, BeforeAndAfterEach}
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneServerPerSuite

/**
  * Convenience "super Suite" base class for unit testing.
  *
  * Extend this class by default for unit testing. You can mix other traits into it to access needed fixtures, such as
  * [[com.cjwwdev.testing.unit.application.FakeAppPerTest]] to gain access to a fake application with a test MessagesAPI.
  *
  * This super suite extends:
  *   - PlaySpec to gain access to must verbs
  *   - BeforeAndAfterX for being able to execute functions before and after test suites have ran
  *   - FutureHelpers provides access to FutureAwaits and DefaultAwaitTimeout along with a [[awaitAndAssert]] helper
  *   - JsonValidation provides [[play.api.libs.json.JsResult]] validation: must be error free and must have these errors
  *   - ResponseHelpers to evaluate [[play.api.libs.ws.WSResponse]]
  *   - StubbedBasicHttpCalls to make stubbed calls to services beyond the boundary of the current application
  *   - WireMockSetup to build, start, stop and reset a [[com.github.tomakehurst.wiremock.WireMockServer]]
  *
  * @author Chris J W Walker
  */
trait IntegrationTestSpec
  extends PlaySpec
    with BeforeAndAfter
    with BeforeAndAfterEach
    with BeforeAndAfterAll
    with GuiceOneServerPerSuite
    with FutureHelpers
    with JsonValidation
    with ResponseHelpers
    with StubbedBasicHttpCalls
    with WireMockSetup {

  override protected def beforeAll(): Unit = {
    super.beforeAll()
    startWm()
  }

  override protected def beforeEach(): Unit = {
    super.beforeEach()
    resetWm()
  }

  override protected def afterAll(): Unit = {
    super.afterAll()
    stopWm()
  }
}



