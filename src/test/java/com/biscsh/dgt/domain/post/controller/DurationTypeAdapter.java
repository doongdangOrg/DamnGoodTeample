package com.biscsh.dgt.domain.post.controller;

import com.google.gson.*;
import org.hibernate.validator.internal.util.logging.formatter.DurationFormatter;

import java.lang.reflect.Type;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DurationTypeAdapter implements JsonSerializer<Duration>, JsonDeserializer<Duration> {

//  private static final DurationFormatter formatter = DurationFormatter("yyyy-MM-dd HH:mm:ss");

  @Override
  public JsonElement serialize(Duration duration, Type srcType,
                               JsonSerializationContext context) {
    
    return new JsonPrimitive(new DurationFormatter(duration).toString());
  }

  @Override
  public Duration deserialize(JsonElement json, Type typeOfT,
      JsonDeserializationContext context) throws JsonParseException {

    return Duration.parse(json.getAsString());
  }
}