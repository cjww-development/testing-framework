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

import com.cjwwdev.testing.unit.UnitTestSpec
import org.scalatest.Assertion

import scala.io.Source

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
  * @author Chris J W Walker
  */
trait MessagesVerification {
  self: UnitTestSpec =>

  val messagesLocation: String

  lazy val messagesFile = Source.fromFile(messagesLocation)

  val noSingleQuotes     = """^.+[=].*[^']'[^'].*$"""

  def key(line: String): String = line.takeWhile(_ != '=')

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
    * @param regex regex to validate messages against
    * @return Unit
    */
  def validateMessagesAgainstRegex(regex: String): Unit = {
    messagesFile.getLines.foreach { line =>
      if(!line.matches(regex)) {
        fail(s"Key ${key(line)} did not match given regex ()")
      }
    }
  }
}
