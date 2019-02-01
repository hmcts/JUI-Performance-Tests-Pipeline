package uk.gov.hmcts.jui.sscs.scenario

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.jui.sscs.scenario.utils._

import scala.util.Random

object JUILogin {

  //val IdamJUIURL = Environment.idamJUIURL
  val IdamJUIURL = scala.util.Properties.envOrElse("IDAM_WEB_URL", Environment.IDAM_WEB_URL).toLowerCase()
  val MinThinkTime = Environment.minThinkTime
  val MaxThinkTime = Environment.maxThinkTime

  val submitLogin = exec(http("TC02_JUI_SubmitLogin")
    .post(IdamJUIURL + "/login?response_type=code&client_id=${client_id}&redirect_uri=${redirect_uri}")
    .formParam("username", "${SSCSUserName}")
    .formParam("password", "${SSCSUsrPwd}")
    .formParam("continue", "${continue}")
    .formParam("upliftToken", "")
    .formParam("response_type", "${response_type}")
    .formParam("_csrf", "${csrftoken}")
    .formParam("redirect_uri", "${redirect_uri}")
    .formParam("client_id", "${client_id}")
    .formParam("scope", "")
    .formParam("state", "")
    .check(regex("""href="/case/SSCS/Benefit/(.*?)/casefile""").findAll.saveAs("P_cases"))
    .check(regex("""href="/case/SSCS/Benefit/(.*?)/casefile""").count.gte(1).saveAs("pickCaseCounts")))

    .pause(MinThinkTime, MaxThinkTime)

    .exec(http("TC02_JUI_SubmitLogin_bold-a2452cb66f-v1.woff2")
      .get("/assets/fonts/bold-a2452cb66f-v1.woff2")
      .resources(http("TC02_JUI_SubmitLogin_light-f38ad40456-v1.woff2")
        .get("/assets/fonts/light-f38ad40456-v1.woff2")))

    .exec(session => {
      val PickCaseCounts = session("pickCaseCounts").as[Int]
      val P_Cases = session("P_cases").as[List[Int]]
      val P_Case = P_Cases(Random.nextInt(PickCaseCounts)).toString
      session.set("P_case", P_Case)
    }).exec(session => {
    println(session)
    session
  })
    .pause(MinThinkTime, MaxThinkTime)
}
