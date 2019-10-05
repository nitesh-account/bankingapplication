package com.bankingapplication.rest.utils;

import java.time.Instant;

/**
 * DateAssistantUtils class is used for date related common method
 *
 * @author Nitesh Kumar
 */
public class DateAssistantUtils {

  public static Long setDate() {
    return Instant.now().getEpochSecond();
  }
}
