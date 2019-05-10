package uk.gov.hmcts.jui.sscs.scenario

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.jui.sscs.scenario.utils._

object JUIDecision {
  val MinThinkTime = Environment.minThinkTime
  val MaxThinkTime = Environment.maxThinkTime
  val CommonSSCSHeader = Environment.commonSSCSHeader

  val submitDecision = exec(http("JUI_120_MakeDecision")
    .get("/api/decisions/state/SSCS/Benefit/${New_Case_Id}/create"))
    .pause(7)
    .exec(http("JUI_130_SelectPreliminaryView")
      .post("/api/decisions/state/SSCS/Benefit/${New_Case_Id}/create")
      .headers(CommonSSCSHeader)
      .body(StringBody("""{"formValues":{"preliminaryView":"yes","visitedPages":{"create":true,"final-decision":true}},"event":"continue"}"""))
      .resources(http("JUI_140_005_SelectAwards")
        .get("/api/decisions/state/SSCS/Benefit/${New_Case_Id}/preliminary-advanced")))
    .pause(MinThinkTime, MaxThinkTime)

    .exec(http("JUI_140_010_SelectAwards")
      .post("/api/decisions/state/SSCS/Benefit/${New_Case_Id}/preliminary-advanced")
      .headers(CommonSSCSHeader)
      .body(StringBody("""{"formValues":{"preliminaryView":"yes","forDailyLiving":"standardRate","forMobility":"standardRate","compareToDWPAward":"Higher","visitedPages":{"create":true,"preliminary-advanced":true,"final-decision":true}},"event":"continue"}"""))
      .resources(http("JUI_150_005_SelectAwardDates")
        .get("/api/decisions/state/SSCS/Benefit/${New_Case_Id}/set-award-dates")))
    .pause(MinThinkTime, MaxThinkTime)

    .exec(http("JUI_150_010_SelectAwardDates")
      .post("/api/decisions/state/SSCS/Benefit/${New_Case_Id}/set-award-dates")
      .headers(CommonSSCSHeader)
      .body(StringBody("""{"formValues":{"preliminaryView":"yes","forDailyLiving":"standardRate","forMobility":"standardRate","compareToDWPAward":"Higher","awardStartDateDay":"12","awardStartDateMonth":"04","awardStartDateYear":"2018","endDateRadio":"endDate","awardEndDateDay":"12","awardEndDateMonth":"03","awardEndDateYear":"2019","visitedPages":{"create":true,"preliminary-advanced":true,"final-decision":true,"set-award-dates":true}},"event":"continue"}"""))
      .resources(http("JUI_160_005_Scores")
        .get("/api/decisions/state/SSCS/Benefit/${New_Case_Id}/scores")))
    .pause(MinThinkTime, MaxThinkTime)

    .exec(http("JUI_160_010_Scores")
      .post("/api/decisions/state/SSCS/Benefit/${New_Case_Id}/scores")
      .headers(CommonSSCSHeader)
      .body(StringBody("""{"formValues":{"preliminaryView":"yes","forDailyLiving":"standardRate","forMobility":"standardRate","compareToDWPAward":"Higher","awardStartDateDay":"12","awardStartDateMonth":"04","awardStartDateYear":"2018","endDateRadio":"endDate","awardEndDateDay":"12","awardEndDateMonth":"03","awardEndDateYear":"2019","preparingFood":true,"takingNutrition":true,"managingTherapy":false,"washingBathing":true,"managingToilet":false,"dressingUndressing":false,"communicatingVerbally":false,"readingAndUnderstanding":true,"engagingWithOtherPeople":false,"makingBudgetingDecisions":true,"planningFollowingJourneys":true,"movingAround":true,"visitedPages":{"create":true,"preliminary-advanced":true,"final-decision":true,"set-award-dates":true,"scores":true}},"event":"continue"}"""))
      .resources(http("JUI_170_005_PreparingFood")
        .get("/api/decisions/state/SSCS/Benefit/${New_Case_Id}/preparing-food")))
    .pause(MinThinkTime, MaxThinkTime)

    .exec(http("JUI_170_010_PreparingFood")
      .post("/api/decisions/state/SSCS/Benefit/${New_Case_Id}/preparing-food")
      .headers(CommonSSCSHeader)
      .body(StringBody("""{"formValues":{"preliminaryView":"yes","forDailyLiving":"standardRate","forMobility":"standardRate","compareToDWPAward":"Higher","awardStartDateDay":"12","awardStartDateMonth":"04","awardStartDateYear":"2018","endDateRadio":"endDate","awardEndDateDay":"12","awardEndDateMonth":"03","awardEndDateYear":"2019","preparingFood":true,"takingNutrition":true,"managingTherapy":false,"washingBathing":true,"managingToilet":false,"dressingUndressing":false,"communicatingVerbally":false,"readingAndUnderstanding":true,"engagingWithOtherPeople":false,"makingBudgetingDecisions":true,"planningFollowingJourneys":true,"movingAround":true,"dailyLivingPreparingFood":"4","visitedPages":{"create":true,"preliminary-advanced":true,"final-decision":true,"set-award-dates":true,"scores":true,"preparing-food":true}},"event":"continue"}"""))
      .resources(http("JUI_180_005_TakingNutrition")
        .get("/api/decisions/state/SSCS/Benefit/${New_Case_Id}/taking-nutrition")))
    .pause(MinThinkTime, MaxThinkTime)

