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
import play.api.data.Form

/**
  * FormValidation provides helpers to assert that [[play.api.data.Form]]
  * doesn't have any errors and that a form must have this set of errors
  *
  * @author Chris J W Walker
  */
trait FormValidation {
  self: PlaySpec =>

  implicit class FormValidationMethods[T](form: Form[T]) {

    /**
      * @return Assertion
      */
    def mustNotHaveErrors: Assertion = {
      form.hasErrors & form.hasGlobalErrors mustBe false
    }

    /**
      * @param errorList Seq[(String, String)]
      * @return Unit
      */
    def mustHaveTheseErrors(errorList: (String, String)*): Unit = {
      if(form.errors.size != errorList.size) {
        fail(s"${form.errors.size} did not equal ${errorList.size}")
      } else {
        for((key, msg) <- errorList) {
          val formError = form.error(key).getOrElse(throw new NoSuchElementException(s"No form error found for key $key"))
          formError.message mustBe msg
        }
      }
    }
  }
}
