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

package com.cjwwdev.test

import com.cjwwdev.test.data.{JsonValidator, TestDataGenerator}
import com.cjwwdev.test.enums.AccountEnums
import com.cjwwdev.test.http.FakeResponseBuilder
import com.cjwwdev.test.mongo.FakeMongoResults
import org.scalatest.BeforeAndAfterEach
import org.scalatest.mockito.MockitoSugar
import org.scalatestplus.play.PlaySpec
import play.api.http.{HeaderNames, HttpProtocol, MimeTypes, Status}
import play.api.test._

trait BaseComponentSpec
  extends PlaySpec
    with MockitoSugar
    with BeforeAndAfterEach
    with TestDataGenerator
    with JsonValidator
    with HeaderNames
    with Status
    with MimeTypes
    with HttpProtocol
    with DefaultAwaitTimeout
    with ResultExtractors
    with Writeables
    with EssentialActionCaller
    with RouteInvokers
    with FutureAwaits {

  object MongoFakes extends FakeMongoResults

  object ResponseFakes extends FakeResponseBuilder

  object AccountEnums extends AccountEnums
}
