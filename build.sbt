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
  case Failure(_)   => "0.1.0"
}

val jettyVersion         = "9.2.13.v20150730"

val dependencies = Seq(
  "org.scalatestplus.play" % "scalatestplus-play_2.12" % "3.0.0",
  "org.mockito"            % "mockito-core"            % "2.18.3",
  "org.reactivemongo"     %% "play2-reactivemongo"     % "0.13.0-play26",
  "com.github.tomakehurst" % "wiremock-standalone"     % "2.17.0"
)

val library = Project(libraryName, file("."))
  .settings(
    version                              :=  btVersion,
    scalaVersion                         :=  "2.12.6",
    organization                         :=  "com.cjww-dev.libs",
    resolvers                            +=  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
    libraryDependencies                  ++= dependencies,
    bintrayOrganization                  :=  Some("cjww-development"),
    bintrayReleaseOnPublish in ThisBuild :=  true,
    bintrayRepository                    :=  "releases",
    bintrayOmitLicense                   :=  true
  )
