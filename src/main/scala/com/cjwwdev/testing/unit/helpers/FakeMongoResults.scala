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
package com.cjwwdev.testing.unit.helpers

import org.mockito.Mockito.when
import org.scalatestplus.mockito.MockitoSugar
import reactivemongo.api.commands.{UpdateWriteResult, WriteResult}

/**
  * FakeMongoResults mocks out a [[WriteResult]] and an [[UpdateWriteResult]]
  *
  * Four helpers providing a success and failure scenario for [[WriteResult]] and [[UpdateWriteResult]]
  *
  * @author Chris J W Walker
  */
trait FakeMongoResults {
  self: MockitoSugar =>

  def fakeSuccessWriteResult: WriteResult = {
    val mockWriteResult = mock[WriteResult]
    when(mockWriteResult.ok).thenReturn(true)
    mockWriteResult
  }

  def fakeFailedWriteResult: WriteResult = {
    val mockWriteResult = mock[WriteResult]
    when(mockWriteResult.ok).thenReturn(false)
    mockWriteResult
  }

  def fakeSuccessUpdateWriteResult: UpdateWriteResult = {
    val mockUpdateWriteResult = mock[UpdateWriteResult]
    when(mockUpdateWriteResult.ok).thenReturn(true)
    mockUpdateWriteResult
  }

  def fakeFailedUpdateWriteResult: UpdateWriteResult = {
    val mockUpdateWriteResult = mock[UpdateWriteResult]
    when(mockUpdateWriteResult.ok).thenReturn(false)
    mockUpdateWriteResult
  }
}
