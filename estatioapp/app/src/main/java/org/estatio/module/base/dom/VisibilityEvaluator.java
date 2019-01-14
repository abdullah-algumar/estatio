/*
 *
 *  Copyright 2012-2014 Eurocommercial Properties NV
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.estatio.module.base.dom;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Programmatic;

import org.isisaddons.module.security.app.user.MeService;
import org.isisaddons.module.security.dom.tenancy.ApplicationTenancyEvaluator;
import org.isisaddons.module.security.dom.user.ApplicationUser;

/**
 * To programmatically filter out domain objects that the current user wouldn't normally be able to see.
 */
@DomainService(nature = NatureOfService.DOMAIN)
public class VisibilityEvaluator {

    @Programmatic
    public boolean visibleToMe(final Object domainObject) {
        final ApplicationUser meAsApplicationUser = meService.me();
        return evaluator.hides(domainObject, meAsApplicationUser) == null;
    }

    @Inject
    MeService meService;

    @Inject
    ApplicationTenancyEvaluator evaluator;
}