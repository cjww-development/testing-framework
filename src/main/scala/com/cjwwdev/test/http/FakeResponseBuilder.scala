// Copyright (C) 2016-2017 the original author or authors.
// See the LICENCE.txt file distributed with this work for additional
// information regarding copyright ownership.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.cjwwdev.test.http

import play.api.libs.ws.WSResponse

trait FakeResponseBuilder {
  def buildFakeWSResponse(responseCode: Int, responseBody: String): WSResponse = new WSResponse {
    override def cookie(name: String) = ???
    override def underlying[T]        = ???
    override def body                 = responseBody
    override def bodyAsBytes          = ???
    override def cookies              = ???
    override def allHeaders           = ???
    override def xml                  = ???
    override def statusText           = ???
    override def json                 = ???
    override def header(key: String)  = ???
    override def status               = responseCode
  }
}
