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

import org.scalatest.Assertion
import org.scalatestplus.play.PlaySpec
import play.api.data.validation.ValidationError
import play.api.libs.json.{JsError, JsPath, JsResult, JsSuccess}

/**
  * JsonValidation provides helpers to validate [[play.api.libs.json.JsResult]]
  *
  * @author Chris J W Walker
  */
trait JsonValidation {
  self: PlaySpec =>

  implicit class JsResultValidator[T](jsResult: JsResult[T]) {

    /**
      * asserts that [[JsResult.isSuccess]]
      *
      * @return [[Assertion]]
      */
    def mustNotHaveErrors: Assertion = assert(jsResult.isSuccess)

    /**
      * Taken from the latest scala play docs
      *  https://www.playframework.com/documentation/2.6.x/ScalaI18N#Notes-on-apostrophes
      *
      * Since Messages uses [[java.text.MessageFormat]], single quotes are used as a meta-character
      * for escaping parameter substitutions.
      *
      * This class provides functionality to validate scala play message files don't contain
      * lonesome single quotes. Use the regex along with validateMessagesAgainstRegex()
      *
      * override messagesLocation with the location of your messages file i.e ''conf/messages''
      *
      * @param expectedErrors [[Map]] of [[JsPath]] -> [[Seq ValidationError]]
      * @return Unit
      */
    def mustHaveErrors(expectedErrors: Map[JsPath, Seq[ValidationError]]): Unit = jsResult match {
      case JsSuccess(data, _) => fail(s"read should have failed and didn't - reads produced $data")
      case JsError(errors)    => for((path, errs) <- errors) {
        expectedErrors.keySet must   contain(path)
        expectedErrors(path)  mustBe errs
      }
    }
  }
}
