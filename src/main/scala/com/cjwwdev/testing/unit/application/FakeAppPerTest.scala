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

package com.cjwwdev.testing.unit.application

import com.cjwwdev.testing.unit.UnitTestSpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.i18n.MessagesApi

/**
  * FakeAppPerTest, generally, should not be needed for unit testing a scala play application.
  *
  * A use case for is if you're looking to validate messages from your messages file in a twirl html view
  * [[MessagesApi]] generally needs an application fired to work. This class exposes a testMessagesApi you
  * put into your view.
  *
  * @author Chris J W Walker
  */
trait FakeAppPerTest extends GuiceOneAppPerTest {
  self: UnitTestSpec =>

  implicit lazy val testMessagesApi: MessagesApi = app.injector.instanceOf[MessagesApi]
}
