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

import com.typesafe.config.ConfigFactory
import scala.util.{Try, Success, Failure}

val libraryName = "testing-framework"

val btVersion: String = Try(ConfigFactory.load.getString("version")) match {
  case Success(ver) => ver
  case Failure(_)   => ""
}

val dependencies = Seq(
  "org.scalatestplus.play" % "scalatestplus-play_2.11"  % "2.0.1",
  "org.mockito"            % "mockito-core"             % "2.13.0",
  "org.reactivemongo"      % "play2-reactivemongo_2.11" % "0.11.14",
  "com.github.tomakehurst" % "wiremock"                 % "2.8.0"
)

val library = Project(libraryName, file("."))
  .settings(
    version                              :=  btVersion,
    scalaVersion                         :=  "2.11.11",
    organization                         :=  "com.cjww-dev.libs",
    resolvers                            +=  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
    libraryDependencies                  ++= dependencies,
    bintrayOrganization                  :=  Some("cjww-development"),
    bintrayReleaseOnPublish in ThisBuild :=  true,
    bintrayRepository                    :=  "releases",
    bintrayOmitLicense                   :=  true
  )
