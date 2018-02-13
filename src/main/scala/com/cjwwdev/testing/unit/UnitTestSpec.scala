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

package com.cjwwdev.testing.unit

import com.cjwwdev.testing.common.{FormValidation, FutureHelpers, JsonValidation}
import com.cjwwdev.testing.unit.helpers.FakeMongoResults
import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, BeforeAndAfterEach}
import org.scalatest.mockito.MockitoSugar
import org.scalatestplus.play.PlaySpec
import play.api.http.{HeaderNames, HttpProtocol, MimeTypes, Status}
import play.api.test._

import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Convenience "super Suite" base class for unit testing.
  *
  * Extend this class by default for unit testing. You can mix other traits into it to access needed fixtures, such as
  * [[com.cjwwdev.testing.unit.application.FakeAppPerTest]] to gain access to a fake application with a test MessagesAPI.
  *
  * This super suite extends:
  *   - PlaySpec to gain access to must verbs
  *   - BeforeAndAfterX for being able to execute functions before and after test suites have ran
  *   - HeaderNames, Status, MimeTypes, HttpProtocol to gain access to prefined headers, http status codes and mime types
  *   - Result extractors to evaluate the status, body and headers of a [[scala.concurrent.Future[Result]]
  *   - FutureHelpers provides access to FutureAwaits and DefaultAwaitTimeout along with a [[awaitAndAssert]] helper
  *   - FormValidation provides [[play.api.data.Form]] validation: must be error free and must have these errors
  *   - JsonValidation provides [[play.api.libs.json.JsResult]] validation: must be error free and must have these errors
  *
  * @author Chris J W Walker
  */
trait UnitTestSpec
  extends PlaySpec
    with MockitoSugar
    with BeforeAndAfter
    with BeforeAndAfterEach
    with BeforeAndAfterAll
    with HeaderNames
    with Status
    with MimeTypes
    with HttpProtocol
    with ResultExtractors
    with Writeables
    with EssentialActionCaller
    with RouteInvokers
    with FutureHelpers
    with FormValidation
    with JsonValidation {

  /**
    * Another convenience helper to overcome the no implicit execution context found...
    * try importing scala.concurrent.ExecutionContext.Implicits.global
    * @return [[ExecutionContext]]
    */
  implicit val ec: ExecutionContext = global.prepare()

  /**
    * Provides mock reactive mongo [[reactivemongo.api.commands.WriteResult]] and [[reactivemongo.api.commands.UpdateWriteResult]]
    */
  object MongoFakes extends FakeMongoResults with MockitoSugar

}


