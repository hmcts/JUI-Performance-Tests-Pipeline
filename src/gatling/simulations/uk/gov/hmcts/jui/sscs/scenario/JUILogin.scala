package uk.gov.hmcts.jui.sscs.scenario

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.jui.sscs.scenario.utils._

object JUILogin {

  val IdamJUIURL = scala.util.Properties.envOrElse("IDAM_WEB_URL", Environment.IDAM_WEB_URL).toLowerCase()
  val MinThinkTime = Environment.minThinkTime
  val MaxThinkTime = Environment.maxThinkTime
  val JUIBaseUrl = scala.util.Properties.envOrElse("URL_TO_TEST", Environment.JUI_URL).toLowerCase()
  val feedASSCSJudgeData = csv("JUISSCSUser.csv").circular
  val commonSSCSHeader = Environment.commonSSCSHeader
  val jsoncommonHeader = Environment.jsoncommonHeader
  val headers_0 = Environment.headers_0
  val headers_1 = Environment.headers_1
  val headers_7 = Environment.headers_7

  val submitLogin = group ("JUI_Login") {
    exec(http("JUI_020_005_Login")
      .post(IdamJUIURL + "/login?response_type=code&client_id=juiwebapp&redirect_uri=" + JUIBaseUrl + "/oauth2/callback") //.disableFollowRedirect
      .headers(headers_0)
      .formParam("username", "${SSCSUserName}") //${SSCSUserName}
      .formParam("password", "${SSCSUsrPwd}") //${SSCSUsrPwd}
      .formParam("save", "Sign in")
      .formParam("selfRegistrationEnabled", "false")
      .formParam("_csrf", "${csrftoken}"))

      .exec(http("JUI_020_010_Login")
        .get(JUIBaseUrl + "/api/cases/paginationMetadata")
        .headers(headers_7))

      .exec(http("JUI_020_015_Login")
        .get(JUIBaseUrl + "/api/cases?requestCcdPage=1")
        .headers(headers_7))
  }

      /*.exec(http("JUI_020_010_Login")
        .get(JUIBaseUrl + "/api/cases")
        .headers(headers_3)
        .check(regex("""case_id":(.*?),""").findAll.saveAs("P_cases"))
        .check(regex("""case_id":(.*?),""").count.gte(1).saveAs("pickCaseCounts")))U*/

      //.pause(MinThinkTime, MaxThinkTime)

      /*.exec(session => {
        val PickCaseCounts = session("pickCaseCounts").as[Int]
        val P_Cases = session("P_cases").as[List[Int]]
        val P_Case = P_Cases(Random.nextInt(PickCaseCounts)).toString
      session.set("P_case", P_Case)
      }).exec(session => {
        println(session)
        session
      })*/

      .pause(MinThinkTime, MaxThinkTime)
}