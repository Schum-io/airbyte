/*
 * Copyright (c) 2023 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.integrations.destination.clickhouse;

import com.fasterxml.jackson.databind.JsonNode;

public record ClickhouseDestinationDeployTypeConfig(String type, String cluster, boolean replication, String database_engine) {

  public static final String DEFAULT_DEPLOY_TYPE = "clickhouse-cloud";
  public static final String DEFAULT_CLUSTER_NAME = "{cluster}";
  public static final boolean DEFAULT_REPLICATION = false;
  public static final String DEFAULT_DATABASE_ENGINE = "Atomic";

  public static ClickhouseDestinationDeployTypeConfig get(final JsonNode config) {
    return new ClickhouseDestinationDeployTypeConfig(
        config.has("deploy_type") ? config.get("deploy_type").asText() : DEFAULT_DEPLOY_TYPE,
        config.has("cluster") ? EscapeClusterString(config.get("cluster").asText()) : EscapeClusterString(DEFAULT_CLUSTER_NAME),
        config.has("replication") ? config.get("replication").asBoolean() : DEFAULT_REPLICATION,
        config.has("database_engine") ? config.get("database_engine").asText(): DEFAULT_DATABASE_ENGINE);
  }

  private static String EscapeClusterString(String input) {
    String output = input;
    if (!output.startsWith("'")) {
        output = "'" + output;
    }
    if (!output.endsWith("'")) {
        output = output + "'";
    }
    return output;
  }

  public static ClickhouseDestinationDeployTypeConfig defaultConfig() {
    return new ClickhouseDestinationDeployTypeConfig(DEFAULT_DEPLOY_TYPE, DEFAULT_CLUSTER_NAME,
        DEFAULT_REPLICATION, DEFAULT_DATABASE_ENGINE);
  }

}