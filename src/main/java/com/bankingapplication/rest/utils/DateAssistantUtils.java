package com.bankingapplication.rest.utils;

import java.time.Instant;

public class DateAssistantUtils {

  public static Long setDate() {
    return Instant.now().getEpochSecond();
  }
}
