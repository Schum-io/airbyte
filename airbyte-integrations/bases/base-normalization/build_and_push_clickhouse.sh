#!/usr/bin/env bash

VERSION=${VERSION:-0.0.11}
REGISTRY=${REGISTRY:-registry.company.io/data/airbyte/normalization-clickhouse:$VERSION}

docker build --platform linux/amd64 -t $REGISTRY --file clickhouse.Dockerfile .
docker push $REGISTRY

# clickhouse-replication
# https://docs.airbyte.com/integrations/destinations/clickhouse/