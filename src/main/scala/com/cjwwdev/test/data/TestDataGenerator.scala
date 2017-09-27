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
package com.cjwwdev.test.data

import java.util.UUID

import org.joda.time.{DateTime, DateTimeZone}

trait TestDataGenerator {
  val USER      = "user"
  val ORG       = "org-user"
  val CONTEXT   = "context"
  val SESSION   = "session"
  val DEVERSITY = "deversity"
  val DIAG      = "diag"
  val HUB       = "hub"
  val FEED_ITEM = "feed-item"

  val uuid = UUID.randomUUID()

  val now = DateTime.now(DateTimeZone.UTC)

  def generateTestSystemId(prefix: String): String = s"$prefix-$uuid"
}
