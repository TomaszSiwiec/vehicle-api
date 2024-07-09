Feature: S3 test scenario

  Scenario: Should successfully upload file to bucket
    Given prepare image to upload
    When upload image
    Then check image in bucket
    And check image in database
