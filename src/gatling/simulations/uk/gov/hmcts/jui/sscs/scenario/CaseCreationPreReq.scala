package simulations.uk.gov.hmcts.jui.sscs.scenario

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.jui.sscs.scenario.utils._

object CaseCreationPreReq {

  val IdamCCDURL = scala.util.Properties.envOrElse("IDAM_WEB_URL", Environment.IDAM_WEB_URL).toLowerCase()
  val CCDBaseUrl = scala.util.Properties.envOrElse("CCD_URL", Environment.CCD_WEB_URL).toLowerCase()
  val feedASSCSJudgeData = csv("JUISSCSUser.csv").circular
  val MinThinkTime = Environment.minThinkTime
  val MaxThinkTime = Environment.maxThinkTime

  val headers_01 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3",
    "Accept-Encoding" -> "gzip, deflate, br",
    "Accept-Language" -> "en-US,en;q=0.9",
    "Origin" -> "https://idam-web-public.aat.platform.hmcts.net",
    "Upgrade-Insecure-Requests" -> "1")

  val homepage = exec(http("CCDTX01_Homepage")
    .get("/"))

    .exec(http("CCDTX01_JUI_LandingLoginPage_02")
      .get(IdamCCDURL + "/login?response_type=code&client_id=juiwebapp&redirect_uri=" + CCDBaseUrl + "/oauth2/callback")
      .check(css("input[name='_csrf']", "value").saveAs("csrftoken"))
      /*.check(css(".form-group>input[name='upliftToken']", "value").saveAs("upliftToken"))
      .check(css(".form-group>input[name='response_type']", "value").saveAs("response_type"))
      .check(css(".form-group>input[name='redirect_uri']", "value").saveAs("redirect_uri"))
      .check(css(".form-group>input[name='client_id']", "value").saveAs("client_id"))
      .check(css(".form-group>input[name='scope']", "value").saveAs("scope"))
      .check(css(".form-group>input[name='state']", "value").saveAs("state"))
      .check(css(".form-group>input[name='continue']", "value").saveAs("continue"))*/)

    .feed(feedASSCSJudgeData)

    .pause(MinThinkTime, MaxThinkTime)

  val login = exec(http("CCDTX03_Login")
      .post(IdamCCDURL + "/login?response_type=code&client_id=ccd_gateway&redirect_uri=https%3A%2F%2F" + CCDBaseUrl + "%2Foauth2redirect")
      .headers(headers_01)
      .formParam("username", "juijudgeloadtest1@gmail.com")
      .formParam("password", "Password12")
      .formParam("save", "Sign in")
      .formParam("selfRegistrationEnabled", "false")
      .formParam("_csrf", "${csrftoken}")
  )

}
