package technologyconversations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import scala.util.Properties._

class Books extends Simulation {

  val httpConf = http.baseURL(envOrElse("DOMAIN", "http://localhost"))
  val users = envOrElse("USERS", "100").toInt
  val usersOverSeconds = envOrElse("USERS_OVER_SECONDS", users.toString).toInt
  val maxResponseTime = envOrElse("MAX_RESPONSE_TIME", "1000").toInt

  val jsonHeader = Map("Content-Type" -> "application/json")

  val scn = scenario("books")
    .exec(
      http("post")
        .put("/api/v1/books")
        .body(StringBody(
          """{
            |"_id": 99999,
            |"title": "Stressed Book",
            |"author": "John Doe",
            |"description": "How to get stressed"
            |}""".stripMargin))
        .asJSON
    )
    .pause(2)

  setUp(scn.inject(rampUsers(users) over (usersOverSeconds seconds))
    .protocols(httpConf))
    .assertions(global.responseTime.max.lessThan(maxResponseTime))

}
