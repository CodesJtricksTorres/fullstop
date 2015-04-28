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
package org.zalando.stups.fullstop.jobs;

import static org.zalando.stups.fullstop.jobs.UsersPredicates.PASSWORD_LAST_USED_HAS_NON_NULL_DATE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import org.zalando.stups.fullstop.jobs.annotation.EveryDayAtElevenPM;
import org.zalando.stups.fullstop.violation.ViolationStore;

import com.amazonaws.services.identitymanagement.model.ListUsersResult;
import com.amazonaws.services.identitymanagement.model.User;

/**
 * @author  jbellmann
 */
@Component
public class NoPasswordsJob {

    private final ViolationStore violationStore;

    private final IdentityManagementDataSource identityManagementDataSource;

    @Autowired
    public NoPasswordsJob(final ViolationStore violationStore,
            final IdentityManagementDataSource identityManagementDataSource) {
        this.identityManagementDataSource = identityManagementDataSource;
        this.violationStore = violationStore;
    }

    @EveryDayAtElevenPM
    public void check() {

        for (Tuple<String, ListUsersResult> listUsersResultPerAccount : getListUsersResultPerAccount()) {
            filter(listUsersResultPerAccount._1, listUsersResultPerAccount._2.getUsers());
        }

    }

    protected void filter(final String accountId, final List<User> users) {
        final UsersConsumer consumer = new UsersConsumer(violationStore, accountId);
        users.stream().filter(PASSWORD_LAST_USED_HAS_NON_NULL_DATE).forEach(consumer);
    }

    protected List<Tuple<String, ListUsersResult>> getListUsersResultPerAccount() {
        return this.identityManagementDataSource.getListUsersResultPerAccountWithTuple();
    }
}
