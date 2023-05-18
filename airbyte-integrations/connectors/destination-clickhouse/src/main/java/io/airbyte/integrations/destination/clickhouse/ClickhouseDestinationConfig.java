/*
 * Copyright (c) 2023 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.integrations.destination.clickhouse;

import com.fasterxml.jackson.databind.JsonNode;

// This configuration class does not include the default JDBC configuration parameters.
public record ClickhouseDestinationConfig(String engine,
                                          ClickhouseDestinationDeployTypeConfig deploy_config,
                                          String database_engine) {

  public final static String DEFAULT_ENGINE = "MergeTree";

  public static ClickhouseDestinationConfig get(final JsonNode config) {
    return new ClickhouseDestinationConfig(
        config.has("engine") ? config.get("engine").asText() : DEFAULT_ENGINE,
        config.has("deploy_type") ? ClickhouseDestinationDeployTypeConfig.get(config.get("deploy_type"))
            : ClickhouseDestinationDeployTypeConfig.defaultConfig(),
        config.has("database_engine") ? config.get("database_engine").asText()
            : ClickhouseDestinationDeployTypeConfig.DEFAULT_DATABASE_ENGINE);
  }

}