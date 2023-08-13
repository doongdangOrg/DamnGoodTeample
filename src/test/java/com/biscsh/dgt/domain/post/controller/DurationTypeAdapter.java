package com.biscsh.dgt.domain.post.controller;

import com.google.gson.*;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.hibernate.validator.internal.util.logging.formatter.DurationFormatter;

import java.lang.reflect.Type;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DurationTypeAdapter implements JsonSerializer<Duration>, JsonDeserializer<Duration> {


  @Override
  public JsonElement serialize(Duration duration, Type srcType,
                               JsonSerializationContext context) {
    
    return new JsonPrimitive(DurationFormatUtils.formatDurationHMS(duration.toMillis()));
  }

  @Override
  public Duration deserialize(JsonElement json, Type typeOfT,
      JsonDeserializationContext context) throws JsonParseException {
    String[] split = json.getAsString().split(":");
    return Duration.parse("P" + split[0] + "H" + split[1] + "M" + split[2] + "S");
  }
}