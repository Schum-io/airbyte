#!/usr/bin/env bash

VERSION=${VERSION:-0.0.7}
REGISTRY=${REGISTRY:-shodanio/airbyte-destination-clickhouse-replication:$VERSION}

../../../gradlew :airbyte-integrations:connectors:destination-clickhouse:build
docker image tag airbyte/destination-clickhouse:dev $REGISTRY
docker push $REGISTRY

# clickhouse-replication
# https://docs.airbyte.com/integrations/destinations/clickhouse/