    .exec(http("JUI_180_010_TakingNutrition")
      .post("/api/decisions/state/SSCS/Benefit/${New_Case_Id}/taking-nutrition")
      .headers(CommonSSCSHeader)
      .body(StringBody("""{"formValues":{"preliminaryView":"yes","forDailyLiving":"standardRate","forMobility":"standardRate","compareToDWPAward":"Higher","awardStartDateDay":"12","awardStartDateMonth":"04","awardStartDateYear":"2018","endDateRadio":"endDate","awardEndDateDay":"12","awardEndDateMonth":"03","awardEndDateYear":"2019","preparingFood":true,"takingNutrition":true,"managingTherapy":false,"washingBathing":true,"managingToilet":false,"dressingUndressing":false,"communicatingVerbally":false,"readingAndUnderstanding":true,"engagingWithOtherPeople":false,"makingBudgetingDecisions":true,"planningFollowingJourneys":true,"movingAround":true,"dailyLivingPreparingFood":"4","dailyLivingTakingNutrition":"6","visitedPages":{"create":true,"preliminary-advanced":true,"final-decision":true,"set-award-dates":true,"scores":true,"preparing-food":true,"taking-nutrition":true}},"event":"continue"}"""))
      .resources(http("JUI_190_005_WashingBathing")
        .get("/api/decisions/state/SSCS/Benefit/${New_Case_Id}/washing-bathing")))
    .pause(MinThinkTime, MaxThinkTime)

    .exec(http("JUI_190_010_WashingBathing")
      .post("/api/decisions/state/SSCS/Benefit/${New_Case_Id}/washing-bathing")
      .headers(CommonSSCSHeader)
      .body(StringBody("""{"formValues":{"preliminaryView":"yes","forDailyLiving":"standardRate","forMobility":"standardRate","compareToDWPAward":"Higher","awardStartDateDay":"12","awardStartDateMonth":"04","awardStartDateYear":"2018","endDateRadio":"endDate","awardEndDateDay":"12","awardEndDateMonth":"03","awardEndDateYear":"2019","preparingFood":true,"takingNutrition":true,"managingTherapy":false,"washingBathing":true,"managingToilet":false,"dressingUndressing":false,"communicatingVerbally":false,"readingAndUnderstanding":true,"engagingWithOtherPeople":false,"makingBudgetingDecisions":true,"planningFollowingJourneys":true,"movingAround":true,"dailyLivingPreparingFood":"4","dailyLivingTakingNutrition":"6","dailyLivingWashingBathing":"3","visitedPages":{"create":true,"preliminary-advanced":true,"final-decision":true,"set-award-dates":true,"scores":true,"preparing-food":true,"taking-nutrition":true,"washing-bathing":true}},"event":"continue"}"""))
      .resources(http("JUI_200_005_ReadingSigns")
        .get("/api/decisions/state/SSCS/Benefit/${New_Case_Id}/reading-signs")))
    .pause(MinThinkTime, MaxThinkTime)

    .exec(http("JUI_200_010_ReadingSigns")
      .post("/api/decisions/state/SSCS/Benefit/${New_Case_Id}/reading-signs")
      .headers(CommonSSCSHeader)
      .body(StringBody("""{"formValues":{"preliminaryView":"yes","forDailyLiving":"standardRate","forMobility":"standardRate","compareToDWPAward":"Higher","awardStartDateDay":"12","awardStartDateMonth":"04","awardStartDateYear":"2018","endDateRadio":"endDate","awardEndDateDay":"12","awardEndDateMonth":"03","awardEndDateYear":"2019","preparingFood":true,"takingNutrition":true,"managingTherapy":false,"washingBathing":true,"managingToilet":false,"dressingUndressing":false,"communicatingVerbally":false,"readingAndUnderstanding":true,"engagingWithOtherPeople":false,"makingBudgetingDecisions":true,"planningFollowingJourneys":true,"movingAround":true,"dailyLivingPreparingFood":"4","dailyLivingTakingNutrition":"6","dailyLivingWashingBathing":"3","dailyLivingReadingSigns":"4","visitedPages":{"create":true,"preliminary-advanced":true,"final-decision":true,"set-award-dates":true,"scores":true,"preparing-food":true,"taking-nutrition":true,"washing-bathing":true,"reading-signs":true}},"event":"continue"}"""))
      .resources(http("JUI_210_005_BudgetingDecisions")
        .get("/api/decisions/state/SSCS/Benefit/${New_Case_Id}/budgeting-decisions")))
    .pause(MinThinkTime, MaxThinkTime)

