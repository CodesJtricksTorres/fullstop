/**
 * Copyright 2015 Zalando SE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.zalando.stups.fullstop.violation.store.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zalando.stups.fullstop.violation.ViolationStore;

/**
 * Simple implementation to use the logging-framework of choice to collection violations.
 *
 * @author  jbellmann
 */
public class Slf4jViolationStore implements ViolationStore {

    private static final String LOGGER_NAME = "fullstop.violations.store";
    public static final String VIOLATION = "VIOLATION: ";

    private final Logger logger = LoggerFactory.getLogger(LOGGER_NAME);

    @Override
    public void save(final Object violation) {
        logger.info(VIOLATION + violation.toString());
    }

}