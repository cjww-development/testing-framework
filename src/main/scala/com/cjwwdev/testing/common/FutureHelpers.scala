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

package com.cjwwdev.testing.common

import akka.util.Timeout
import org.scalatest.Assertion
import org.scalatestplus.play.PlaySpec
import play.api.mvc.Result
import play.api.test.{DefaultAwaitTimeout, FutureAwaits}

import scala.concurrent.Future
import scala.concurrent.duration._

/**
  * FutureHelpers provides access to FutureAwaits and DefaultAwaitTimeout
  *
  * @author Chris J W Walker
  */
trait FutureHelpers extends FutureAwaits with DefaultAwaitTimeout {
  self: PlaySpec =>

  /**
    * overridden defaultAwaitTimeout
    *
    * 20 by default felt too long, reduced down to 5 seconds
    *
    * @return Timeout
    */
  override implicit def defaultAwaitTimeout: Timeout = 5.seconds

  /**
    * @param f function that returns type Future[A]
    * @param assert A => [[Assertion]]
    * @return Assertion
    */
  def awaitAndAssert[A](f: => Future[A])(assert: A => Assertion): Assertion = {
    assert(await(f))
  }

  /**
    * @param methodUnderTest function that returns type Future[Result]
    * @param assertions Future[Result] => [[Assertion]]
    * @return Assertion
    */
  def assertFutureResult(methodUnderTest: => Future[Result])(assertions: Future[Result] => Assertion): Assertion = {
    assertions(methodUnderTest)
  }
}