    .exec(http("JUI_210_010_BudgetingDecisions")
      .post("/api/decisions/state/SSCS/Benefit/${New_Case_Id}/budgeting-decisions")
      .headers(CommonSSCSHeader)
      .body(StringBody("""{"formValues":{"preliminaryView":"yes","forDailyLiving":"standardRate","forMobility":"standardRate","compareToDWPAward":"Higher","awardStartDateDay":"12","awardStartDateMonth":"04","awardStartDateYear":"2018","endDateRadio":"endDate","awardEndDateDay":"12","awardEndDateMonth":"03","awardEndDateYear":"2019","preparingFood":true,"takingNutrition":true,"managingTherapy":false,"washingBathing":true,"managingToilet":false,"dressingUndressing":false,"communicatingVerbally":false,"readingAndUnderstanding":true,"engagingWithOtherPeople":false,"makingBudgetingDecisions":true,"planningFollowingJourneys":true,"movingAround":true,"dailyLivingPreparingFood":"4","dailyLivingTakingNutrition":"6","dailyLivingWashingBathing":"3","dailyLivingReadingSigns":"4","dailyLivingMakingBudgetDecisions":"2","visitedPages":{"create":true,"preliminary-advanced":true,"final-decision":true,"set-award-dates":true,"scores":true,"preparing-food":true,"taking-nutrition":true,"washing-bathing":true,"reading-signs":true,"budgeting-decisions":true}},"event":"continue"}"""))
      .resources(http("JUI_220_005_PlanningJourneys")
        .get("/api/decisions/state/SSCS/Benefit/${New_Case_Id}/planning-journeys")))
    .pause(MinThinkTime, MaxThinkTime)

    .exec(http("JUI_220_010_PlanningJourneys")
      .post("/api/decisions/state/SSCS/Benefit/${New_Case_Id}/planning-journeys")
      .headers(CommonSSCSHeader)
      .body(StringBody("""{"formValues":{"preliminaryView":"yes","forDailyLiving":"standardRate","forMobility":"standardRate","compareToDWPAward":"Higher","awardStartDateDay":"12","awardStartDateMonth":"04","awardStartDateYear":"2018","endDateRadio":"endDate","awardEndDateDay":"12","awardEndDateMonth":"03","awardEndDateYear":"2019","preparingFood":true,"takingNutrition":true,"managingTherapy":false,"washingBathing":true,"managingToilet":false,"dressingUndressing":false,"communicatingVerbally":false,"readingAndUnderstanding":true,"engagingWithOtherPeople":false,"makingBudgetingDecisions":true,"planningFollowingJourneys":true,"movingAround":true,"dailyLivingPreparingFood":"4","dailyLivingTakingNutrition":"6","dailyLivingWashingBathing":"3","dailyLivingReadingSigns":"4","dailyLivingMakingBudgetDecisions":"2","MobilityPlanningJourneys":"4","visitedPages":{"create":true,"preliminary-advanced":true,"final-decision":true,"set-award-dates":true,"scores":true,"preparing-food":true,"taking-nutrition":true,"washing-bathing":true,"reading-signs":true,"budgeting-decisions":true,"planning-journeys":true}},"event":"continue"}"""))
      .resources(http("JUI_230_005_MovingAround")
        .get("/api/decisions/state/SSCS/Benefit/${New_Case_Id}/moving-around")))
    .pause(MinThinkTime, MaxThinkTime)

