#!/bin/sh

set -o errexit ; set -o nounset

${LIFERAY_PORTAL_DIR}/gradlew deploy

cp build/libs/*.jar ${LIFERAY_BUNDLES_PORTAL_DIR}/liferay-portal/deploy
