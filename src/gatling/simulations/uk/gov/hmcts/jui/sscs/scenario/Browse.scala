package uk.gov.hmcts.jui.sscs.scenario

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration
import uk.gov.hmcts.jui.sscs.scenario.utils._


object Browse {

 val IdamJUIURL = scala.util.Properties.envOrElse("IDAM_WEB_URL", Environment.IDAM_WEB_URL).toLowerCase()
  val feedASSCSJudgeData = csv("JUISSCSUser.csv").circular
  val MinThinkTime = Environment.minThinkTime
  val MaxThinkTime = Environment.maxThinkTime

  val landingLoginPage = exec(http("TC01_JUI_LandingLoginPage")
    .get("/")
    .check(css("input[name='_csrf']", "value").saveAs("csrftoken"))
    .check(css(".form-group>input[name='upliftToken']", "value").saveAs("upliftToken"))
    .check(css(".form-group>input[name='response_type']", "value").saveAs("response_type"))
    .check(css(".form-group>input[name='redirect_uri']", "value").saveAs("redirect_uri"))
    .check(css(".form-group>input[name='client_id']", "value").saveAs("client_id"))
    .check(css(".form-group>input[name='scope']", "value").saveAs("scope"))
    .check(css(".form-group>input[name='state']", "value").saveAs("state"))
    .check(css(".form-group>input[name='continue']", "value").saveAs("continue")))
    .exec(http("TC01_JUI_LandingLoginPage_govuk-template-print.css")
      .get(IdamJUIURL + "/public/stylesheets/govuk-template-print.css?0.23.0")
      .resources(http("TC01_JUI_LandingLoginPage_govuk-template.js")
        .get(IdamJUIURL + "/public/javascripts/govuk-template.js?0.23.0"),
        http("TC01_JUI_LandingLoginPage_gov.uk_logotype_crown_invert_trans.png")
          .get(IdamJUIURL + "/public/images/gov.uk_logotype_crown_invert_trans.png?0.23.0"),
        http("TC01_JUI_LandingLoginPage_govuk-template.css")
          .get(IdamJUIURL + "/public/stylesheets/govuk-template.css?0.23.0"),
        http("TC01_JUI_LandingLoginPage_fonts.css")
          .get(IdamJUIURL + "/public/stylesheets/fonts.css?0.23.0"),
        http("TC01_JUI_LandingLoginPage_open-government-licence_2x.png")
          .get(IdamJUIURL + "/public/stylesheets/images/open-government-licence_2x.png?0.23.0"),
        http("TC01_JUI_LandingLoginPage_gov.uk_logotype_crown.png")
          .get(IdamJUIURL + "/public/stylesheets/images/gov.uk_logotype_crown.png?0.23.0"),
        http("TC01_JUI_LandingLoginPage_govuk-crest-2x.png")
          .get(IdamJUIURL + "/public/stylesheets/images/govuk-crest-2x.png?0.23.0")))
    .feed(feedASSCSJudgeData)
    .pause(MinThinkTime, MaxThinkTime)
}