    .exec(http("JUI_230_010_MovingAround")
      .post("/api/decisions/state/SSCS/Benefit/${New_Case_Id}/moving-around")
      .headers(CommonSSCSHeader)
      .body(StringBody("""{"formValues":{"preliminaryView":"yes","forDailyLiving":"standardRate","forMobility":"standardRate","compareToDWPAward":"Higher","awardStartDateDay":"12","awardStartDateMonth":"04","awardStartDateYear":"2018","endDateRadio":"endDate","awardEndDateDay":"12","awardEndDateMonth":"03","awardEndDateYear":"2019","preparingFood":true,"takingNutrition":true,"managingTherapy":false,"washingBathing":true,"managingToilet":false,"dressingUndressing":false,"communicatingVerbally":false,"readingAndUnderstanding":true,"engagingWithOtherPeople":false,"makingBudgetingDecisions":true,"planningFollowingJourneys":true,"movingAround":true,"dailyLivingPreparingFood":"4","dailyLivingTakingNutrition":"6","dailyLivingWashingBathing":"3","dailyLivingReadingSigns":"4","dailyLivingMakingBudgetDecisions":"2","MobilityPlanningJourneys":"4","MobilityMovingAround":"8","visitedPages":{"create":true,"preliminary-advanced":true,"final-decision":true,"set-award-dates":true,"scores":true,"preparing-food":true,"taking-nutrition":true,"washing-bathing":true,"reading-signs":true,"budgeting-decisions":true,"planning-journeys":true,"moving-around":true}},"event":"continue"}"""))
      .resources(http("JUI_240_005_MakeDecisionCheck")
        .get("/api/decisions/state/SSCS/Benefit/${New_Case_Id}/check"),
        /*http("TX034_JUI_SelectCase_MakeDecision_GET_check_light-tabular-851b10ccdd-v1.woff2")
          .get("/assets/fonts/light-tabular-851b10ccdd-v1.woff2"),
        http("TX034_JUI_SelectCase_MakeDecision_GET_check_bold-tabular-b89238d840-v1.woff2")
          .get("/assets/fonts/bold-tabular-b89238d840-v1.woff2")*/))
    .pause(MinThinkTime, MaxThinkTime)

    .exec(http("JUI_240_010_MakeDecisionCheck")
      .post("/api/decisions/state/SSCS/Benefit/${New_Case_Id}/check")
      .headers(CommonSSCSHeader)
      .body(StringBody("""{"formValues":{"preliminaryView":"yes","visitedPages":{"create":true,"preliminary-advanced":true,"final-decision":true,"set-award-dates":true,"scores":true,"preparing-food":true,"taking-nutrition":true,"washing-bathing":true,"reading-signs":true,"budgeting-decisions":true,"planning-journeys":true,"moving-around":true},"forDailyLiving":"standardRate","forMobility":"standardRate","compareToDWPAward":"Higher","decisionNotes":"test of removal from list","awardStartDateDay":"12","awardStartDateMonth":"04","awardStartDateYear":"2018","endDateRadio":"endDate","awardEndDateDay":"12","awardEndDateMonth":"03","awardEndDateYear":"2019","preparingFood":true,"takingNutrition":true,"managingTherapy":false,"washingBathing":true,"managingToilet":false,"dressingUndressing":false,"communicatingVerbally":false,"readingAndUnderstanding":true,"engagingWithOtherPeople":false,"makingBudgetingDecisions":true,"planningFollowingJourneys":true,"movingAround":true,"dailyLivingPreparingFood":"4","dailyLivingTakingNutrition":"6","dailyLivingWashingBathing":"3","dailyLivingReadingSigns":"4","dailyLivingMakingBudgetDecisions":"2","MobilityPlanningJourneys":"4","MobilityMovingAround":"8","reasonsTribunalView":"Performance Testing 12345678910"},"event":"continue"}"""))
      .resources(http("JUI_250_005_CheckTribunal")
        .get("/api/decisions/state/SSCS/Benefit/${New_Case_Id}/check-tribunal")))
    .pause(MinThinkTime, MaxThinkTime)

    .exec(http("JUI_250_010_CheckTribunal")
      .post("/api/decisions/state/SSCS/Benefit/${New_Case_Id}/check-tribunal")
      .headers(CommonSSCSHeader)
      .body(StringBody("""{"formValues":{"preliminaryView":"yes","visitedPages":{"create":true,"preliminary-advanced":true,"final-decision":true,"set-award-dates":true,"scores":true,"preparing-food":true,"taking-nutrition":true,"washing-bathing":true,"reading-signs":true,"budgeting-decisions":true,"planning-journeys":true,"moving-around":true},"forDailyLiving":"standardRate","forMobility":"standardRate","compareToDWPAward":"Higher","decisionNotes":"test of removal from list","awardStartDateDay":"12","awardStartDateMonth":"04","awardStartDateYear":"2018","endDateRadio":"endDate","awardEndDateDay":"12","awardEndDateMonth":"03","awardEndDateYear":"2019","preparingFood":true,"takingNutrition":true,"managingTherapy":false,"washingBathing":true,"managingToilet":false,"dressingUndressing":false,"communicatingVerbally":false,"readingAndUnderstanding":true,"engagingWithOtherPeople":false,"makingBudgetingDecisions":true,"planningFollowingJourneys":true,"movingAround":true,"dailyLivingPreparingFood":"4","dailyLivingTakingNutrition":"6","dailyLivingWashingBathing":"3","dailyLivingReadingSigns":"4","dailyLivingMakingBudgetDecisions":"2","MobilityPlanningJourneys":"4","MobilityMovingAround":"8","reasonsTribunalView":"Performance Testing 12345678910"},"event":"continue"}""")))
    .pause(MinThinkTime, MaxThinkTime)
}