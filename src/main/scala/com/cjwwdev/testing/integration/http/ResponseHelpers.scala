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

package com.cjwwdev.testing.integration.http

import play.api.http.{HeaderNames, MimeTypes, Status}
import play.api.libs.json._
import play.api.libs.ws.{WSCookie, WSResponse}

/**
  * ResponseHelpers provides helper functions to get values from a WSResponse
  *
  * @author Chris J W Walker
  */
trait ResponseHelpers extends Status with MimeTypes with HeaderNames {

  /**
    * @param resp WSResponse
    * @return Int
    */
  def statusOf(resp: WSResponse): Int = resp.status

  /**
    * @param resp WSResponse
    * @return String
    */
  def statusTextOf(resp: WSResponse): String = resp.statusText

  /**
    * @param resp WSResponse
    * @return T
    */
  def jsonContent[T](resp: WSResponse)(implicit reads: Reads[T]): T = Json.fromJson[T](resp.json).get

  /**
    * @param resp WSResponse
    * @return T
    */
  def bodyAsJson[T](resp: WSResponse)(implicit reads: Reads[T]): T = {
    Json.parse(resp.body).validate[T] match {
      case JsSuccess(t, _) => t
      case JsError(errors) => throw JsResultException(errors)
    }
  }

  /**
    * @param resp WSResponse
    * @return String
    */
  def bodyAsString(resp: WSResponse): String = resp.body

  /**
    * @param resp WSResponse
    * @return Map[String, Seq[String] ]
    */
  def headersOf(resp: WSResponse): Map[String, scala.collection.Seq[String]] = resp.headers

  /**
    * @param key String
    * @param resp WSResponse
    * @return Option[String]
    */
  def headerFrom(key: String, resp: WSResponse): Option[String] = resp.header(key)

  /**
    * @param resp WSResponse
    * @return Seq[WSCookie]
    */
  def cookiesOf(resp: WSResponse): scala.collection.Seq[WSCookie] = resp.cookies

  /**
    * @param resp WSResponse
    * @return Option[WSCookie]
    */
  def cookieFrom(key: String, resp: WSResponse): Option[WSCookie] = resp.cookie(key)
}
